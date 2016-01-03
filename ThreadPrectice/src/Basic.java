import java.util.Scanner;


class Basic extends Thread {
    private volatile 
    
    boolean running=true;
	@Override
	
	public void run() {
		System.out.println("hello loop started");
		for(int i=0;i<100000;i++)
		{
			
		}	
		System.out.println("loop over");
		
	}

	public void shutDown()
	{
		running=false;
	}
	
	public static void main(String[] args)
	{
		Basic t1 = new Basic();
		
		
		t1.start();
		/*try {
			//t1.join();
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		System.out.println("I will come last");
		
		
	}

}



