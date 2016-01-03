/*
 * is this output	--->
 *			<--
 *			...
 * the only possible output?
 * ja ein objekt
 */
public class T_22 extends Thread    {
    private static String info;

    public T_22(String info )	{
	this.info = info;
    }
    private void inProtected () {
       synchronized ( info )       {
             System.err.println("--> " + info);
             try { 
                      sleep(1000);
             } catch (  Exception e ) {
                 e.printStackTrace();
             }
             System.err.println("<-- " + info);
       }
    }

    public void run () {
        inProtected();
    }
    public static void main (String args []) {
        T_22 one = new T_22("a");
	one.start();
	try {
		sleep(100);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        T_22 two = new T_22("b");
	two.start();
        // new T_2("a").start();
        // new T_2("b").start();

    }
}
