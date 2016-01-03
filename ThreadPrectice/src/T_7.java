public class T_7 extends Thread    {
    String i = "2";
    static String  theValue="1";
    T_7(String i)	{
	this.i = i;
    }
    public void run () {
	synchronized ( theValue )	{
		try {
			System.out.println("Main gona wait for me I got the lock if main is not done yet");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ( this.i.equals("1") )
			theValue = "3";
		else
			theValue = "4";
		
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("I am done");
    }
    }
    public static void main (String args []) {
        T_7 aT_7_1 = new T_7("1");
        T_7 aT_7_2 = new T_7("1");
	aT_7_1.start();
	aT_7_2.start();

	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


/* drei threads, es haengt davon up, wann der main
 * thread durch den Block geht
 */
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	/*synchronized ( theValue )	{
		System.out.println("theValue main is here= " + theValue );
	}*/
    }
}
