/*
 * is this output	1 0 1 0 1 ...
 *			...
 * the only possible output?
 *
 * Falsch: es ist nichtgarantiert, in welcher die
 * Threads eintreten.
 */
public class T extends Thread	{
	private String info;
	static Object o = new Object();
	public T (String info) {
		this.info    = info;
	}
	public void run () {
		synchronized ( o ) {
			while ( true )	{
				System.out.println(info);
				try {
					o.notify();
					sleep(100);
					o.wait();
				} catch ( Exception e ) { }
			}
		}
	}
	public static void main (String args []) {
		( new T("0") ).start();
		( new T("1") ).start();
	}
}
