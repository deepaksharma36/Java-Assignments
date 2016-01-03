
public class ConsumerBoxFiller extends Thread {
	public volatile int filledBoxes=0;
	public volatile boolean run=true;
	int packedBoxesStorageLimit;
	CandyBoxProducer aCandyBoxProducer;
	ConsumerWrapper aConsumerWrapper;
	public ConsumerBoxFiller(int packedBoxesStorageLimit, CandyBoxProducer aCandyBoxProducer, ConsumerWrapper aConsumerWrapper)
	{


	 	this.aCandyBoxProducer=aCandyBoxProducer;
	 	this.aConsumerWrapper=aConsumerWrapper;
	 	this.packedBoxesStorageLimit=packedBoxesStorageLimit;
	 	
	}
		
	public void run()
	{
	 while(run)
	 {
		try {
			if(filledBoxes>=packedBoxesStorageLimit)
			{System.out.println("Done with the day");
			break;
			}
			aCandyBoxProducer.removeCandyBox(1);
			aConsumerWrapper.removeWrappedCandy(4);
			updatePackedBox();
			
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 System.out.println("*****************Box Filling Machine is Closing**************");
	}
	public void updatePackedBox()
	{

		
		filledBoxes++;
		System.out.println("Box prepared");
		
	}

}
