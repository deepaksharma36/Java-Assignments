import java.util.Random;


public class BridgeManager extends Thread {

   private volatile int BridgeWeight=0;
   private final int maxBridgeWeight=200000;
   private int waitingTruckCountLeftSide=0;
   private int waitingTruckCountRightSide=0;
   private Object bridgeLock=new Object();
   private Object waitingLock= new Object();
   private volatile int bridgeLeftCount=0;
   private volatile int bridgeRightCount=0;
   private volatile int servingTokenNumber=1;
   
   
   public void crossthisTruck(Truck aTruck)
   {
	 
	
	   checkTheBridge(aTruck);
	// truck is on the road out side synchronized Block
	try {
		Thread.sleep(new Random().nextInt(200));
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
	synchronized(bridgeLock)
	{
		if(aTruck.getDirection().equals("left"))
		bridgeLeftCount--;
		else
		bridgeRightCount--;
		BridgeWeight-=aTruck.getWeight();
		
	}
	
	System.out.println(aTruck.getWaitingNumber() +"leaving Bridge");	
   }
	
   public int updateWaitingTruck(int side, int num)
   {
	   synchronized(waitingLock)
	   {
		 
		   waitingTruckCountLeftSide+=num;
		   return waitingTruckCountLeftSide;
		   }
		 
		    
	   }
   
   
   public void checkTheBridge(Truck aTruck)
   {   if(aTruck.getDirection().equals("left") && aTruck.getWaitingNumber()==0)
	   aTruck.setWaitingNumber(updateWaitingTruck(1,1));
   else
   {
   if(aTruck.getWaitingNumber()==0)
   aTruck.setWaitingNumber(updateWaitingTruck(2,1));
   }   
   
   System.out.println("For waiting number "+aTruck.getWaitingNumber() +"new truck on the bridge");
	   synchronized(bridgeLock){
		   try{
		   while(aTruck.getWeight()+BridgeWeight>maxBridgeWeight || bridgeLeftCount+bridgeRightCount>=4||aTruck.getWaitingNumber()!=servingTokenNumber)
		   {   //System.out.println("For waiting number "+aTruck.getWaitingNumber() +"Bridge has stopped the flow of trucks");
		   	   bridgeLock.notifyAll();
		       bridgeLock.wait();
		    }
		   
		   BridgeWeight=BridgeWeight+aTruck.getWeight();
		   if(aTruck.getDirection().equals("left"))
		   bridgeLeftCount++;
		   else
		   bridgeRightCount++;
		   System.out.println("Number of Trucks on Bridge "+(bridgeLeftCount+bridgeRightCount)+" Weight of the bridge "+BridgeWeight);
		   servingTokenNumber++;
		   bridgeLock.notifyAll();
		   
		   
		   }
	   catch(Exception e){
	System.out.println("Error:"+e);	   
	   }}
   }
   
	public static void main(String[] args) {
		BridgeManager aBridgeManager= new BridgeManager(); 
	    TruckManger aTruckManger = new TruckManger(aBridgeManager);
	    aTruckManger.start();
	    //aBridgeManager.crossthisTruck(aTruckManger.sendTruck());
	
	
		
	}
			
	

}
