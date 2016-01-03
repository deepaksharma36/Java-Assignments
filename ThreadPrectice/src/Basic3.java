import java.util.ArrayList;

import org.omg.PortableInterceptor.Interceptor;

public class Basic3 {

	/**
	 * @param args
	 */
	private static int count = 0;
	ArrayList<String> ary1 = new ArrayList<String>();
	ArrayList<String> ary2 = new ArrayList<String>();
	Object obj = new Object();
	Object obj1 =new Object();

	public static synchronized void increase() {
		count++;
	}
	
	public void process() 
	{
				
	}

	public  void main()  {
		
		
		long time= System.currentTimeMillis();
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				both();		
				
			}
			
		});
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				both();		
				
			}
			
		});

		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		long time2= System.currentTimeMillis();
		System.out.println(time2-time);
		
		// TODO Auto-generated method stub
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++)
					increase();

			}

		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++)
					increase();

			}

		});

		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(count);

	}

	public void both()
	{
		for(int i=0;i<1000;i++)
		{
			Add1();
			Add2();
		}
	}
	public  void Add1() {
		synchronized(obj){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		ary1.add("Hello");}

	}

	public    void Add2() {
		synchronized(obj1){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		ary2.add("Hello");}

	}
	
	
	public static void main(String[] args)
	{
		Basic3 b = new Basic3();
		b.main();
		
	}

}
