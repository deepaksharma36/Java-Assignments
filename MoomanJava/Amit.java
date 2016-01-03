/*
*
* Ensures sequence 0,1,0,1,0,1.....
AMIT SHROFF <aas6521@rit.edu>
*/
public class Amit extends Thread   {
    static int count=0;    
	private String info;
        static Object o = new Object();
        public Amit (String info) {
                this.info    = info;
        }
        public void run () {
                while ( true )  {
                	if((count > 0) || (this.info.equals("0"))){
                		count++;
				/*hpb*/if ( count == 0 )
				/*hpb*/		try { sleep(1000); }  catch (Exception e) {}
				synchronized ( o ) {
					if((count > 0) || (this.info.equals("0"))){
						count++;
						System.out.println(info);
						try {
							o.notify();
							sleep(100);
							o.wait();
						} catch ( Exception e ) { }
					}
                          }
                   }
            }
        public static void main (String args []) {
                ( new Amit("0") ).start();
                ( new Amit("1") ).start();
        }
}

