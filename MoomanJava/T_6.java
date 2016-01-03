public class T_6 extends Thread    {
    String i = "2";
    static String  theValue ;
    T_6(String i)	{
	this.i = i;
    }
    public void run () {
	if ( this.i.equals("1") )
		theValue = "3";
	else
		theValue = "4";
    }

/*
 * falls run nichts passiert in paralell
 */
    public static void main (String args []) {
        T_6 aT_6_1 = new T_6("1");
        T_6 aT_6_2 = new T_6("1");
	aT_6_1.start();
	aT_6_2.start();
	System.out.println("the value = " + theValue );
    }
}
