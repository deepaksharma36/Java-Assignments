import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class Processor implements Runnable {

	/**
	 * @param args
	 */
	private int id;
	public Processor(int id){
		this.id=id;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Starting ID"+this.id);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Completed ID"+this.id);
	}

}
public class AppN
{
public static void main(String[] args)	
{
	ExecutorService executor = Executors.newFixedThreadPool(2);
	
	for (int i =0;i<5;i++)
	{
		executor.submit(new Processor(i));
	}
	executor.shutdown();
	System.out.println("All taks submitted ");
	try {
		executor.awaitTermination(1,TimeUnit.DAYS);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("All taks completed");
	}

}