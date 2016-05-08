

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Scanner;

public class Client
{
	private String path, IP;
	private MetaData MD;
	private int selfId, portNo;
	private HashMap<Integer, Peer> peersList;
	public HashMap<String,FetchPieces> inProgress= new HashMap<String, FetchPieces>(); 
	private void init(String path, int port)
	{
		try
		{
			this.path = path;
			this.portNo = port;
			this.IP = Inet4Address.getLocalHost().getHostName();
			this.deserialize(this.IP);
			this.initialSetup();
			this.contactTracker();
		}
		catch(Exception e)
		{
			System.out.println("I am in client constructor exception");
		}
	}
	
	private void publishInit(String path, int port) throws UnknownHostException
	{
		this.path = path;
		this.portNo = port;
		this.IP = Inet4Address.getLocalHost().getHostName();
		this.deserialize(this.IP);
		byte[] pieceInfo = new byte[(int) this.MD.getNoOfPieces()];
		for(int i=0;i<this.MD.getNoOfPieces();i++)
		{
			pieceInfo[i] = 1;
		}
		System.out.println("trying to connect to tracker");
		String registryURL = "rmi://" + this.MD.getTrackerServer() +".cs.rit.edu"+ ":"
				+ this.MD.getTrackerPort() + "/FileServer";
		try
		{ 
			TrackerInterface TI = (TrackerInterface) Naming
						.lookup(registryURL);
			this.selfId = TI.publish(this.MD.getFileName(), this.IP, String.valueOf(this.portNo), pieceInfo, (int)this.MD.getPieceLength(), (int)this.MD.getNoOfPieces(), (int)this.MD.getSizeOfFile());
			System.out.println(selfId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("I am in exception while contacting tracker");
		}
	}
	
	private void deserialize(String str)
	{
		try
		{
			System.out.println(System.getProperty("user.dir"));
			File file = new File("/home/stu4/s15/la4401/Courses/CSCI652/BT/"+str+"/metadata.txt");
			if(!file.exists())
			{
				System.out.println(" Not Exists");
				return;
			}
			FileInputStream fin=new FileInputStream(this.path);  
			ObjectInputStream in=new ObjectInputStream(fin);
			this.MD = (MetaData)in.readObject();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("I am in deserialize exception");
		}
	}
	
	private void initialSetup()
	{
		try
		{
			File file = new File("/home/stu4/s15/la4401/Courses/CSCI652/BT/arizona/"+this.MD.getFileName());
			file.createNewFile();
			RandomAccessFile f = new RandomAccessFile(file, "rw");
			f.setLength(this.MD.getSizeOfFile());
			f.close();
		}
		catch(Exception e)
		{
			System.out.println("I am in exception while doing initial setup");
		}	
	}
	
	private void contactTracker()
	{
		String registryURL = "rmi://" + this.MD.getTrackerServer() +".cs.rit.edu"+ ":"
		+ this.MD.getTrackerPort() + "/FileServer";
		try
		{ 
			TrackerInterface TI = (TrackerInterface) Naming
						.lookup(registryURL);
			this.selfId = TI.addPeer(this.IP, String.valueOf(this.portNo), this.MD.getFileName(), new byte[(int) this.MD.getNoOfPieces()]);
			this.peersList = TI.getPeers(this.selfId, this.MD.getFileName());
			show();
		}
		catch(Exception e)
		{
			System.out.println("I am in exception while contacting tracker");
		}
	}
	
	private void initiateProcess()
	{
		try
		{
			String path = "/Courses/CSCI652/BT/arizona/" + this.MD.getFileName();
			FetchPieces FP = new FetchPieces(this.peersList, this.MD, this.selfId, path);
			inProgress.put(this.MD.getFileName(), FP);
			Registry registry = LocateRegistry.createRegistry(this.portNo);
			registry.rebind("FileServer", FP);
		}
		catch(Exception e)
		{
			System.out.println("I am in initiateProcess exception");
		}
	}
	
	public static void main(String args[]) throws NumberFormatException, UnknownHostException
	{
		Scanner scan = new Scanner(System.in);
		Client step1 = new Client();
		System.out.println("Enter 1 to publish or 2 to search for a file");
		int input = scan.nextInt();
		if(input==2)
		{
			step1.init("/home/stu4/s15/la4401/Courses/CSCI652/BT/arizona/metadata.txt", Integer.parseInt(args[0]));
		}
		else if(input==1)
		{
			step1.publishInit("/home/stu4/s15/la4401/Courses/CSCI652/BT/kansas/metadata.txt", Integer.parseInt(args[0]));
		}
	}
	
	public void show()
	{
		for(int id: this.peersList.keySet())
			System.out.println(peersList.get(id));
	}
}
