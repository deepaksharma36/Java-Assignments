
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentLinkedQueue;


public class FetchPieces extends UnicastRemoteObject implements FetchPiecesInterface
{
	private HashMap<Integer, ArrayList<Peer>> rarePiecesMap;
	private HashMap<Integer, Peer> peerMap;
	public HashMap<Integer, Peer> getPeerMap() {
		return peerMap;
	}

	public MetaData getMD() {
		return MD;
	}

	public int getSelfId() {
		return selfId;
	}

	private MetaData MD;
	private HashMap<Integer, Integer> handshakeStatus;
	private String IP, port, path;
	private int selfId;
	private Queue<Integer> handshakeList;
	private Object handshakelock;
	
	public FetchPieces(HashMap<Integer,Peer> peerMap, MetaData MD, int id, String path) throws RemoteException
	{
		this.peerMap = peerMap;
		this.MD = MD;
		this.selfId = id;
		this.path = path;
		this.init();
		this.handshakeStatus = new HashMap<Integer, Integer>();
		this.handshakeList = new ConcurrentLinkedQueue();
		this.handshakelock = new Object();
	}
	
	private void init()
	{
		for(int i=1;i<=this.MD.getNoOfPieces();i++)
		{
			this.rarePiecesMap.put(i, new ArrayList<Peer>());
		}
	}

	private void buildRarePiecesMap()
	{
		for(Entry<Integer, Peer> entry: this.peerMap.entrySet())
		{
			if(this.selfId!=entry.getValue().getPeerId())
			{
				for(int i=0;i<this.MD.getNoOfPieces();i++)
				{
					if(entry.getValue().getPieceInfo()[i]==1)
						this.rarePiecesMap.get(i).add(entry.getValue());
				}
			}
		}
	}
	
	private void sendHandShakes()
	{
		for(Entry<Integer, Peer> entry: this.peerMap.entrySet())
		{
			if(this.selfId!=entry.getValue().getPeerId())
			{
				try
				{
				
					int id = handshakeList.poll();
					String address = "rmi://" + peerMap.get(id).getIPaddress() + ":"
							+ peerMap.get(id).getPortNumber() + "/FileServer";
					//get object
					FetchPiecesInterface fileInterface = (FetchPiecesInterface)Naming
							.lookup(address);
					//Appropriate method called using interface
					fileInterface.receiveHandShake(selfId);
				}
				catch(Exception e)
				{
					System.out.println("I am in sendHandShakesException");
				}
			}
		}
	}
	
	public void receiveHandShake(int peerId)
	{
		this.handshakeList.add(peerId);
		synchronized (this.handshakelock) {
			this.handshakelock.notify();
		}
	}
	
	public void handShakeReply(int peerId)
	{
		this.handshakeStatus.put(peerId, 1);
	}
	
	public void handShakeResolver()
	{
		Thread a = new Thread(new Runnable()
		{
			public void run() 
			{
				try
				{
					while(true)
					{
						synchronized (handshakelock) {
							if(handshakeList.isEmpty())
								handshakelock.wait();
						}
						//address is generated
						int id = handshakeList.poll();
						String address = "rmi://" + peerMap.get(id).getIPaddress() + ":"
								+ peerMap.get(id).getPortNumber() + "/FileServer";
						//get object
						FetchPiecesInterface fileInterface = (FetchPiecesInterface)Naming
								.lookup(address);
						//Appropriate method called using interface
						fileInterface.handShakeReply(selfId);
			
					  }
				}
				catch(Exception e)
				{
					System.out.println("I am in handShakeResolver Exception");
				}	
			}
			});
		
		a.start();
		
		
	}
}
