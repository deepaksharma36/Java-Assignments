/*
 * is this output	--->
 *			<--
 *			...
 * the only possible output?
 * nur ein o
 */
public class T_4a extends Thread    {
   
    static Object o = new Object();
    String info;

    public T_4a(String info )    {
        this.info = info;
    }     

    public void run () {
        synchronized ( o ) { 
            System.err.println("--->" + info);
            try {
                    sleep(1000);
            }
            catch (  InterruptedException e ) {
                System.err.println("Interrupted!");
            }
            System.err.println("<---" + info);
        }
    }

    public static void main (String args []) {
        new T_4a("1").start();
        new T_4a("2").start();
        new T_4a("3").start();
    }
}
