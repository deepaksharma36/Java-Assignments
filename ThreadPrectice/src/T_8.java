public class T_8 extends Thread    {
    static String i = null;
    T_8(String i)	{
    	try {
			sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	this.i = i;
    }
    public void run () {
	synchronized ( i )	{
		if ( this.i.equals("1") )
			i = "3";
		else
			i = "4";
	}
    }

    public static void main (String args []) {
        T_8 aT_8 = new T_8("1");
	//aT_8.start();

	System.out.println("aT_8.i = " + aT_8.i );
	System.out.println("aT_8.i = " + aT_8.i );
    }
}