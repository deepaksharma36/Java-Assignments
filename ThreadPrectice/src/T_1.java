/*
 * is this output	--->
 *			<--
 *			...
 * the only possible output?
 */
public class T_1 extends Thread    {
    public static Object obj=new Object();
    public String info;
    public T_1(String info){
		this.info=info;
	}
    private synchronized  void inProtected () {
    //   synchronized (obj) {
	
	
    	System.err.println("--> "+ info);
       try {
              sleep(1000);
       }
       catch (  InterruptedException e ) {
                 System.err.println("Interrupted!");
       }
       System.err.println("<-- "+ info);
       }
   // }

    public void run () {
        inProtected();
    }
    public static void main (String args []) {
        new T_1("1").start();
        new T_1("2").start();
        new T_1("3").start();
    }
}
