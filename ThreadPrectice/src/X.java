/*
 * is this output	1 0 1 0 1 ...
 *			...
 * the only possible output?
 *
 * Falsch: es ist garantiert, in welcher die
 * Threads eintreten.
 */
public class X extends Thread	{
	private String info;
	static Object o = new Object();
	public X (String info) {
		this.info    = info;
	}
	public void run () {
		while ( true )	{
			synchronized ( o ) {
				
				try {
					//
					sleep(1000);
					System.out.println(info);	
					//o.notify();
					//sleep(100);
					o.wait(1);
					
				} catch ( Exception e ) { }
			}
		}
	}
	public static void main (String args []) {
		( new X("0") ).start();
		( new X("1") ).start();
	}
}
