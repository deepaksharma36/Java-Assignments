import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Random;

/**
 * 
 * @author deepak Sharma ds5930
 *
 */
public class Process extends UnicastRemoteObject implements ProcessInterface {
	volatile int amount=1000;
	volatile boolean snapShotState;
	volatile boolean withdrawalFlag;
	volatile int markerFlag;
	volatile int receivedAmount;
	volatile int moneySenderID;
	
	 final int processID;
	 public int getProcessID() {
		return processID;
	}

	final int processeCount;
	public int getProcesseCount() {
		return processeCount;
	}

	private final Object lock1 = new Object();
	private final Object lock2 = new Object();
	private final Object markerLock = new Object();
	private final Object moneyReceiveLock = new Object();
	private final Object snapshotLock = new Object();
	String RMIport;	
	Snapshot snapshot=null;
	int snapShotInvoker=-1;
	boolean closeTransactionFlag=false;
	LinkedHashSet<Integer> markers;
	HashMap<String, ProcessInterface> remoteObjectStorage;
	ArrayList<Snapshot> snapShotLog=new ArrayList<Snapshot>();
	
	
	Process(int processID,int processCount,String RMIport) throws RemoteException {
		
		this.RMIport=RMIport;
		markers = new LinkedHashSet<Integer>();
		this.processID=processID;
		remoteObjectStorage = new HashMap<String, ProcessInterface>();
		snapShotState = false;
		this.processeCount=processCount;
		this.withdrawalFlag=true;
		this.markerFlag=-1;
		
	}
	
	public boolean sendMoney(int processID,int amount){
			//System.out.println("Sending Money to "+processID);
			if(withdraw(amount)){
				try {
				if(getProcess(PROCESS[processID-1]).receiveMoney(amount,this.processID))
						return true;
				} catch (RemoteException e) {
					e.printStackTrace();
				
				}
		}
		return false;
	}
	
	private  boolean withdraw(int withdrawAmount){
		while(!withdrawalFlag){
		
		}
			// while sending markers, no withdrawal should take place
			// a withdrawal can take  place before or after marker sending process
			//System.out.println("taking out:"+withdrawAmount);
			return updateMoney(-1*withdrawAmount);
		}	
	
	
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


	public boolean receiveMoney(int depositAmount, int processID) throws RemoteException {
			/*synchronized(moneyReceiveLock){
			receivedAmount=depositAmount;
			moneySenderID=processID;
			moneyReceiveLock.notify();
			return true;}*/
		synchronized(lock2){
			// receive money should be synchronized with updating method for snapShotState
			//System.out.println("Receiving Money"+ depositAmount +" from "+processID);	
			if(snapShotState && processID != snapShotInvoker && !closeTransactionFlag ){
				//System.out.println("This part is not executing");
				updateIncommingChannel(depositAmount,processID);
			}
			}
			return updateMoney(depositAmount);
	}
	private boolean receiveMoneyManager() throws RemoteException{
		
		synchronized(moneyReceiveLock){
			try {
		while(true){		
				moneyReceiveLock.wait();
			
		int depositAmount = receivedAmount;
		int processID = moneySenderID;
		//synchronized(lock2){
			// receive money should be synchronized with updating method for snapShotState
			//System.out.println("Receiving Money"+ depositAmount +" from "+processID);	
			if(snapShotState){
				System.out.println("This part is not executing");
				updateIncommingChannel(depositAmount,processID);
			}
			//}
			return updateMoney(depositAmount);}} 
		catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}}
	}
	private void updateIncommingChannel(int arrivingAmount, int processID)
	{	//System.out.println(arrivingAmount+"Channel Amount");
		HashMap<Integer, ChannelSnapShot> incommingChannelSnaps = snapshot.getChannelAmount();
		if(incommingChannelSnaps.containsKey(processID))
			incommingChannelSnaps.get(processID).updateAmount(arrivingAmount);
		
	}
	private void initIncommingChannel()
	{
		for(int counter=0;counter<processeCount;counter++){
			 
			snapshot.getChannelAmount().put(counter+1, new ChannelSnapShot(0, PROCESS[counter]));
		}
		
	}
	public void snapShot() throws RemoteException
	{
		
		
		//System.out.println("SnapShot method invoked");
		//int sanpSmount;
		if(!snapShotState){
			synchronized(lock2){
				synchronized(lock1){withdrawalFlag=false;}
				synchronized(this){
					snapShotState=true;}
			}
			System.out.println("**************Intiating a SnapShot**********");
			// amount should not change while setting flag and noting amount
			// this lock has also been used for update amount.
				
				synchronized (this) {
				snapshot =new Snapshot(amount);}
				initIncommingChannel();
				sendMarkers();
				synchronized(lock1){
					withdrawalFlag=true;
				}	
		
		}
		
		
	}
	private void checkSnapShotStatus()
	{	//System.out.println("checking snapShot Status");
		if(markers.size()>=processeCount-1 && snapshot!=null)
		{
			System.out.println("Snapshot has been complited now");
			// receive money which check for snapshotstate should be sync with update method	
			synchronized(lock2){
			snapShotState=false;
			System.out.println(snapshot);
			snapShotLog.add(snapshot);
			snapshot=null;
			markers.clear();
			snapShotInvoker=-1;
			closeTransactionFlag=false;}
			
				 
		}
			
		//System.out.println("SnapShot Status has been checked");
	}
	public void receiveMarker (int fromProcessID) throws RemoteException
	{
	
		synchronized(markerLock){
			if (snapShotInvoker==-1)
				snapShotInvoker=fromProcessID;	
		markers.add(fromProcessID);
		markerLock.notify();}
		if(markers.size()==processeCount)
			closeTransactionFlag=true;
		System.out.println("Received Marker from  "+fromProcessID);
		System.out.println("number of Markers:" +markers.size());
		//synchronized(markerLock){
			//System.out.println("RMI call has Invoked receiveMarker method");	
			//markerFlag=fromProcessID;
			//System.out.println(markerFlag);
			//while(markerFlag>0)
			//markerLock.notify();
		//}
	}
	private void receiveMarkerManger() throws RemoteException{
		try {
			int fromProcessID;
			synchronized(markerLock){
			while(true){
				//System.out.println("Marker Receiver manger is Waiting for notify");
				markerLock.wait();
				fromProcessID=markerFlag;
		
				markerFlag=-1;
				//System.out.println("Received Markers Code Block is Executing");
				//snapShot();
				
				markers.add(fromProcessID);
				System.out.println("Received Marker from  "+fromProcessID);
				//checkSnapShotStatus();
			
		}
		} }catch (Exception e) {

			e.printStackTrace();
		}
	}
	private void sendMarkers () throws RemoteException
	{	
		
		//synchronizing with withdrawal process as marker should be placed
		// before any future withdrawal
		
		for(int processCounter=0;processCounter<processeCount;processCounter++)
		{	if (processCounter+1!=processID) //preventing from downloading Stub of itself
			{System.out.println("Sending Markers to "+ (processCounter+1)+" "+PROCESS[processCounter]);
/*			try {
				Random rn = new Random();
			Thread.sleep(rn.nextInt(1000));
				
			} catch (InterruptedException e) {
		
				e.printStackTrace();
			}*/
			getProcess(PROCESS[processCounter]).receiveMarker(processID);
			System.out.println("Marker Sent "+ (processCounter+1));
			}
		}
	}
	
	private synchronized boolean updateMoney(int amount)
	{	if(this.amount+amount>0)
		{this.amount+=amount;
		return true;}
	else 
		return false;
		
		
	}

	public void snapShotexecuter()  {
		
		Thread snapShot = new Thread( new Runnable() {
			
			public void run() {
				try {
					//snapShotTerminator();
					while(true){
						
					Thread.sleep(2000);
					//System.out.println("Process 1 is Starting a SnapShot");
					//synchronized(snapshotLock){	
						try {
							if(markers.size()>=2 )
							{
							  checkSnapShotStatus();
							  //break;
							}	
							synchronized(snapshotLock){
							if(!snapShotState)
							snapShot();}
							
							//snapshotLock.notify();
							//snapshotLock.wait();
				
						} catch (RemoteException e) {

							e.printStackTrace();
						}}
					}
				//}
				 catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		snapShot.start();
		
		
	}
	
	public void transactionExecuter()  {
		Thread transaction = new Thread(new Runnable() {
			
			public void run() {
				
					try {
						while(true){	
						Random r = new Random();
						int tAmount=r.nextInt(100)+1;
						
						int processNumber;
						do{
						 processNumber=r.nextInt(getProcesseCount())+1;}
						while(processNumber==getProcessID());
						sendMoney(processNumber, tAmount);
						
							Thread.sleep(1000);
						}} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
				
			}
				
			
		});
		transaction.start();
	}
	
	public void markerExecuter(){
		Thread markerExecuter = new Thread(new Runnable() {
			
			public void run() {
				try {
					receiveMarkerManger();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
		});markerExecuter.start();
	}
	
	public void moneyReceiveExecuter(){
		Thread moneyReceiveExecuter = new Thread(new Runnable() {
			
			public void run() {
				try {
					receiveMoneyManager();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
		});moneyReceiveExecuter.start();
	}
	
	public void snapShotExecuterpassive()
	{
		Thread snapShot = new Thread(new Runnable() {
			
			public void run() {
			try {
			//synchronized(snapshotLock){
				int size;
				while(true){
				//System.out.print(markers.size());	
					//System.out.println("This is executing");
					//snapShotTerminator();
					synchronized(markerLock){
						//System.out.println("Snap Shot Executer on wait");
						//markerLock.wait();
						//System.out.println("Marker Lock has been notified");
					size=markers.size();}
					if(size>0 )
					{
						if(size>=2 )
						{
						  checkSnapShotStatus();	
						}	
					//System.out.println("Intiating a snap shot using thread");
					synchronized(snapshotLock){	
					snapShot();}
					}
					
					//checkSnapShotStatus();
					//snapshotLock.notify();
					//snapshotLock.wait();
					//System.out.println("snapShot Thread has been compited");
					}
				
					}//}
			catch (Exception e) {
				
				e.printStackTrace();
			}
				
			}
		});
		snapShot.start();
	}

	public void snapShotTerminator(){
		
		Thread terminator = new Thread(new Runnable() {
			
			public void run() {

	                 
				while(true)		
						checkSnapShotStatus();
						
					
				

				
			}
		});
		terminator.start();
		
	

}
}
//kill `ps -ef | grep java | grep ds5930 | cut -c 10-15`