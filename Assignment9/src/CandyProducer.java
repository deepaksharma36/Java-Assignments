
public class CandyProducer extends Thread {
public volatile int candy=0;
public int candyLimit;
public volatile boolean run=true;
Object lock1;
public CandyProducer(int candyLimit, Object lock1)
{
 	candy= 0;
 	this.lock1=lock1;
 	this.candyLimit=candyLimit;
}
	
public void run()
{
while(run)
{
		
		addCandy(1);
		
		
}
System.out.println("********************candy Procution is closing************************************");

}

public void addCandy(int num)
{
	try{
	synchronized (lock1) {
	
	while(candy+num>=candyLimit && run)
	{
		
		lock1.wait();
	}
	candy=candy+num;
	System.out.println("got new candy"+candy);
	lock1.notify();
	}}
	catch (InterruptedException e) {
		
		//e.printStackTrace();
	}
}

public void removeCandy(int num)
{
	
	synchronized (lock1) {
		try{
	
		while(candy-num<0 && run)
		{
			lock1.wait();
		}
		candy=candy-num;
		System.out.println("candy gone"+candy);
		lock1.notify();
		}
	
	
catch (InterruptedException e) {
		
		e.printStackTrace();
	}
	}
}

}

