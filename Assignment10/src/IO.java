
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class IO extends Thread {
	final int MAX = 1024;
	static Object Lock = new Object();
	byte buffer[] = new byte[1024];
	static boolean run = true;
	IO aIoThread1;
	static String inputFileName;
	static MyQueue myQueue= new MyQueue();
	static BufferedInputStream input;

	IO(String inputFileName) {
		IO.inputFileName = inputFileName;
		try {
			input= new BufferedInputStream(
					new FileInputStream(inputFileName), 1024);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	IO() {

	}

	public void writer() {

		try {
			synchronized (Lock) {
				if (input.read(buffer, 0, 1024) == -1) {
					myQueue.push(null);
					close();
				}
				Lock.notify();
				if (myQueue.getSize() < 10) {
					myQueue.push(buffer);
					System.out.println("Data pushed in the queue now size is"
							+ myQueue.getSize());
				} else {
					Lock.wait();
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public byte[] reader() {

		try {
			synchronized (Lock) {

				Lock.notify();
				if (myQueue.getSize() > 0) {
					System.out.println("Data pulled from the queue now size is"
							+ myQueue.getSize());
					return myQueue.pop();

				} else {
					System.out.println("Reader going for wait No deta found");
					Lock.wait();
					System.out.println("Reader got notification executing now");
					return myQueue.pop();
				}

			}

		}

		catch (Exception e) {
			return null;
		}
	}

	public void run() {
		while (run) {
			// System.out.println("Run method started");
//			try {
//				sleep(0);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			writer();
		}
	}

	public void open() {
		IO aIoThread1 = new IO();
		// IO aIoThread2 = new IO();
		aIoThread1.start();
		// aIoThread2.start();
		System.out.println("Writer Thread has started running");
	}

	public void close() {
		System.out.println("End of file has reached");
		run = false;
		try {
			aIoThread1.interrupt();

		} catch (Exception e) {

		}
		/*
		 * try{ aIoThread2.interrupt(); } catch(Exception e) {
		 * 
		 * }
		 */
	}

}