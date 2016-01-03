
public class IOReader extends Thread {
	static String inputFileName = "words.txt";
	public static void main(String args[]) {
		System.out.println("Program has started");
		IO aIo = new IO(inputFileName);
			aIo.open();
			while(aIo.reader()!=null)
			{
				try {
					sleep(100);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			

			
}}
