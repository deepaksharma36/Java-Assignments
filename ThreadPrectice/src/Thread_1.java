
public class Thread_1 extends Thread	{

	private String info;
	static int x = 0;

	public Thread_1 (String info) {
		this.info = info;
	}

	public void run () {
		if ( info == "1" )
			x = 3;
		else
			x = 1;
		System.out.println(x+"Fck");
	}

	public static void main (String args []) {
		Thread_1 aT1  = new Thread_1("1");
		Thread_1 aT2  = new Thread_1("2");
		aT1.start();
		aT2.start();
		System.out.println(x);
	}
}
