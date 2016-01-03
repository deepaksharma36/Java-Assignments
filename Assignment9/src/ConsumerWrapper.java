
public class ConsumerWrapper extends Thread{


	public volatile int wrappedCandy=0;
	Object lock3;
	int wrappedCandyStorageLimit;
	CandyProducer aCandyProducer;
	WrappingPaperProducer aWrappingPaperProducer;
	public volatile  boolean run=true;
	public ConsumerWrapper(int wrappedCandyStorageLimit,Object lock3, CandyProducer aCandyProducer, WrappingPaperProducer aWrappingPaperProducer)
	{

	 	this.lock3=lock3;
	 	this.wrappedCandyStorageLimit=wrappedCandyStorageLimit;
	 	this.aWrappingPaperProducer=aWrappingPaperProducer;
	 	this.aCandyProducer=aCandyProducer;
	 	
	}
		
	public void run()
	{
	while(run){
	
				
			addWrappedCandy(1);
		
		
	}
	System.out.println("***********************Candy wraping machine is closing******************");
	
	}
	public void addWrappedCandy(int num)
	{
		try{
		synchronized (lock3) {

			
		while(wrappedCandy+num >=wrappedCandyStorageLimit && run)
		{	if(run)
			lock3.wait();
			else    
		    break;}
		aCandyProducer.removeCandy(1);
		aWrappingPaperProducer.removeWrappingPaper(1);
		wrappedCandy += num;
		lock3.notify();
		}
		}
		catch (InterruptedException e) {
			
		}
	}

	public void removeWrappedCandy(int num)
	{
		
		synchronized (lock3) {
			try{
		
			while(wrappedCandy-num<0 && run)
			{
				if(run)
				lock3.wait();
				else
					break;
			}
			wrappedCandy=wrappedCandy-num;
			lock3.notify();
			}
		
		
	catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		}
	}

}
