/**
 * this class Implements a parallel version of the sieve of Eratosthenes
 * algorithm using threads.
 * 
 * @author deepak sharma ds5930
 * @author sree lakshmi kurra sk9040
 * 
 */
public class SieveOfEratosthenes implements Runnable {

	final static int FIRSTpRIMEuSED = 2;
	static int MAX;
	final boolean[] numbers;
	public static volatile int count = 0;
	public static volatile int startCount = 0;
	public static int limit;
	public Object obj = new Object();
	public static int maxNoThread = 0;

	/**
	 * implementing run method of the runnable interface. invoke
	 * determinePrimeNumbers2 method for each multiplier. Also keep a track of
	 * new and the finished threads using startCount and count.
	 * 
	 */
	public void run() {
		synchronized (obj) {
			startCount++;
		}
		determinePrimeNumbers2(Integer.parseInt(Thread.currentThread()
				.getName()));
		synchronized (this) {
			count++;
		}

	}

	/**
	 * 
	 * @param max
	 *            is the maximum number in the number list, number list provided
	 *            for determining prime numbers.
	 */
	public SieveOfEratosthenes(int max) {
		numbers = new boolean[max];
		this.MAX = max;

	}

	/**
	 * This method creates one new thread for each multiplier in
	 * SieveOfEratosthenes algorithm.
	 */
	public void determinePrimeNumbers() {
		for (int index = 1; index < MAX; index++) {
			numbers[index] = true;

		}
		limit = (MAX > 10 ? (int) Math.sqrt(MAX) + 1 : 3);

		for (int index = 2; index < limit; index++) {
			while (startCount - count > maxNoThread) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Thread x = new Thread(this);
			x.setName(Integer.toString(index));
			x.start();

		}

	}

	/**
	 * this method determines the non prime numbers by multiplying counter to
	 * the multiplier of the numbers. this method is invoked by the run method
	 * of this class for parallel processing of each multiplier in the
	 * SieveOfEratosthenes algorithm.
	 * 
	 * @param index
	 *            is the multiplier.
	 */
	public void determinePrimeNumbers2(int index) {

		// this is the part for the parallel part
		if (numbers[index]) { // this is the part for the parallel part
			int counter = 2; // this is the part for the parallel part
			while (index * counter < MAX) {
				// System.out.println(index * counter);// this is the part for
				// the parallel part
				numbers[index * counter] = false; // this is the part for the
													// parallel part
				counter++; // this is the part for the parallel part
			} // this is the part for the parallel part
		}

	}

	/**
	 * tests the numbers in the list for prime numbers using numbers array
	 * created while the execution of determine prime number method.
	 */
	public void testForPrimeNumber() {
		int[] test = { 2, 3, 4, 7, 13, 17, MAX - 1, MAX };
		for (int index = 0; index < test.length; index++) {
			if (test[index] < MAX) {
				System.out.println(test[index] + " = " + numbers[test[index]]);
			}
		}
	}

	/**
	 * this main method invoke various methods required to determine prime
	 * numbers in a given list by creating object of SieveOfEratosthenes.
	 * 
	 * @param args
	 *            [0] maximum number of possible active threads indicated by the
	 *            user.
	 */
	public static void main(String[] args) {
		maxNoThread = Integer.parseInt(args[0]);
		SieveOfEratosthenes aSieveOfEratosthenes = new SieveOfEratosthenes(20);
		aSieveOfEratosthenes.determinePrimeNumbers();
		while (count < limit - 2) {
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		aSieveOfEratosthenes.testForPrimeNumber();
		System.exit(0);
	}
}
