
class Truck extends Thread {

	private int weight;
	private String direction;
	private BridgeManager aBridgeManager;

	public String getDirection() {
		return direction;
	}

	private int waitingNumber;

	public int getWaitingNumber() {
		return waitingNumber;
	}

	public void setWaitingNumber(int waitingNumber) {
		this.waitingNumber = waitingNumber;
	}

	public int getWeight() {
		return weight;
	}

	public Truck(int weight, String direction,BridgeManager aBridgeManager ) {
		this.weight = weight;
		this.direction = direction;
		this.aBridgeManager=aBridgeManager;
		}
	
    public void run()
    {
    	
    	aBridgeManager.crossthisTruck(this);
    }
}