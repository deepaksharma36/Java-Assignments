/*
 * is this output	--->
 *			<--
 *			...
 * the only possible output?
 */
public class T_2 extends Thread {
	private static String info;
	private static Integer x = new Integer(2);
   
	public T_2(String info, int x) {
		this.info = info;//new String(info);
		//this.x = x;
	}

	private void inProtected() {
		synchronized (x) {
			System.out.println(info);
			System.err.println("--> " + info);
			try {
				sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.err.println("<-- " + info);
		}
	}

	public void run() {
		inProtected();
	}

	public static void main(String args[]) {
		String a = "hello";
		String b = "hola";
		String c = "Bonjour";
		String d = "Hallo";
		Thread x= new T_2(a,2);//.start();
		Thread y= new T_2(a,2);
		Thread z= new T_2(a,2);
		Thread w= new T_2(a,2);
		Thread v= new T_2(a,2);
		x.start();
		y.start();
		z.start();
		w.start();
		v.start();
		
	}
}
