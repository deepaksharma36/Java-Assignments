
public class CandyFactory {
public Object lock1=new Object();
public Object lock2=new Object();
public Object lock3=new Object();
public Object lock4=new Object();	
	/**
	 * @param args

	 */
	public static void main(String[] args) {

CandyFactory aCandyFactory = new CandyFactory();
aCandyFactory.runFactor();
	}
	public void runFactor(){
		CandyProducer aCandyProducer = new CandyProducer(50,lock1);
		WrappingPaperProducer aWrappingPaperProducer= new WrappingPaperProducer(50,lock2);
		ConsumerWrapper aConsumerWrapper = new ConsumerWrapper(50,lock3,aCandyProducer,aWrappingPaperProducer);
		CandyBoxProducer aCandyBoxProducer = new CandyBoxProducer(12, lock4);
		ConsumerBoxFiller aConsumerBoxFiller = new ConsumerBoxFiller(10, aCandyBoxProducer, aConsumerWrapper);
		
		try {
			
		aCandyProducer.start();
		aWrappingPaperProducer.start();
		aCandyBoxProducer.start();
		aConsumerWrapper.start();
		aConsumerBoxFiller.start();
		aConsumerBoxFiller.join();
			aCandyProducer.run=false;
			aWrappingPaperProducer.run=false;
			aCandyBoxProducer.run=false;
			aConsumerWrapper.run=false;
			aConsumerBoxFiller.run=false;
			
		aCandyProducer.interrupt();
		aWrappingPaperProducer.interrupt();
		aCandyBoxProducer.interrupt();
		aConsumerWrapper.interrupt();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}
