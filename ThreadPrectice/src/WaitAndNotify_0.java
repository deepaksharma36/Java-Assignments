import java.util.Vector;

public class WaitAndNotify_0 extends Thread	{

	private static int counter = 0;
	static  Vector aVector = new Vector();

	public WaitAndNotify_0 (String name, Vector aVector) {
		this.aVector = aVector;
		this.setName(name);

	}
	
 	public void doTheJob() {
	   synchronized ( aVector )	{
		counter ++;
		if (  counter == 3  )	{
			System.out.println(getName() + " is waking up ...");
			aVector.notify();
			System.out.println(getName() + " done.");
		} else {
			System.out.println(getName() + " will wait ...");
			try {
				aVector.wait();
			} catch ( IllegalMonitorStateException  e )	{
				System.out.println( ": IllegalMonitorStateException");
			} catch ( InterruptedException  e )	{
				System.out.println(": InterruptedException");
			}
			System.out.println(getName() + " is awake!");
		}
	  }
	}


	public void run () {
		doTheJob();
	}

	public static void main (String args []) {
		new WaitAndNotify_0("one", aVector).start();
		new WaitAndNotify_0("two", aVector).start();
		new WaitAndNotify_0("three", aVector).start();
	}
}
