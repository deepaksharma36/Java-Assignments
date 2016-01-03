/*
 * is this output	--->
 *			<--
 *			...
 * the only possible output?
 */
public class T_4 extends Thread    {
    static Object o = new Object();
    String s;
    public T_4(String s)
    {
    	this.s=s;
    	o=new Object();
    }
    public void run () {
        synchronized ( o ) { 
            System.err.println(s+ "   --->");
            try {
                    sleep(1000);
            }
            catch (  InterruptedException e ) {
                System.err.println("Interrupted!   "+s);
            }
            System.err.println(s+ "   <---" );
        }
    }

    public static void main (String args []) {
        //new T_4().start();
        //new T_4().start();
        //new T_4().start();
    	for(int i=0;i<100;i++)
    	{
        new T_4(Integer.toString(i)).start();
        
    	}
    }
}
