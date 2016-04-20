import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;



/**
 * This is an implementation of the Ricard and Agrawala Mechanism, 
 *  
 * @author sharma, deepak
 *
 */

public class Process extends UnicastRemoteObject implements ProcessInterface{
	
	
	// maintain log of transactions/requests/events for future use
	
	int processID;
	volatile int holderID;

	volatile String processState="RELEASED";
	String RMIport;
	Object CSLock = new Object();
	Object holderLock = new Object();
	Object tokenLock = new Object();
	Object stateLock = new Object();
	Object tokenReceiving= new Object();
	boolean working=true;
	
	
	HashMap<String, ProcessInterface> remoteObjectStorage;

	Queue<Integer> csRequests = new LinkedList<Integer>();
	Queue<Integer> processorQue = new LinkedList<Integer>();
	/**
	 * Constructor of process
	 * @param processCount Number of process available 
	 * @param processID Id for process
	 * @param RMIport Port number for communicating with peers
	 * @throws RemoteException
	 */
	Process(int processID,int holderID, String RMIport) throws RemoteException{
	
		this.processID=processID;
		this.holderID=holderID;

		
		this.RMIport=RMIport;
		remoteObjectStorage = new HashMap<String, ProcessInterface>();
		
	
	}
			
	/**
	 * For accessing  the remote Process this method provide STUB using RMI  
	 * @param hostName Name of the Remote Process
	 * @return Stub of Remote Process
	 * @throws RemoteException
	 */
	private ProcessInterface getProcess(String hostName) throws RemoteException{
		
		if (remoteObjectStorage.containsKey(hostName))
			return remoteObjectStorage.get(hostName);
		String registryURL = "rmi://" + hostName + ":" + RMIport + "/server";
		try {
			ProcessInterface aProcess = (ProcessInterface) Naming
					.lookup(registryURL);
			remoteObjectStorage.put(hostName, aProcess);
			return aProcess;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}  catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public  void  tokenRequest(int requistersID) throws RemoteException {
		
			synchronized(tokenLock){
			System.out.println("Token Request from "+PROCESS[requistersID-1]+" has been received");	
			processorQue.add(requistersID);
			tokenLock.notify();
			}
		
		
	}
	/**
	 * This method is used by requestResolverThread to resolve a token request
	  */
	public void processTokenRequest() throws RemoteException, InterruptedException
	{int aRequest;
		while(true){
		synchronized(tokenLock){
			if(processorQue.size()==0)
				tokenLock.wait();
			aRequest=processorQue.poll();

		}
		
		if(!csRequests.contains(aRequest))
		{	
			
			synchronized(holderLock){
				System.out.println("\tToken Request from "+PROCESS[aRequest-1]+" has been placed on CS request que");
				csRequests.add(aRequest);
				showCSRequest();
			if(holderID!=processID){
				getProcess(PROCESS[holderID-1]).tokenRequest(processID);
				System.out.println("\tIn response to token Request from "+PROCESS[aRequest-1]+"\n\tA token Request to: "+PROCESS[holderID-1]+" has been made");}
			}
			Thread.sleep(5);
		}
		else
			System.out.println("\t Not Entertaining request marked as Duplicate ");
		
		}


		}
	/**
	 * This method is Executed by csRequestExecutor Thread when process Receive the token 
	 * It checks the CS request que and take necessary action
	 * @throws InterruptedException
	 * @throws RemoteException
	 */
	private void resolvingCSrequest() throws InterruptedException, RemoteException{
		System.out.println("Checking CSRequest Que:");
		showCSRequest();
		int nextRequest =csRequests.poll();
		System.out.println("\t Resolving CS request from "+PROCESS[nextRequest-1]);
		if (nextRequest==processID)
			critcalSection();
			
		else
			sendToken(nextRequest);
	}
	/**
	 * Method used by  csRequestExecutor Thread for sending token to remote process
	 * @param nextRequest
	 * @throws RemoteException
	 */
	private void sendToken(int nextRequest) throws RemoteException
	{
		synchronized(holderLock){
		updateThreadState("RELEASED");

		System.out.println("\t Sending token to: "+PROCESS[nextRequest-1]);
		if(getProcess(PROCESS[nextRequest-1]).reponseTokenRequest(processID)){
			System.out.println("\tUpdated Holder to "+ PROCESS[nextRequest-1]);
			holderID=nextRequest;
		if (csRequests.size()>0)
			{getProcess(PROCESS[holderID-1]).tokenRequest(processID);
			System.out.println("\t Sending a token Request to "+ PROCESS[holderID-1]+"As My que is not empty");}
		}
		
		}
		
	}

	/**
	 * Remote processes use this method for delivering Token to the process
	 */
	public boolean reponseTokenRequest(int requesterID) throws RemoteException {
		synchronized(holderLock)
		{
			System.out.println("Received Token, Delivered by "+PROCESS[requesterID-1]);
			this.holderID=this.processID;
			return true;
		}
	}
	
	/**
	 * Once all the requirements for Critical section is made then this method will be executed
	 * by Critical Section Executer thread.
	 * @throws InterruptedException 
	 */
	private void critcalSection() throws InterruptedException
	{
		Random aRandom = new Random();
 		updateThreadState("HELD");
		System.out.println("Inside Critical Section");
		Thread.sleep(aRandom.nextInt(1)+10000);
		closingTask();
		
}


	/**
	 * This method Executed after execution of critical section for resettings
	 */
	private void closingTask() 
	{

		System.out.println("Released the Critical Section");
	
		updateThreadState("RELEASED");
		
	}

/**
 * For updating the status of Process: Released Held	
 * @param status
 */
	private  void  updateThreadState(String status)
	{
		synchronized(stateLock){
		this.processState=status;}
	}
	/**
	 * This thread seeks/create opportunities for getting Critical Section after Random Time
	 */
	
	public void criticalSectionSeeker(){
		Thread criticalSectionSeeker = new Thread(new Runnable() {
			Random randomWait = new Random(); 
			public void run() {
				while(true){
				try {
					Thread.sleep(randomWait.nextInt(1)+5000);
					if(processState.equals("RELEASED") && !processorQue.contains(processID) && !csRequests.contains(processID))
						{
						System.out.println("Critical Section request generated by Critical Section Seeker Thread");
						tokenRequest(processID);
						}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (RemoteException e) {
					
					e.printStackTrace();
				}
				
			}
				}
		
		});criticalSectionSeeker.start();
	}
	/**
	 * This Thread Resolve CS requests once process receive the token, get notified/activated once it receive the Token 
	 *  
	 */
	
	public void csRequestExecutor(){
		Thread csRequestExecutor = new Thread( new Runnable() {
			public void run() {
				try {
					while(true){
						if(holderID==processID && csRequests.size()>0){
						System.out.println("Now possessing token, Identified by CS request Executor thread");
						
						resolvingCSrequest();
						}}
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		csRequestExecutor.start();
	}

	/**
	 * This Thread Entertain Critical Section requests received by self(critical Section Seeker Thread) and other processes 
	 */
	public void requestResolver()
	{
		Thread requestResolverThread = new Thread(new Runnable() {
			public void run() {
				
				try {
					processTokenRequest();
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
					
				}
				
			
		});
		requestResolverThread.start();
		
	}

	/**
	 * Method for printing the CS Request que at various locations 
	 */
	public void showCSRequest()
	{System.out.println("\n CS Request Que");
		for(int request: csRequests)
			System.out.print("\t"+PROCESS[request-1].substring(0,PROCESS[request-1].indexOf('.')));
	System.out.println("\n");
	}
}
