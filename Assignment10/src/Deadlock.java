
/**
 * this program creates two threads that always end up in a deadlock
 * 
 * @author Deepak Sharma ds5930
 * @author Sree Lakshmi Kurra sk9040
 *
 */
class Deadlock1 extends Thread {
	Object lock1 = new Object();
	Object lock2 = new Object();
	Deadlock dead = new Deadlock();

	public Deadlock1(Object lock1, Object lock2, Deadlock dead) {
		this.lock1 = lock1;
		this.lock2 = lock2;
		this.dead = dead;
	}

	public void run() {
		dead.deadlock1();
	}
}

class Deadlock2 extends Thread {
	Object lock1 = new Object();
	Object lock2 = new Object();
	Deadlock dead = new Deadlock();

	public Deadlock2(Object lock1, Object lock2, Deadlock dead) {
		this.lock1 = lock1;
		this.lock2 = lock2;
		this.dead = dead;
	}

	public void run() {
		dead.deadlock2();
	}
}

// this class lets both the threads enter the class, and while entering the
// synchronized blocks will result in a deadlock.
public class Deadlock {

	public Object lock1 = new Object();
	public Object lock2 = new Object();

	public void deadlock1() {
		System.out.println(Thread.currentThread().getName() + " Entered the deadloc1 method");
		try {
			synchronized (lock1) {
				{
					System.out.println(Thread.currentThread().getName() + " got lock1");
					synchronized (lock2) {
						System.out.println(Thread.currentThread().getName() + " got lock2");

						createthread2();

						System.out.println(Thread.currentThread().getName()
								+ " created thread 2 and went of wait giving up lock2");
						lock2.wait();
						System.out.println(Thread.currentThread().getName() + " looking for lock2");

						synchronized (lock2) {
							System.out.println(" Giving up lock 2 I have lock 1");
						
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Not wroking 1");
		}
	}

	public void deadlock2() {
		try {
			System.out.println(Thread.currentThread().getName() + " inside deadloc2 method");
			synchronized (lock2) 
				{
					System.out.println(Thread.currentThread().getName() + " got lock2 in side deadloc2");
					
					System.out.println(Thread.currentThread().getName() + "  looking for lock 1");
					lock2.notify();
				
					synchronized (lock1) {
						System.out.println(Thread.currentThread().getName() + " got lock1");
				
					}
				}

				}catch (Exception e) {
			System.out.println("Not wroking");
		}
	}

	// new thread of object deadlock2 is created here.
	public void letHaveAdeadLock() {
		Deadlock1 aDeadlock1 = new Deadlock1(lock1, lock2, this);
		aDeadlock1.setName("Thread1");
		aDeadlock1.start();
	}

	// new thread of object deadlock2 is created here.
	public void createthread2() {
		Deadlock2 aDeadlock2 = new Deadlock2(lock1, lock2, this);
		aDeadlock2.setName("Thread2");
		aDeadlock2.start();
	}

	// objects of class deadlock are created here
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName());
		Deadlock d = new Deadlock();
		d.letHaveAdeadLock();
		
	}

}