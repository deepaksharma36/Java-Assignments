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

public class Process extends UnicastRemoteObject implements ProcessInterface, Runnable{
	
	
	volatile private int[] vectorClock;
	volatile int amount=1000;
	// maintain log of transactions/requests/events for future use
	TransactionsLog log;
	int processID;
	int processCount;
	volatile String processState="RELEASED";
	String RMIport;
	Object clockLock = new Object();
	Object moneyLock = new Object();
	Object tokenLock = new Object();
	Object stateLock = new Object();
	boolean working=true;
	Transaction seekingCS=null;
	CSRequest currentRequest=null;
	HashMap<String, ProcessInterface> remoteObjectStorage;
	HashSet<Integer> okMessage = new HashSet<Integer>();
	Queue<Integer> pendingRequst = new LinkedList<Integer>();
	Queue<CSRequest> csRequests = new LinkedList<CSRequest>();
	/**
	 * Constructor of process
	 * @param processCount Number of process available 
	 * @param processID Id for process
	 * @param RMIport Port number for communicating with peers
	 * @throws RemoteException
	 */
	Process(int processCount,int processID, String RMIport) throws RemoteException{
	
		this.processID=processID;
		this.processCount=processCount;
		vectorClock = new int[processCount];
		this.log= new TransactionsLog();
		this.RMIport=RMIport;
		remoteObjectStorage = new HashMap<String, ProcessInterface>();
		
	
	}
	/**
	 * for updating Vector clock after internal events
	 * @return
	 */
	public int[] updateClock()
	{
		synchronized(clockLock){
		 this.vectorClock[processID-1]++;
		 return vectorClock;
		}
	}
	/**
	 * For updating Vector clock after event trigger by external events 
	 * @param vectorClock
	 * @return
	 * @throws RemoteException
	 */
	public  int[] updateClock(int[] vectorClock) throws RemoteException{
		synchronized(clockLock){
		for(int counter=0;counter<processCount;counter++)
		{
			if(this.vectorClock[counter]<vectorClock[counter])
				this.vectorClock[counter]=vectorClock[counter];
		}
		return updateClock();
		}
	}
	
	/**
	 * For updating Money 
	 * @param amount
	 */
	public void updateMoney(int amount)
	{
		synchronized(moneyLock)
		{
			this.amount+=amount;
		}
	}
	
	/**
	 * withdrawal method
	 * @param withdrawAmount 
	 * @param message custom message
	 * @return if sufficiant amount is not available than return false
	 */
	public  boolean withdraw(int withdrawAmount , String message){
		if(withdrawAmount<amount){
		synchronized(this){
			updateMoney(-1*withdrawAmount);
			int[] timeStamp=updateClock();
			log.registerEvent
			(new Transaction("withdraw "+message, (timeStamp), withdrawAmount, amount));
		}
		return true;
		}
		System.out.println("Insuficiant Fund");
		return false;
	}

	public boolean deposit(int depositAmount,String message,int[] time) throws RemoteException{
		synchronized(this){
			updateMoney(depositAmount);
			int[] timeStamp = updateClock(time);
			log.registerEvent
			(new Transaction("deposit"+message, timeStamp, depositAmount, amount));
		}
		return true;
	}
	/**
	 * this functionality take place to two steps first withdrawal at sender's end
	 * then deposit at receivers end 
	 * @param processID Id of the receiving process
	 * @param amount amount to be transfered
	 * @return
	 */
	public boolean sendMoney(int processID,int amount){
		synchronized(this){
			if(withdraw(amount,"DR for sending "+processID)){
				try {
					//danger zone
					//getProcess(PROCESS[processID-1]).updateClock(vectorClock);
				
				if(getProcess(PROCESS[processID-1]).deposit(amount,"CR from "+processID,vectorClock))
					return true;
				} catch (RemoteException e) {
				
					e.printStackTrace();
					return false;
				}}
		}
		return false;
	}
	
	/**
	 * For accessing  the remote Process this method prvoid STUB using rmi  
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

	/**
	 * This method execute random transactions for showing the working of the vector clock
	 */
	public void run() {
		try{
		System.out.println("Money Sending Process has started");	
		while(true){	
		Random r = new Random();
		int tAmount=r.nextInt(100)+1;
		int action=r.nextInt(3)+1;
		int processNumber;
		do{
		 processNumber=r.nextInt(processCount)+1;}
		while(processNumber==this.processID);
		Thread.sleep(5000);
		switch (action) {
		case 1:
			System.out.println("\t\t\t\t\tdepositing money "+tAmount);
			deposit(tAmount,"Self CR",vectorClock);
			log.showLast();
			break;
		case 2:
			System.out.println("\t\t\t\tSending money "+tAmount+" to "+processNumber);
			sendMoney(processNumber, tAmount);
			log.showLast();
			break;
		case 3:
			System.out.println("\t\t\t\twithdrawing money "+tAmount);
			withdraw(tAmount,"Self DR");
			log.showLast();
			break;				
		default:
			System.out.println("Invalid Choice");
			break;
		}
		}
		}catch(RemoteException ex){
			ex.printStackTrace();
		}
		catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * For comparing Vector TIme Stamp of two processes
	 * @param time
	 * @param guestProcessID
	 * @return
	 */
	private synchronized boolean compareTime(int[] time, int guestProcessID)
	{
				 
		int equaler=0;
		int requesterLeadCount=0;
		for(int counter=0;counter<processCount;counter++){
			if(time[counter]==this.seekingCS.timeStamp[counter])
				equaler++;
			if(time[counter]>=this.seekingCS.timeStamp[counter])
				requesterLeadCount++;
		}
		if (requesterLeadCount==this.processCount && equaler!=processCount){
			System.out.println("Not sending Ok as Visiters' request time  :"
		+Arrays.toString(time)+" is > self time: "+Arrays.toString(seekingCS.timeStamp));
			return false;}
		if (equaler<this.processCount && requesterLeadCount==equaler)
		{ System.out.println(Arrays.toString(time)+" Is less than  "+ Arrays.toString(seekingCS.timeStamp));
			return true;}
		System.out.println("There is a Tia between: " +Arrays.toString(time)+" and "+Arrays.toString(seekingCS.timeStamp));
		return tiaBreaker( guestProcessID);
	}
	/**
	 * This method Will be used to break tia based of priority, Less Process ID Gets high Priority
	 * @param guestProcessID
	 * @return return True if guest deserve a OK message
	 */
	private boolean tiaBreaker(int guestProcessID){
		
		if (guestProcessID<this.processID)
			return true;
		return false;
	}
	
	
	public  void  tokenRequest(int requistersID, int[] visitorCSTime, int[] vectorClock) throws RemoteException {
		synchronized(tokenLock){
		//once we received the request we created an transaction then we provided necessary response  
		int[] timeStamp=updateClock(vectorClock);
		this.log.registerEvent(new Transaction("Received Request for CS"+requistersID, timeStamp, 0, amount));
		//log.showLast();
		currentRequest=new CSRequest(visitorCSTime, requistersID,timeStamp);
		tokenLock.notify();
		System.out.println("Received Token Request from "+requistersID+"at Local Time: "+Arrays.toString(timeStamp));
		// If i don't want I will say ok}
	}}
	/**
	 * This method is used by Resolver Thread to resolve a token request
	 * @param requistersID ID of remote server which have made request
	 * @param time Time since remote server Seeking Critical Section
	 * @param localTime Time seice Local Server Seeking critical section
	 * @throws RemoteException
	 */
	public void processTokenRequest(int requistersID,int[] visitorCSTime,int[] localTime) throws RemoteException
	{
		
		synchronized(stateLock){
		if(processState.equals("RELEASED")){
			System.out.println("  I am in Released State so reponding ok to requister  "+requistersID);
			getProcess(PROCESS[requistersID-1]).reponseTokenRequest(processID,localTime);}
		// If i want then I will compare my time with this guy time
		// if he wins i will send Ok else I will put him in que
		else if(processState.equals("WANTED")){
			System.out.println("Received Token in wanted state");
			if(compareTime(visitorCSTime,requistersID))
			{   System.out.println("sending ok");
				getProcess(PROCESS[requistersID-1]).reponseTokenRequest(processID,localTime);
			}
			else 
			{ System.out.println("Not sending ok, Putting"+requistersID + "'s Request into Que");
			 
				pendingRequst.add(requistersID);}
		}
		// because this mate gona call me one day , then I have to check
		// if I am using It, i will put your request in my que
		else{
			System.out.println("Not sending ok, Putting"+requistersID + "'s Request into Que");
			pendingRequst.add(requistersID);
		}
		}
	}
	
    // Reponse for token request

	public void reponseTokenRequest(int processID,int[] respondertime) throws RemoteException {
		int[] timeStamp=updateClock(respondertime);
		log.registerEvent(new Transaction("Received Ok from "+ processID, timeStamp, 0, amount));
		System.out.println("Received Ok from"+processID+" Event registered at Local Time "+Arrays.toString(timeStamp));
		okMessage.add(processID);
		System.out.println("Total Oks"+okMessage.size());

		
		
	}
	
	/**
	 * Once all the requirements for Critical section is made then this method will be executed
	 * by Critical Section Executer thread.
	 */
	private void critcalSection()
	{

		Random aRandom = new Random();
		System.out.println("Inside Critical Section");
		try {
			Thread.sleep(aRandom.nextInt(1000)+1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	/**
	 * This method will empty the que, after the execution of Critical Section
	 * @throws RemoteException
	 */
	private void closingTask() throws RemoteException
	{
		//current thread is done with CS
		//It will check que and respond to them
		int[] timeNow=vectorClock;
		System.out.println("Released the Critical Section");
		for( int requester : pendingRequst)
		{		
		System.out.println("Empting Que by Sending ok to: "+requester);
		getProcess(PROCESS[requester-1]).reponseTokenRequest(processID,timeNow);}
		//cleaning the que
		pendingRequst.clear();
		
		//it will clean csSeeking transaction object
		
		this.seekingCS=null;
		//cleaning Ok messages
		this.okMessage.clear();
		//it will change it status
		updateThreadState("RELEASED");
		
	}
/**
 	* This method will be executed by Critical Section Seeker Thread for achieving critical section 	
 * @throws RemoteException
 */
	private void lookingForCriticalSection() throws RemoteException
	{   Random randomWait = new Random();
		int[] timeStamp=updateClock();
		seekingCS = new Transaction("Seeking CS",(timeStamp),0,amount);
		log.registerEvent(seekingCS);
		System.out.println("\nSeeking CS Since: "+Arrays.toString(seekingCS.timeStamp));

		for(int counter=0;counter<processCount;counter++)
		{if(counter!=this.processID-1){
			try {
				Thread.sleep(randomWait.nextInt(1));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Sending requests to "+ (counter+1));
			getProcess(PROCESS[counter]).tokenRequest(processID, seekingCS.timeStamp,vectorClock);}
		}
	}
/**
 	* For updating the status of Thread	
 * @param status
 */
	private  void  updateThreadState(String status)
	{
		synchronized(stateLock){
		this.processState=status;}
	}
	/**
	 * This thread seeks/create opportunities for getting Critical Section 
	 */
	
	public void criticalSectionSeeker(){
		Thread criticalSectionSeeker = new Thread(new Runnable() {
			Random randomWait = new Random(); 
			public void run() {
				while(true){
				try {
					Thread.sleep(randomWait.nextInt(1300)+35000);
					if(processState.equals("RELEASED") && seekingCS==null )
						{updateThreadState("WANTED");
						lookingForCriticalSection();}
					
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
	 * Once the requirements for accessing Critical Sections are met, This Thread take
	 * the Process to critical Section. 
	 */
	
	public void criticalSectionExecutor(){
		Thread criticalSectionExecutor = new Thread( new Runnable() {
			
			public void run() {
			while(true)
			{
				if(processState.equals("WANTED") && okMessage.size()==processCount-1)
				{
					System.out.println("All requirments Done!! ready to go Critical section");
					updateThreadState("HELD");
				   	critcalSection();
				   	try {
						closingTask();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   
				}
			}
				
			}
		});
		
		criticalSectionExecutor.start();
	}
	/**
	 * This thread resolve the Request received from external processes 
	 */
	
	public void requestResolver()
	{
		Thread requestResolverThread = new Thread(new Runnable() {
			public void run() {
				System.out.println("Activating Token Request Resolver,may take some Time," +
						"Receiving Request Before the Activation may Result in DeadLock");
				while(true)
				{	try{
					synchronized(tokenLock){
						
						tokenLock.wait();
							
						processTokenRequest(currentRequest.processID, Arrays.copyOf(currentRequest.time,currentRequest.time.length ),currentRequest.localTime);
						currentRequest=null;
					}
					}catch (Exception e) {
						System.out.println("Errro");
					}
				}
					
				}
				
			
		});
		requestResolverThread.start();
		
	}
}
