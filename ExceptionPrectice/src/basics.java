import java.util.concurrent.TimeUnit;


public class basics {

	public static void timepass() throws ArithmeticException, NullPointerException , ArrayIndexOutOfBoundsException 
	{
       try{	
		int x=0;
		int y = x/0;
		System.out.println("I never reached here ");
       }
       catch(Exception e)
       {
    	   System.out.println("shit shit");
       }
		
	}
	
	public static void timetime()
	{
		timepass();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		try{
   
			timepass();
			
		}
		
		catch(ArithmeticException ar)
		{
			System.out.println("Shit happend arthmaics");
		}
		
		catch(NullPointerException x)
		{
			System.out.println("Shit happend");
		}
		catch(Exception e)
		{
			System.out.println("Shit happend  and i can take care of all shits");
		}
		
		System.out.print("hey i will not get printed");

	
	}

}
