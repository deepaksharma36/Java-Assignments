
public class MyThread implements Runnable {

	private String info;
	int x = 0;

	public MyThread (String info) {
		this.info = info;
		
	}

	public void run () {
		x=1;
		System.out.print(info);
	}

	public static void main (String args []) {
		System.out.println(args[0]);
		if (args != null)	{
			System.out.println("hey");
			for (int n = 0; n < args.length; ++ n)	{
				new Thread( new MyThread(""+n)).start();
			}
		}
	}
}
