
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Random;


/**
 * Client class implements the remote interface ClientInterface. the object of this
 * class is used by the server for invoking getFile from
 * various Servers.
 * 
 * @author Sharma, Deepak DS5930
 * 
 */
public class Client extends UnicastRemoteObject implements ClientInterface {
	HashMap<Integer,String> serverMap = new HashMap<Integer,String>();
	String myPath="/home/stu13/s17/ds5930/";
	public final int PORTNUMBER;
	String requestTrail;
	String error;
	protected Client(int SeverPortNumber) throws RemoteException {
		super();
	this.PORTNUMBER=SeverPortNumber;
		try {
			String myAddress = InetAddress.getLocalHost().getHostName();
			this.myPath=myPath+myAddress+'/';
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*public void insertFile(File s, ServerInterface destination)
			throws RemoteException {
		
	}*/
	public void updateRequestTrail(String trail) throws RemoteException{
		this.requestTrail+="->"+trail;
	}
	public void getFile(byte[] data,String name, String server) throws RemoteException {
	    System.out.println("Receiving File: "+name +" from Server");	
		Path Currentpath = Paths.get(myPath+name);
		try {
			Files.write(Currentpath, data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public  void Error(String error) throws RemoteException{
		this.error=error;
	}
	/**
	 * Helper method for invoking servers method for coping file
	 * @param name name of the file
	 * @param destination remote object of the destinations server
	 * @param requestCode Coordinates of the destination server
	 */
	private void transmitFile(String name,ServerInterface destination,int[] requestCode){
		Path path = Paths.get(myPath+name);
		 
	      try {
			byte[] data = Files.readAllBytes(path);
			destination.insertFile(name, data);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	/**
	 * Method for copying file on a given location
	 * @param name name of the file on local Client
	 */
	public void sendFile(String name){
   	int[] requestCode={0,0};
	String server = serverMap.get(Math.abs(new String(name+requestCode[0]+requestCode[1]).hashCode()%NUMBEROFSERVER));
	System.out.println("Sending file "+name+ " to"+server);
	String registryURL = "rmi://" + server + ":" + PORTNUMBER + "/server";

		this.requestTrail=server;
		ServerInterface aServerInterface;
		try {
			aServerInterface = (ServerInterface) Naming.lookup(registryURL);
			transmitFile(name,aServerInterface,requestCode);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

}
    public void findFile(String name){
    	Random aRandom = new Random();
    	int[] requestCode={2,aRandom.nextInt(4)};
    	String server = serverMap.get(Math.abs(new String(name+requestCode[0]+requestCode[1]).hashCode()%NUMBEROFSERVER));
		String registryURL = "rmi://" + server + ":" + PORTNUMBER + "/server";
		try {
			this.requestTrail=server;
			ServerInterface aServerInterface = (ServerInterface) Naming.lookup(registryURL);
			aServerInterface.getFile(name, this, requestCode);
			if (requestTrail!=null)
			System.out.println(this.requestTrail);
			if (error!=null)
			System.out.println(this.error);
			this.requestTrail=null;
			this.error=null;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
	public static void main(String[] args){
    	try {
    		//Below code can be use if RMI gives Error unknown host while it was making callback from server to client
    		//String hostName = InetAddress.getByName(ClientIP).getHostAddress();
    		//System.out.println(hostName);
    		//System.setProperty("java.rmi.server.hostname", hostName);
			Client aClient = new Client(Integer.parseInt(args[0]));
			aClient.init();
			//Comment Below line as file was sent to random server for testing the application
			//aClient.sendFile(args[1]);
			
			aClient.findFile(args[1]);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	
    }
    
	private void init()
	{
		this.serverMap.put(0, SERVERS[0]);
		this.serverMap.put(1, SERVERS[1]);
		this.serverMap.put(2, SERVERS[2]);
		this.serverMap.put(3, SERVERS[3]);
		this.serverMap.put(4, SERVERS[4]);
		this.serverMap.put(5, SERVERS[5]);
		this.serverMap.put(6, SERVERS[6]);
	}
	

}
