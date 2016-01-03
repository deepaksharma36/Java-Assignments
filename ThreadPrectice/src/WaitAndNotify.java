import java.util.Vector;

public class WaitAndNotify extends Thread	{

	private String info;
	static  Vector aVector = new Vector();

	public WaitAndNotify (String info, Vector aVector) {
		this.info = info;
		this.aVector = aVector;
	}
	
 	public void doTheJob() {
	   synchronized ( aVector )	{
		if (  info.equals("last") )	{
			System.out.println(info + " is waking up ...");
			aVector.notifyAll();
			System.out.println(info + " done.");
		} else {
			System.out.println(info + " is waiting");
			try {
				aVector.wait();
			} catch ( IllegalMonitorStateException  e )	{
				System.out.println(info +
				  ": IllegalMonitorStateException");
			} catch ( InterruptedException  e )	{
				System.out.println(info +
				  ": InterruptedException");
			}
			System.out.println(info + " is awake!");
		}
	  }
	}


	public void run () {
		doTheJob();
	}

	public static void main (String args []) {
		new WaitAndNotify("first", aVector).start();
		new WaitAndNotify("second", aVector).start();
		try {
			sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new WaitAndNotify("last", aVector).start();
	}
}
