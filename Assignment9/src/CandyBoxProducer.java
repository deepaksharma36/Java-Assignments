
/***
 * 
 * @author deepak
 *
 */
public class CandyBoxProducer extends Thread {
	public volatile int candyBox=0;
	public int limitcandyBox;
	Object lock4;
	public volatile boolean run=true;
	public CandyBoxProducer(int limitcandyBox, Object lock4)
	{
		candyBox= 0;
		this.limitcandyBox=limitcandyBox;
	 	this.lock4=lock4;
	}
		
	public void run()
	{
while(run)
{
	
			
			addCandyBox(1);
			
	
}
System.out.println("********Empty Cnady box production closing*****************");
	}
	public void addCandyBox(int num)
	{
		try{
		synchronized (lock4) {
			
		while(candyBox+num >limitcandyBox && run)
			lock4.wait();
		candyBox += num;
		System.out.println("empty Box prepared");
		lock4.notify();
		}
		}
		catch (InterruptedException e) {
		
		}
	}

	public void removeCandyBox(int num)
	{
		
		synchronized (lock4) {
			try{
		
			while(candyBox-num<0 && run)
			{
				lock4.wait();
			}
			candyBox=candyBox-num;
			System.out.println("empty box gone");
			lock4.notify();
			}
		
		
	catch (InterruptedException e) {
			
		
		}
		}
	}


	
	
}
