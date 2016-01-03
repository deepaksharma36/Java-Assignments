/*
 * alles ist moeglich
 */
public class T_55 extends Thread    {
    String i = "2";
    static String  theValue ;
    T_55(String i)	{
	this.i = i;
    }
    public void run () {
	if ( this.i.equals("1") )
		theValue = "3";
	else
		theValue = "4";
    }

    public static void main (String args []) {
        T_55 aT_5_a = new T_55("1");
        T_55 aT_5_b = new T_55("1");

        aT_5_a.start();
        aT_5_a.run();

	System.out.println("aT_5.i = " + aT_5_a.theValue );
	System.out.println("aT_5.i + " + aT_5_a.theValue );
    }
}
