public class Anusha extends Thread {
   private String info;
   static int index=0;
   static int index2=0;
   int temp;
   static Object[] oArray = new Object[9];
   static boolean allAreRunning = false;

   static {
      for(int i = 0; i <= 4; i++) {
         oArray[i] = new Object();
      }
   }
   public Anusha (String info) {
      this.info    = info;
   }

   public void run () {
 	  //System.out.println("Is it running  " + info);
	   while ( true ) {

    	   System.out.println(info+"output"); 
            try {
            	
               synchronized (oArray) {
                   temp=index2;   
                   //System.out.println(temp+"why");
            	   if(index==3)
            	   {   
            		   index2=(index2+1)%4;
            		   System.out.println("Would like to notify"+index2);
            		   oArray[index2].notify();
            	         index2++;
            	         index=4;
            	   }
            	   else if(index==4)
            	   {
            		   index2=(index2)%4;
            		   oArray[index2].notify();
          	         index2++;
            	   }

                  
                  // next index
                  //System.out.print(info);
                  if ( ! allAreRunning )   {
                     ( new Anusha(++index + "") ).start();
                     //System.out.println("Thread created"+ index + "and index2" + index++);
                     if(index == 3) allAreRunning = true;
                     
                    
                     index2++;
                    
                  }
                  sleep(300);
                  //System.out.println(temp +" is going on wait");
                  oArray[temp].wait();
                 // System.out.println("I am comming back"+temp);
               } // sync inner 
            } catch ( Exception e ) { 
            	e.printStackTrace();
            	try {
					sleep(2000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
            }
         } // sync outer
      }
   //}

   public static void main (String args []) {
      System.out.println("wtf");
	   new Anusha("0").start();
   }
}