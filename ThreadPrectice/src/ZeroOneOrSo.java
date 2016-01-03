/*
 * is this output	0 1 0 1 0 1 ...
 * the only possible output?
 *
 *
 */
public class ZeroOneOrSo extends Thread	{
	private String info;
	static Object o = new Object();

	public ZeroOneOrSo (String info) {
		this.info    = info;
	}
	public void run () {
		while ( true )	{
			synchronized ( o ) {
				o.notify();
				System.out.println(info);
				try {
					sleep(300);
					o.wait();
				} catch ( Exception e ) { }
			}
		}
	}
	public static void main (String args []) {
		new ZeroOneOrSo("0").start();
		new ZeroOneOrSo("1").start();
	}
}
