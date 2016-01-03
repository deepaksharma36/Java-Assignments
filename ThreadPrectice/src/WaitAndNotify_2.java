import java.util.Vector;

public class WaitAndNotify_2 extends Thread	{

	private String info;
	static Integer monitor;
	static int count = 0;
	static int max = 0;

	public WaitAndNotify_2 (String info) {
		monitor = new Integer(3);
		System.out.println(monitor);
		this.info = info;
		// max ++;
	}
	
 	public void doTheJob() {
	   synchronized ( monitor )	{
			System.out.println(info + " is waiting");
			count ++;
			try {
				
				sleep(1000000);
			} catch ( Exception  e )	{
				System.out.println(
				  ": IllegalMonitorStateException");
			}


			
			System.out.println(info + " is awake!");
		}
	}


	public void run () {
		doTheJob();
	}

	public static void main (String args []) {
		new WaitAndNotify_2("first").start();
				new WaitAndNotify_2("second").start();
		new WaitAndNotify_2("last").start();
	}
}
