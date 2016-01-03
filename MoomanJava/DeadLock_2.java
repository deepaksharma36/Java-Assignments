public class DeadLock_2 extends Thread    {
    private String info;
    private static String s_1 = new String();;
    private static String s_2 = new String();;
    
    public DeadLock_2(String info)	{
	this.info = info;
    }
 
    private void inProtected_1() {
	System.err.println("inProtected_1: Entering");
	try { 
	       synchronized ( s_1 )       {
			
			synchronized ( s_2 )       {
				new DeadLock_2("2").start();
				System.err.println("inProtected_1: after start");
				s_2.wait();
				System.err.println("inProtected_1: after wait");
			}
			System.err.println("------ inProtected_1: you should see this ");
			synchronized ( s_2 )       {
				System.err.println("------ inProtected_1: after synchronized ( s_2 )");
			}
			System.err.println("inProtected_1");
			
       		}
	} catch (Exception e )	{}
    }
    private void inProtected_2() {
	System.err.println("inProtected_2: Entering");
	try { 
	       synchronized ( s_2 )       {
			System.err.println("inProtected_2: before notify");
			s_2.notifyAll();
			System.err.println("------ inProtected_2: you should see this ");

			System.err.println("------ inProtected_2: you should see this ");
			synchronized ( s_1 )       {
				System.err.println("------ inProtected_2: after synchronized ( s_1 )");
			}
			System.err.println("inProtected_2");
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
	new DeadLock_2("1").start();

    }
}

