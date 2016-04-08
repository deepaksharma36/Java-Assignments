import java.util.ArrayList;
import java.util.HashMap;


public class Snapshot {

	HashMap<Integer,ChannelSnapShot> ChannelAmount;
	public Snapshot(int nodeAmount) {
		super();
		this.nodeAmount = nodeAmount;
		this.ChannelAmount = new HashMap<Integer,ChannelSnapShot>(); 
	}


	int nodeAmount;
	
	public int getNodeAmount() {
		return nodeAmount;
	}
	public void setNodeAmount(int nodeAmount) {
		this.nodeAmount = nodeAmount;
	}
	
	public void addChannelSnapShot(int processID,ChannelSnapShot ch)
	{
		 
		ChannelAmount.put(processID, ch);
	}
	
	public String toString()
	{   String channelOutput="";
		for( int processID: ChannelAmount.keySet())
			channelOutput+="\n\t"+ChannelAmount.get(processID).toString();
		return ""+nodeAmount+channelOutput;
	}
	
	public HashMap<Integer,ChannelSnapShot> getChannelAmount() {
		return ChannelAmount;
	}

}
