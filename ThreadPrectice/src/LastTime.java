public class LastTime {
    public static void main(String[] args){
        ThreadB b = new ThreadB();
        b.start();
 
        synchronized(b)
        {
                System.out.println("Waiting for b to complete...");
                try {
					b.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            
            }
 
            System.out.println("Total is: " + b.total);
        }
    }

 
class ThreadB extends Thread{
    int total;
    @Override
    public void run(){
        synchronized(this){
            for(int i=0; i<100 ; i++){
                total = i;
            }
            notify();
        }
    }
}