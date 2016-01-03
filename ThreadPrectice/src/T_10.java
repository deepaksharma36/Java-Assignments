/*
 * 1000 rennen
 */ 
public class T_10 extends Thread    {
   
    String info; 
    double x = 10;

    public T_10(String info )    {
        this.info = info;
    }     

    public void run () {
        for ( int index = 1; index < 1000000000; index ++ )
		x  = Math.log( (double) index);
		
    }
    public static void main (String args []) {
	for ( int index = 1; index < 1000; index ++ )	{
		new T_10("i").start();
    }
   }
}
