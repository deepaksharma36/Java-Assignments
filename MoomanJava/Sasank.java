public class XXX extends Thread {
        private String info;
        static Object o = new Object();
        static int count = 0;

        public XXX (String info) {
                this.info    = info;
                synchronized ( o ) {                 
                }
        }
        public void run () {

                while ( true )  {
                        synchronized ( o ) {
                        	if ( info.equals("0") && count == 0){
                        		( new XXX("1") ).start();
					count++;
                        	}
                                System.err.print(info);
                                try {
                                        o.notify();
                                       sleep(1000);
                                        o.wait();
                                } catch ( Exception e ) { }
                        }
                }
        }
        public static void main (String args []) {
                new XXX("0").start();
        }
}
