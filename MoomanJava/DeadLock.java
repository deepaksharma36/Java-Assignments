public class DeadLock extends Thread    {
    private String info;
    private static String s_1 = new String();;
    private static String s_2 = new String();;
    
    public DeadLock(String info)	{
	this.info = info;
    }
 
    private void inProtected_1() {
	System.err.println("inProtected_1: Entering");
	try { 
	       synchronized ( s_1 )       {
			
			synchronized ( s_2 )       {
				System.err.println("inProtected_1: before notify");
				s_1.notify();
				System.err.println("inProtected_1: after notify");
				s_2.wait();
			}
			System.err.println("inProtected_1");
			
		        synchronized ( s_2 )       {
			}
		
       		}
	} catch (Exception e )	{}
    }
    private void inProtected_2() {
	System.err.println("inProtected_2: Entering");
	try { 
	       synchronized ( s_2 )       {
			
			synchronized ( s_1 )       {
				System.err.println("inProtected_2: before notify");
				s_2.notify();
				System.err.println("inProtected_2: after notify");
				s_1.wait();
			}
			System.err.println("inProtected_2");
			
		        synchronized ( s_1 )       {
			}
	       }
	} catch (Exception e )	{}
    }
    
    public void run () {
	if ( info.equals("1") )
		inProtected_1();
	else
		inProtected_2();
    }
    public static void main (String args []) {
	new DeadLock("1").start();
        new DeadLock("2").start();

    }
}

