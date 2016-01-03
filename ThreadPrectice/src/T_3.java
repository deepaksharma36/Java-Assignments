/*
 * is this output	--->
 *			<--
 *			...
 * the only possible output?
 */
public class T_3 extends Thread    {
    private  int info;
    public  Object obj = new Object(); 
    public T_3 (int info,Object obj) {
        this.info    = info;
        this.obj=obj;
    }

    public  void run () {
    	synchronized(obj){
        System.err.println("--> " + info);
         try { 
                  sleep(1);
         } catch (  Exception e ) {
                e.printStackTrace();
         }
         System.err.println("<-- " + info);
    	}
    }

    public static void main (String args []) {
          Object obj = new Object();
    	for ( int i = 1; i < 10; i ++ )
                new T_3(i,obj).start();
    }
}
