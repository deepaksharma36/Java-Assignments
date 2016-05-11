import java.util.BitSet;
import java.util.HashMap;

class Observer  {
	
	 
	private GUI torrentGUI = new GUI();
	private Client Client;
	public HashMap<String,FetchPieces> inProgress;
 	
	
	Observer(Client Client){
		this.Client=Client;
		
	}
	
	public int totalReceivedPackets(byte[] peiceInfo){
		   BitSet bitset = BitSet.valueOf(peiceInfo);  
		    return bitset.cardinality();
	}
	
	/*public void receivedPeice(int packetNumber){
		this.peiceInfo[packetNumber/8]=(byte)(this.peiceInfo[packetNumber/8]| 1<< packetNumber%8-1);
		
	}*/
	
	@SuppressWarnings("unchecked")
	public void getMap(){
		inProgress=(HashMap<String,FetchPieces>)Client.inProgress.clone();
	}
	
	public void updateGUI()
	{	
		int id;
		byte[] peices;
		long totalRecPacket;
		long totalPack;
		HashMap<Integer,Peer> peerMap;
		Object data[][] = new Object[inProgress.size()][3] ;
		int counter=0;
		for(String file:inProgress.keySet())
		{
			id =inProgress.get(file).getSelfId();
			totalPack=inProgress.get(file).getMD().getNoOfPieces();
			peerMap=inProgress.get(file).getPeerMap();
			peices=peerMap.get(id).getPieceInfo();
			totalRecPacket=totalReceivedPackets(peices);
			data[counter][1]=file;
			data[counter][2]=totalRecPacket;
			data[counter++][3]=peerMap.size();
			
		}
		if(inProgress.size()>0)
			torrentGUI.inform(data);
	}
	
	private void guiUpdatetor(){
		Thread guiUpdatetor = new Thread(new Runnable() 
		{
			public void run() {
				while(true){
				getMap();
				
				updateGUI();
			}
		}});
	
	guiUpdatetor.start();

	}
	
}


	
	
	
	
