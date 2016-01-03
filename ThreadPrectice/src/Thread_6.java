import java.util.*;

public class Thread_6 extends Thread    {
    private String info;
    private Vector aVector;

    public Thread_6 (String info, Vector aVector) {
        this.info    = info;
        this.aVector = aVector;
    }

    private void inProtected_1 () {
	synchronized ( aVector )	{
		System.err.println("1: " + info + ": is inProtected_1 ");
		try {
			sleep(1);
		}
		catch (  InterruptedException e ) {
		    System.err.println("Interrupted!");
		}
		System.err.println("1: " + info + ": exit fro inProtected_1");
	}
    }

    private void inProtected_2 () {
	synchronized ( info )	{
		System.err.println("2: " + info + ": is IN inProtected_2 ");
		try {
			sleep(1);
		}
		catch (  InterruptedException e ) {
		    System.err.println("Interrupted!");
		}
		System.err.println("2: " + info + ": EXIT from inProtected_2");
	}
    }

    private static void inProtected_3 () {
	System.err.println("3: INinProtected_3 ");
	try {
		sleep(1);
	}
	catch (  InterruptedException e ) {
	    System.err.println("Interrupted!");
	}
	System.err.println("3: EXIT inProtected_3");
    }

    public void run () {
        inProtected_1();
        inProtected_2();
        Thread_6.inProtected_3();
    }

    public static void main (String args []) {
        Vector aVector = new Vector();
        Thread_6 aT6_0 = new Thread_6("first",  aVector);
        Thread_6 aT6_1 = new Thread_6("second", aVector);

        aT6_0.start();
        aT6_1.start();
    }
}
