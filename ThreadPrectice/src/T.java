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
	public int i=1000000000;
	static Object o = new Object();
	public T (String info) {
		this.info    = info;
	}
	public void run () {
		synchronized ( o ) {
			
			while ( true )	{
				System.out.println(Thread.currentThread().getName());
				try {
					
					//sleep(100);
					o.notify();
					System.out.println(info);
					while(i-->0);
					i=1000000000;
					o.wait();
					sleep(1000);
				} catch ( Exception e ) { }
			}
		}
	}
	public static void main (String args []) {
		 Thread x=new T("0"); //.start();
		Thread y= new T("1");// ).start();
		x.setPriority(MIN_PRIORITY);
		y.setPriority(MAX_PRIORITY);
		x.start();
		
		y.start();
		
		
	}
}
