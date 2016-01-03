import java.util.Random;


public class TruckManger extends Thread {

private Object truckLock;

private BridgeManager aBridgeManager;
Random rand = new Random();
public  TruckManger(BridgeManager aBridgeManager)
{
	this.aBridgeManager=aBridgeManager;
}

public Truck sendTruck()
{int minWeight=100;
int maxWeight = 100000;
		
String[] direction={"left","right"};	
return new Truck(rand.nextInt(maxWeight-minWeight)+minWeight,direction[rand.nextInt(1)],aBridgeManager );	
}

public void run()
{
	
while(true){
	try{

	
		sendTruck().start();
		
		sleep(rand.nextInt(80));
		
	
	}
	catch(Exception ex)
	{
		
	}
		
	}
}
	
}


