import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.plaf.SliderUI;

/**
 * All functionalities: Deposit WithDraw and SendMoney of process has been implemented in this class.
 *  
 * @author sharma, deepak
 *
 */

public class Process extends UnicastRemoteObject implements ProcessInterface, Runnable{
	
	
	volatile private int[] vectorClock;
	volatile int amount=1000;
	TransactionsLog log;
	int processID;
	int processCount;
	String RMIport;
	/**
	 * Constructor or process
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
			amount-=withdrawAmount;
			vectorClock[processID-1]++;
			log.registerEvent
			(new Transaction("withdraw "+message, Arrays.toString(vectorClock), withdrawAmount, amount));
		}
		return true;
		}
		System.out.println("Insuficiant Fund");
		return false;
	}


	public boolean deposit(int depositAmount,String message) throws RemoteException{
		synchronized(this){
			amount+=depositAmount;
			vectorClock[processID-1]++;
			log.registerEvent
			(new Transaction("deposit"+message, Arrays.toString(vectorClock), depositAmount, amount));
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
					getProcess(PROCESS[processID-1]).updateClock(vectorClock);
				
				if(getProcess(PROCESS[processID-1]).deposit(amount,"CR from "+processID))
					return true;
				} catch (RemoteException e) {
				
					e.printStackTrace();
					return false;
				}}
		}
		return false;
	}
	
	public void updateClock(int[] vectorClock) throws RemoteException{
		for(int counter=0;counter<processCount;counter++)
		{
			if(this.vectorClock[counter]<vectorClock[counter])
				this.vectorClock[counter]=vectorClock[counter];
		}
	}

	private ProcessInterface getProcess(String hostName) throws RemoteException{
		
		String registryURL = "rmi://" + hostName + ":" + RMIport + "/server";
		try {
			ProcessInterface aProcess = (ProcessInterface) Naming
					.lookup(registryURL);
			return aProcess;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method execute random transactions for showing the working of the vector clock
	 */
	public void run() {
		try{
		System.out.println("Process has started");	
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
			deposit(tAmount,"Self CR");
			log.show();
			break;
		case 2:
			System.out.println("\t\t\t\tSending money "+tAmount+" to "+processNumber);
			sendMoney(processNumber, tAmount);
			log.show();
			break;
		case 3:
			System.out.println("\t\t\t\twithdrawing money "+tAmount);
			withdraw(tAmount,"Self DR");
			log.show();
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
	
}
