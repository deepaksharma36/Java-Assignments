/*
 * alles ist moeglich
 */
public class T_5 extends Thread    {
    String i = "2";
    static String  theValue ;
    T_5(String i)	{
	this.i = i;
    }
    public void run () {
	if ( this.i.equals("1") )
		i = "3";
	else
		i = "4";
    }

    public static void main (String args []) {
        T_5 aT_5_a = new T_5("1");
        //T_5 aT_5_b = new T_5("1");

        aT_5_a.start();
        aT_5_a.run();
	System.out.println("aT_5_a.i = " + aT_5_a.i );
	System.out.println(aT_5_a.i + " = " + aT_5_a.i );
    }
}
