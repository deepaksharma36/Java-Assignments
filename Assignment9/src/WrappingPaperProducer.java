
public class WrappingPaperProducer extends Thread {
	public volatile int wrappingPaper=0;
	public int WrappingPaperLimit;
	Object lock2;
	public volatile boolean run=true;
	public WrappingPaperProducer(int WrappingPaperLimit, Object lock2)
	{
		wrappingPaper= 0;
	 	
	 	this.lock2=lock2;
	 	this.WrappingPaperLimit=WrappingPaperLimit;
	}
		
	public void run()
	{
while(run){
			addWrappingPaper(3);
			
	}
     System.out.println("**********************wrapping paper production is closing****************");
	}
	public void addWrappingPaper(int num)
	{
		try{
		synchronized (lock2) {
			
		while(wrappingPaper+num > WrappingPaperLimit && run)
			lock2.wait();
		wrappingPaper += num;
		System.out.println("new wrapping paprpe got"+ wrappingPaper);
		lock2.notify();
		}
		}
		catch (InterruptedException e) {
			//e.printStackTrace();
		}
	}

	public void removeWrappingPaper(int num)
	{
		
		synchronized (lock2) {
			try{
		
			while(wrappingPaper-num<0 && run)
			{
				lock2.wait();
			}
			wrappingPaper=wrappingPaper-num;
			System.out.println("wrapping paprpe gone"+ wrappingPaper);
			lock2.notify();
			}
		
		
	catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		}
	}

}
