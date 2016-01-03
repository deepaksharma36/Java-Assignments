public class Evaluator extends Thread {
	public void run(){
                System.out.println("---->");
 //               try { sleep(1000000); } catch (  InterruptedException e ) { }
                System.out.println("<----");
        }
	public void multiply(){
		for(int i = 0; i < 10; i++){
			for(int j =0; j < 10; j++){
				// Evaluator et = new Evaluator();
				Thread et = new Evaluator();
				et.start();
/*
				try{
					et.join();
				}
				catch(InterruptedException e){
					System.out.println("Interrupted!");
				}
*/
			}
		}
	}
	public static void main(String[] args) {
		Evaluator eval = new Evaluator();
		eval.multiply();
	}
}
