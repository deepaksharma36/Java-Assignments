//import files are places here.
import java.io.File;
import java.io.FileNotFoundException;
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
import java.util.Scanner;

/**
 * this Server class interacts with various client who request for
 * file.
 * @author Sharma, Deepak DS5930
 * 
 *  */

public class Server extends UnicastRemoteObject implements
		ServerInterface {
	public  final int RMIport;
	HashMap<Integer,String> serverMap = new HashMap<Integer,String>();
	HashMap<String,Integer> fileRequestCount = new HashMap<String, Integer>();
	String myAddress;
	String myPath="/home/stu13/s17/ds5930/";
	protected Server(int RMIport) throws RemoteException {
		super();
		this.RMIport=RMIport;
		try {
			//this.RMIport=RMIport;
			this.myAddress=InetAddress.getLocalHost().getHostName();
			this.myPath=myPath+this.myAddress+'/';
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		init();
	
	}
	
	/**
	 * Remote Method for copying file on Server, this method will be called by other servers (parent) 
	 * for replicating  file.
	 */
	public void insertFile(String name, byte[] data)
			throws RemoteException {
		Path Currentpath = Paths.get(myPath+name);
		try {
			Files.write(Currentpath, data);
			System.out.println("Received "+name+" File");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * This method will be invoked when file not found at server and request is forward to parent server
	 * @param name File name
	 * @param destination Remote object of the Client which has requested for File.
	 * @param requestCode Array containing coordinates of the Server in Binary Tree.
	 */
	private void forwardRequest(String name, ClientInterface destination,int[] requestCode)
	{
		try {
			requestCode[0]-=1;
			requestCode[1]=requestCode[1]%2==0?requestCode[1]/2:(requestCode[1]-1)/2;
			String hostName=serverMap.get(Math.abs(new String(""+name+requestCode[0]+requestCode[1]).hashCode()%NUMBEROFSERVER)).toString();
			System.out.println("Request will be forwarded to: "+hostName);
			String registryURL = "rmi://" + hostName + ":" + RMIport + "/server";
			ServerInterface aServer = (ServerInterface) Naming
					.lookup(registryURL);
			destination.updateRequestTrail(hostName);
			aServer.getFile(name,destination,requestCode);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	/**
	 * This method will send the file to client once the requested file found 
	 * @param name name of the file
	 * @param destination Remote object of the Client
	 * @param requestCode Array containing server coordinate in random tree. 
	 */
	private void transmitFile(String name,ClientInterface destination,int[] requestCode){
		System.out.println("Transmitting File to the client destination");
		Path path = Paths.get(new String(myPath+name));
		 //Path path = currentDir.toAbsolutePath();
		 
	      try {
			byte[] data = Files.readAllBytes(path);
			destination.getFile(data, name,myAddress);
			updateFileCount(name);
			//destination.updateRequestTrail(myAddress);
			replication(name,requestCode);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Helper Method (replication process) for reading local file and sending to the Server 
	 * @param name File name
	 * @param destination Remote object of the Child Server
	 * @param requestCode 
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
	 * for updating count of request received for a particular file
	 * @param name File Name
	 */
	private void updateFileCount(String name){
		if(!fileRequestCount.containsKey(name))fileRequestCount.put(name, 1);
		else fileRequestCount.put(name, fileRequestCount.get(name));
	}
	/**
	 * This method contacts to the child servers and replicate file on both clients
	 * @param name File Name
	 * @param requestCode Server Coordinates
	 * @throws RemoteException
	 */
	private void replication(String name,int[] requestCode) throws RemoteException{
		
		if(fileRequestCount.get(name)>=REPLICATIONCOUNT && requestCode[0]+1<=2){
		
										//Math.abs(new String(""+name+(requestCode[0]+1)+(2*requestCode[1])).hashCode()%NUMBEROFSERVER)
										//Math.abs(new String(""+name+(requestCode[0]+1)+(2*requestCode[1]+1)).hashCode()%NUMBEROFSERVER)
		
		String	child1=serverMap.get(Math.abs(new String(""+name+(requestCode[0]+1)+(2*requestCode[1])).hashCode()%NUMBEROFSERVER));
		
		String registryURLChild1 = "rmi://" + child1.toString() + ":" + RMIport + "/server";
		String	child2=serverMap.get(Math.abs(new String(""+name+(requestCode[0]+1)+(2*requestCode[1]+1)).hashCode()%NUMBEROFSERVER));
		String registryURLChild2 = "rmi://" + child2.toString() + ":" + RMIport + "/server";
		System.out.println("Replicating File on Child Servers: " +child1+"  "+child2);
		try {
			
			ServerInterface childServer1 = (ServerInterface)Naming.lookup(registryURLChild1);
			transmitFile(name, childServer1, requestCode);
			
			ServerInterface childServer2 = (ServerInterface)Naming.lookup(registryURLChild2);
			transmitFile(name, childServer2, requestCode);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		}
	}
	/**
	 * This method will be invoked by Client in order to inquiring for file 
	 * @param name name of the File inquired by client
	 * @param destination Client's remote object from which request has been received
	 * @param requestCode array containing coordinates of the Server 
	 * @throws RemoteException
	 */
	public void getFile(String name, ClientInterface destination,int[] requestCode) throws RemoteException {
		try {
			//if server is root and don't have file
			/*System.out.println(InetAddress.getLocalHost().getHostAddress().toString());
			System.out.println(myAddress);
			System.out.println(Math.abs(new String(""+name+"00").hashCode()%NUMBEROFSERVER));
			System.out.println(serverMap.get(Math.abs(new String(""+name+"00").hashCode()%NUMBEROFSERVER)));
			System.out.println(myAddress.equals(serverMap.get(Math.abs(new String(""+name+"00").hashCode()%NUMBEROFSERVER)).toString()));*/
			String aRoot=serverMap.get(Math.abs(new String(""+name+"00").hashCode()%NUMBEROFSERVER));
			aRoot=aRoot.substring(0, aRoot.indexOf('.'));
			if(myAddress.equals(aRoot) && !new File(myPath+name).exists())
				destination.Error("FileNotFound");//transmitFile(name,destination,requestCode);//throw new FileNotFoundException();
			
			else if(new File(myPath+name).exists()){System.out.println("Transmitting File");
				transmitFile(name,destination,requestCode);}
			else{
				System.out.println("Forwarding request for File to parent Server");
				forwardRequest(name, destination,requestCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	/*	catch(FileNotFoundException e){
			e.printStackTrace();
		}*/
	}
	
	/**
	 * Method for intializing Server hash map
	 */
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