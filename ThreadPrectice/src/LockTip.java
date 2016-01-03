import java.awt.List;
import java.util.ArrayList;


public class LockTip extends Thread {
    private ArrayList longs = new ArrayList();

    public static void main(String args[]) {
        LockTip lt = new LockTip();
        lt.start();

        new LongSupplier(lt).start();
    }

    public void run() {
        //synchronized (longs) {
			
		
    	while (true) {
            try {
            	//notify();
                wait();

                // do something with longs
                System.out.println("doing something: " + this.longs);
            }
            catch (InterruptedException e) {
            	System.out.println("Is not working");
            }
        }
        
    }
    public void addLong(Long l) {
        this.longs.add(l);
        notifyAll();
   }
}



class LongSupplier extends Thread {
    private LockTip lt;

    public LongSupplier(LockTip lt) {
        this.lt = lt;
    }


    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                this.lt.addLong(new Long(123));
            }
            catch (InterruptedException e) {}
        }
    }
} 