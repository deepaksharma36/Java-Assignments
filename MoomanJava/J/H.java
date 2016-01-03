public class H extends Thread	{
	String info = "";
	public H (String info) {
		this.info    = info;
	}
	public synchronized void run () {
	   try {

		while ( true )	{
			System.out.println(info);
			notify();
			wait();
		}
	   } catch ( Exception e )	{}
	}
	public static void main (String args []) {
		new H("0").start();
		new H("1").start();
	}
}
