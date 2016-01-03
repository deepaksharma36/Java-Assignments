public class ZeroOne extends Thread {
	private String info;
	static Object o = new Object();
	volatile static int oneIsRunning = 1; volatile static int runningThread = 1;

	public ZeroOne(String info) {
		this.info = info;
	}

	public void run() {
		while (true) {
			synchronized (o) {
				try {
					// o.notifyAll();
					 //System.out.println((runningThread-1)+"upper");
			
					while (!this.info.equals(""+(runningThread-1)))
					{
						o.notify();
						o.wait();
					}
					
					System.out.println(this.info);
					

					if (oneIsRunning < 4) {
						ZeroOne x = new ZeroOne(Integer.toString(oneIsRunning++));
						//x.setName("Thread-" + ++oneIsRunning);
						x.start();
						runningThread=oneIsRunning;
					}
					else if(runningThread==4){
						//System.out.println("No idea"+runningThread);
					    runningThread=1;
					    o.notify();
					    
					    //System.out.println("No idea"+runningThread);
					    
					 }
					else
					{
						runningThread++;
						//System.out.println(runningThread+ "bigad is doing this one"+this.info);
						o.notify();
						
					}
					//System.out.println(""+(runningThread)%2);
					
					sleep(300);
					o.wait();
					//o.notifyAll();
					
				} catch (Exception e) {
				}
			}
		}
	}

	public static void main(String args[]) {
		
		new ZeroOne("0").start();
	}
}
