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
import java.util.HashSet;
import java.util.Scanner;

import javax.print.attribute.standard.Destination;

/**
 * this Server class interacts with various peer in chord, provide functionality for adding file in chord,
 * removing file from chord .adding new node and leaving old from chord
 * @author Sharma, Deepak DS5930
 *  */

public class Server extends UnicastRemoteObject implements ServerInterface {
	public  final int RMIport;
	public final String EntryServer;
	public final int NUMBEROFIDSPCES;
	HashMap<Integer,String> serverMap = new HashMap<Integer,String>();
	HashMap<String,String> files = new HashMap<String,String>();
	private String predecessor=null;
	public String getPredecessor() throws RemoteException {
		return predecessor;
	}
	public String getSuccessor() throws RemoteException {
		return successor;
	}
	private String successor=null;
	private int[] range=new int[2];
	String myAddress;
	String addRequest="";
	String fileAddRequest="";
	String fileSearchRequest="";
	String error=null;
	String myPath="/home/stu13/s17/ds5930/";
	protected Server(int RMIport, String EntryServer , String path,int numberOfIdSpaces) throws RemoteException {
		super();
		this.RMIport=RMIport;
		this.NUMBEROFIDSPCES=numberOfIdSpaces;
		this.EntryServer=EntryServer;
		try {
			//this.RMIport=RMIport;
			this.myAddress=InetAddress.getLocalHost().getHostAddress();
			//this.myPath=myPath+this.myAddress+'/';
			this.myPath=path+'/';
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	
	}
	/**
	 * For generating hashCode for a Server
	 * @param aString
	 * @return
	 */
	private int ServerHashCode(String aString)
	{
		return Math.abs(aString.hashCode()%NUMBEROFIDSPCES);
	}
	/**
	 * For generating hash Code for a File
	 * @param aString
	 * @return
	 */
	private int FileHashCode(String aString)
	{
		return Math.abs(aString.hashCode()%NUMBEROFIDSPCES);
	}
	public int[] getRange() throws RemoteException {
		return range;
	}
	public void setRange(int[] range) throws RemoteException {
		this.range = range;
	}
	public void setPredecessor(String predecessor) throws RemoteException {
		this.predecessor = predecessor;
	}
	public void setSuccessor(String successor) throws RemoteException{
		this.successor = successor;
	}
	/**
	 * after adding successor server will update its id space
	 * @param hashCode
	 */
	private void updateMyIdSpace(int hashCode){
		range[0]=hashCode+1;
	}
	/**
	 * helper method for now node joining method it will update predecessor of host node
	 * @param hostName
	 * @throws RemoteException
	 */
	private void contactMyOldPredecessorForSuccessorUpdate(String hostName) throws RemoteException{
		if (this.predecessor!=null)
		{
		ServerInterface predecessor =ServerContact(this.predecessor);
		predecessor.setSuccessor(hostName);
		}
		
	};

	public void updateNewNode(String predecessor, String successor,int minRange,int maxRange ) throws RemoteException
	{
	 this.predecessor=predecessor;
	 int[] range = {minRange,maxRange};
	 this.setRange(range);
	 this.successor=successor;
	}
	public synchronized void addNode(String hostName) throws RemoteException {
		try {
			int hashCode = ServerHashCode(hostName);
		/*	System.out.println
			("Trying to Add server "+InetAddress.getByName(hostName).getHostName()+" With hash Code "+hashCode);
			System.out.println(ipToHost(myAddress)+" server takes care of (Before Adding) "+range[0]+" to "+range[1]);
		*/
		if (hashCode==range[1])
			ServerContact(hostName).Error("Space already occupied, can not add you");
		else if (hashCode>=range[0] && hashCode<range[1])
				{
				ServerContact(hostName).updateAddRequestString(ipToHost(myAddress));
				System.out.println(InetAddress.getLocalHost().getHostName()+ " is adding " +InetAddress.getByName(hostName).getHostName());
				if (myAddress.equals(successor) && myAddress.equals(predecessor)){
					setSuccessor(hostName);
					setPredecessor(hostName);
					ServerContact(hostName).updateNewNode(myAddress,myAddress,range[0],hashCode);
					updateMyIdSpace(hashCode);
				}
				else{
				ServerContact(hostName).updateNewNode(predecessor,myAddress,range[0],hashCode);
				contactMyOldPredecessorForSuccessorUpdate(hostName);
				updateMyIdSpace(hashCode);
				setPredecessor(hostName);}
			System.out.println("Successfully added");}
		else if(Math.min(Math.abs(hashCode-range[0]),NUMBEROFIDSPCES-Math.abs(hashCode-range[0]))<
		Math.min(Math.abs(hashCode-range[1]),NUMBEROFIDSPCES-Math.abs(hashCode-range[1]))){
			ServerContact(hostName).updateAddRequestString(ipToHost(myAddress));	
			ServerContact(predecessor).addNode(hostName);
		}
		else{
			ServerContact(hostName).updateAddRequestString(ipToHost(myAddress));
			ServerContact(successor).addNode(hostName);}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
				
	}
 	public synchronized void leave() throws RemoteException{
 		// removing last node
 		
 		 if(predecessor!=null && successor!=null && range!=null){
 		System.out.println(ipToHost(myAddress)+" is ready for removal, Detals before removal:");
 		view();
		int[] predRange  ={range[0],ServerContact(successor).getRange()[1]};
		ServerInterface successorObj=ServerContact(successor);
		ServerInterface predecessorObj=ServerContact(predecessor);
		successorObj.setRange(predRange);
		moveAllFile(successor);
		predecessorObj.setSuccessor(successor);
		successorObj.setPredecessor(predecessor);
		this.setPredecessor(null);
		this.setSuccessor(null);
		this.range=null;
		/////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("Results for testing of leaving operation:");
		System.out.println(ipToHost(myAddress)+" has been successfully removed");
		System.out.println("prdecessor Details:");
		System.out.println("predecessor Node Name: "+ipToHost(predecessor));
		System.out.println(predecessorObj.getRange()[0]+" to "+predecessorObj.getRange()[1]);
		System.out.println("Pre: "+ipToHost(predecessorObj.getPredecessor())+" Succ: "+ ipToHost(predecessorObj.getSuccessor()));
		System.out.println("successor Details:");
		System.out.println("successor Node Name: "+ipToHost(successor));
		System.out.println(successorObj.getRange()[0]+" to "+successorObj.getRange()[1]);
		System.out.println("Pre: "+ipToHost(successorObj.getPredecessor())+" Succ: "+ ipToHost(successorObj.getSuccessor()));
		}
 		else{
 		System.out.println(myAddress+" is not a part of chord");
 		}
 		
	} 
 	private String ipToHost(String Ip){
 		try {
			return InetAddress.getByName(Ip).getHostName();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
 	}
	/**
	 * After deleting file this will move all the file of removed server to its successor
	 * @param predecessor
	 * @throws RemoteException
	 */
 	public void moveAllFile(String predecessor) throws RemoteException
	{
		//File folder = new File("/Users/you/folder/");
		//File[] listOfFiles = folder.listFiles();
		System.out.println("Moving all File for to "+ipToHost(successor));
		for (String file : files.keySet()) {
		 transmitFile(file,files.get(file), ServerContact(successor));
}


	}
	
 	/**
 	 * insert file on server
 	 * @param keyWord
 	 * @param FileName
 	 */
	public void insert(String keyWord, String FileName)  
	{
		fileAddRequest="";
		int idSpace=FileHashCode(keyWord);
		ServerInterface destinationServer;
		try {
			//this.fileAddRequest+=EntryServer;
			if (this.successor==null)
				destinationServer = ServerContact(EntryServer).findDestinationServer(idSpace,this);
			else
				destinationServer = ServerContact(myAddress).findDestinationServer(idSpace,this);
			transmitFile(keyWord,FileName,destinationServer);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
	public ServerInterface findDestinationServer(int idSpace,  ServerInterface source) throws RemoteException{
		System.out.println("Finding on server  "+ipToHost(myAddress));
		if (idSpace>=range[0] && idSpace <=range[1]){
			System.out.println("Found Destination");
			source.updateFileAddRequestString(ipToHost(myAddress));
			source.updateSearchRequest(ipToHost(myAddress));
			return this;}
		else if(Math.min(Math.abs(idSpace-range[0]),NUMBEROFIDSPCES-1-Math.abs(idSpace-range[0]))<
				Math.min(Math.abs(idSpace-range[1]),NUMBEROFIDSPCES-1-Math.abs(idSpace-range[1]))){
			System.out.println("going to Predecessor");
			source.updateFileAddRequestString(ipToHost(myAddress));
			source.updateSearchRequest(ipToHost(myAddress));
			return ServerContact(predecessor).findDestinationServer(idSpace,source) ;
			}
			else{
				System.out.println("going to sucessor");
				source.updateFileAddRequestString(ipToHost(myAddress));
				source.updateSearchRequest(ipToHost(myAddress));
				return ServerContact(successor).findDestinationServer(idSpace,source) ;
				}
	
	}
	
	public void updateSearchRequest(String hostName) throws RemoteException
	{
		fileSearchRequest+="->"+hostName;
	}
	/**
	 * for searching a file on chord
	 * @param keyword
	 * @throws RemoteException
	 */
	public void search(String keyword) throws RemoteException{
		System.out.println("Search for the keyword"+keyword+"Has been invoked");
		error=null;
		int idSpace=FileHashCode(keyword);
		System.out.println("id space is "+idSpace);
		this.fileSearchRequest="";
		ServerInterface	destinationServer;
			if (this.successor==null)
				destinationServer=ServerContact(EntryServer).findDestinationServer(idSpace,this);
			else
				destinationServer=ServerContact(myAddress).findDestinationServer(idSpace,this);
			destinationServer.sendFile(keyword, this);
			System.out.println(fileSearchRequest);
				//destinationServer.Error("Requested File Not Found in cord system");
		
	}
	/**
	 * Remote servers use this method for sending message to requester
	 */
	public  void Error(String error) throws RemoteException{
		this.error=error;
	}
	public void sendFile(String keyword, ServerInterface destination) throws RemoteException
	{String name;
		if(files.containsKey(keyword)){
			 name=files.get(keyword);
				if (new File(myPath+name).exists())
					transmitFile(keyword,name, destination);
			else
				destination.Error("Requested File Not Found in cord system");
	 
		}
		else
			destination.Error("File Not Found");
			
	}
	
	public void updateAddRequestString(String hostName) throws RemoteException{
		addRequest=addRequest+"->"+hostName;
	}
	public void updateFileAddRequestString(String hostName) throws RemoteException{
		fileAddRequest=fileAddRequest+"->"+hostName;
	}
	/**
	 * for viewing peer perameter
	 */
	public void view()
	{	error=null;
		try {
			if(predecessor!=null && successor!=null && range!=null){
			System.out.println("Predecessor: "+this.predecessor +" "  + InetAddress.getByName(predecessor).getCanonicalHostName());
			System.out.println("Successor: "+this.successor +" "+InetAddress.getByName(successor).getCanonicalHostName());
			System.out.println(ipToHost(myAddress)+" Is taking care of Id Space: "+range[0]+" -to- "+range[1]);
		for(String file:files.keySet())
		{
			System.out.println(file+": "+files.get(file));
		}}
			else{
				System.out.println("This server is not a part of Chord anymore");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Server Invoke this method for joing chord
	 */
	public void add(){
		error=null;
		try {
			addRequest="";
			ServerContact(EntryServer).addNode(myAddress);
			if( error!=null);
			addRequest=addRequest+"\n"+error;
			System.out.println(addRequest);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Remote Method for copying file on Server, this method will be called by other servers 
	 * 
	 */
	public synchronized void insertFile(String keyWord, String name, byte[] data)
			throws RemoteException {
			Path Currentpath = Paths.get(myPath+name);
			try {
				Files.write(Currentpath, data);
				files.put(keyWord, name);
				System.out.println("Received "+name+" File" + files.containsKey(keyWord));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Helper Method  for reading local file and sending to the Server 
	 * @param name File name
	 * @param destination Remote object of the Child Server
	 * @param requestCode 
	 */
	private void transmitFile(String keyWord, String name,ServerInterface destination ){
		System.out.println("Transmit File has been called");
		System.out.println(myPath+name);
		Path path = Paths.get(myPath+name);
		 
	      try {
	    	  if (new File(myPath+name).exists()){  
			byte[] data = Files.readAllBytes(path);
			destination.insertFile(keyWord,name, data);}
	    	  else{
	    		  Error("file not FOund at Local System");
	    	  }
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	private ServerInterface ServerContact(String hostName) throws RemoteException{
		
		String registryURL = "rmi://" + hostName + ":" + RMIport + "/server";
		try {
			ServerInterface aServer = (ServerInterface) Naming
					.lookup(registryURL);
			return aServer;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String testInsert(String keyWord,String testFile ) throws RemoteException{
		this.insert(keyWord,testFile);
		
		String result="Keyword with hash Code "+FileHashCode(keyWord)+" added at:";
		result+="\n"+this.fileAddRequest;
		if (error!=null)
			result+='\n'+error;
		return result;
	}
	
	public void testLeave() throws RemoteException{
		this.leave();
	}
	
	@SuppressWarnings("finally")
	public String testView() throws RemoteException{
		String output="";
		try {
			if(predecessor!=null && successor!=null && range!=null){
			output+="\t"+"Predecessor: "+this.predecessor +" "  + InetAddress.getByName(predecessor).getCanonicalHostName();
			output+="\n"+"\t"+"Successor: "+this.successor +" "+InetAddress.getByName(successor).getCanonicalHostName();
			output+="\n"+"\t"+ipToHost(myAddress)+" Is taking care of Id Space: "+range[0]+" -to- "+range[1];
		for(String file:files.keySet())
		{
			output+="\n"+"\t"+file;
		}}
			else{
				output+="\t"+"This server is not a part of Chord anymore";
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		finally{
			return output;
		}
	}
	
	public String testAdd() throws RemoteException{
		this.add();
		if (error!=null)
			return addRequest+"\n"+error;
		return addRequest;
		
	}
	
	public String testSearch(String keyword) throws RemoteException{
		this.search(keyword);
		String result="Keyword with hash Code "+FileHashCode(keyword)+" found at:";
		
		if (error!=null)
			return result+"\n"+fileSearchRequest+"\n"+error;
		return result+"\n"+fileSearchRequest;
	}
}