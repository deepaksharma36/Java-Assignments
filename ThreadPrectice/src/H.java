public class H extends Thread	{
	static String info = "";
	static Object o = new Object();
	public H (String info) {
		this.info    = info;
	}

	public  void HH()
	{
		}
	
		
	
	public  void  run () {
		
		try {
			while ( true )	{
				System.out.println(info);
				this.notify();
				System.out.println("notify NAHI fut gayi");
				
				this.wait();
				System.out.println("never coming here");
				
			
		   } }catch ( Exception e )	{
			   System.out.println(e);
		   }
		}
	}
	public static void main (String args []) {
		new H("0").start();
		new H("1").start();
		
		
	}
}
