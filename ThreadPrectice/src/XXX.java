/*
 * Should print out 0 1 0 1 0 1 ...
 * Is this correct?
 *
 * nicht richtig,
 * weil der Konstruktor fuer das Objekt mit der Id 0
 * nicht zuende gehen muss bevor der 2. Konstruktor
 * zuende geht.
 * 
 */
public class XXX extends Thread	{
	private String info;
	static Object o = new Object();

	public XXX (String info) {
		this.info    = info;
		synchronized ( o ) {
			if ( info.equals("0") )
				( new XXX("1") ).start();
		}
		// hier ein Schlafen einbauen
	}

	public void run () {
		System.out.println(Thread.currentThread().getName());
		int id = 0;
		while ( true )	{
			synchronized ( o ) {
				System.out.println(info);
				try {
					o.notify();
					sleep(10000000);
					o.wait();
				} catch ( Exception e ) { }
			}
		}
	}
	public static void main (String args []) {
		new XXX("0").start();
	}
}
