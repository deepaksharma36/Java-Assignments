import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
class processers implements Runnable{
	private CountDownLatch latch;
	
	public processers(CountDownLatch latch){
		this.latch=latch;
	}
	

	@Override
	public void run() {
      System.out.println("Starting..");
      try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      latch.countDown();
		
	}
	
	
}

public class AppM {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		CountDownLatch latch = new CountDownLatch(3);
		// TODO Auto-generated method stub

		ExecutorService executor =Executors.newFixedThreadPool(3);
		
		for (int i=0;i<3;i++)
		{
			executor.submit(new processers(latch));
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Completed");
	}

}
