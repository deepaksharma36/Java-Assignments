import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.HashMap;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;


/**
 * This is an implementation of the bull Election Mechanism, rules of election
 * each processor will participate in each election carried out result of each
 * election will be same, all server will have same leader or they have no
 * leader
 * 
 * @author sharma, deepak
 * 
 */

public class Process extends UnicastRemoteObject implements ProcessInterface {

	int processID;
	int processCount;
	volatile int leader = 1;
	volatile int electionCounter = 0;
	ElectionMessage electionMessage;
	volatile boolean processState = true;
	volatile boolean waitingCoordi = false;
	volatile boolean response = false;
	String RMIport;
	volatile boolean election = false;
	Object stateLock = new Object();
	Object timeKeeper = new Object();
	Object electionMessageLock = new Object();
	Object leaderMonitor = new Object();
	Object coordiResponseTimeKeeper = new Object();
	Object noCoordiResponseTimeKeeper = new Object();
	Object heartBeatLock = new Object();
	// Queue<Integer> electionResponses;
	Queue<ElectionMessage> electionMessageQue = new LinkedList<ElectionMessage>();
	HashSet<Integer> electionID = new HashSet<Integer>();
	HashMap<String, ProcessInterface> remoteObjectStorage;
	HashSet<Integer> crashedServers = new HashSet<Integer>();

	/**
	 * Constructor of process
	 * 
	 * @param processCount
	 *            Number of process available
	 * @param processID
	 *            Id for process
	 * @param RMIport
	 *            Port number for communicating with peers
	 * @throws RemoteException
	 */
	Process(int processID, int processCount, String RMIport)
			throws RemoteException {

		this.processID = processID;
		this.processCount = processCount;
		this.RMIport = RMIport;
		remoteObjectStorage = new HashMap<String, ProcessInterface>();
		// this.electionResponses = new LinkedList<Integer>();
	}

	/**
	 * For accessing the remote Process this method provide STUB using RMI
	 * 
	 * @param hostName
	 *            Name of the Remote Process
	 * @return Stub of Remote Process
	 * @throws RemoteException
	 */
	private ProcessInterface getProcess(String hostName) throws RemoteException {

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
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * State of the server change between dormant and being alive
	 * 
	 * @param status
	 */
	private void updateProcessState(boolean status) {
		synchronized (stateLock) {
			this.processState = status;
		}
	}

	public void electionMessage(ElectionMessage requister)
			throws RemoteException {

		synchronized (electionMessageLock) {
			System.out.println("Election Message Has Received from:"
					+ requister.processID + " with message ID"
					+ requister.MessageID);
			electionMessageQue.add(requister);

			electionMessageLock.notify();
		}

	}

	/**
	 * Election Monitor Thread use this method to resolve election message
	 * received at it ends
	 * 
	 * @param requester
	 */
	private void electionMessageProcessor(ElectionMessage requester) {
		String messageID;
		if (processState && !crashedServers.contains(requester.processID)) {
			System.out.println("Processing Election Message "
					+ requester.MessageID + " ,sending Response to: "
					+ requester.getProcessID());
			try {
				this.getProcess(PROCESS[requester.getProcessID() - 1])
						.electionResponse(
								new ElectionMessage(processID,
										requester.MessageID));
			} catch (RemoteException ex) {
				crashedServers.add(requester.processID);
			}
			// && !electionID.contains(requester.getMessageID())
			if (!election) {
				electionID.add(requester.MessageID);
				messageID = "" + this.processID + "" + (this.electionCounter++);
				electionMessage = new ElectionMessage(processID,
						Integer.parseInt(messageID));
				initiateElection(electionMessage);
			}
		}

	}

	public void coordinatorMessage(ElectionMessage leader)
			throws RemoteException {
		System.out.println("Recieved Coordinator Message from, "
				+ leader.processID);
		String messageID;
		if (this.processState) {
			System.out.println("processing Coordinator Message from, "
					+ leader.processID + " Election Over");
			this.leader = leader.getProcessID();
			// this.election=false;
			// this.electionResponses.clear();
			// token
			// this.electionResponses.add(-1);
			this.electionID.add(leader.MessageID);
			if (waitingCoordi) {
				waitingCoordi = false;
				//

				synchronized (noCoordiResponseTimeKeeper) {
					System.out.println("coordinator monitor thread notified, set waiting flag to false");
					noCoordiResponseTimeKeeper.notify();
				}
			}
		} else if (this.processState && this.processID >= leader.processID) {
			messageID = "" + processID + "" + (electionCounter++);
			electionMessage = new ElectionMessage(processID,
					Integer.parseInt(messageID));
			initiateElection(electionMessage);

		}
	}

	public boolean heartBeatChecker() throws RemoteException {
		synchronized (heartBeatLock) {
			try {
				if (this.processState)
					return true;
				else
					return false;
			} catch (Exception ex) {
				return false;
			}
		}
	}

	/**
	 * When process decides to initiate election it invokes this method
	 * 
	 * @param request
	 */
	private void initiateElection(ElectionMessage request) {
		// ElectionMessage electionMsg = new ElectionMessage(processID,
		// request.MessageID);

		election = true;
		leader = -1;
		response = false;
		System.out.println("Election Start sending Election Messages with ID: "
				+ request.MessageID);
		// this.electionResponses.clear();

		this.electionMessage = request;

		for (int counter = processID + 1; counter <= processCount; counter++) {
			if (!crashedServers.contains(counter)) {
				try {
					this.getProcess(PROCESS[counter - 1]).electionMessage(
							request);
				} catch (RemoteException ex) {
					crashedServers.add(counter);
				}
			}
		}
		synchronized (timeKeeper) {
			System.out.println("Notified the Election Monitor");
			timeKeeper.notify();
		}
	}

	public void electionResponse(ElectionMessage response)
			throws RemoteException {
		// if I received a single Response I will wait for Coordinator till Time
		// Out
		System.out.println("Received Ele Response from: " + response.processID
				+ " for election Message: " + response.MessageID);
		// this.electionResponses.add(response.getProcessID());
		// first message, no coordination message
		// electionResponses.size()==1 && !electionResponses.contains(-1) &&
		// processID<processCount &&
		// response.MessageID==this.electionMessage.MessageID
		this.response = true;
		if (!waitingCoordi) {
			synchronized (timeKeeper) {
				// notify Election Monitor from 5000 wait state,
				// Election Monitor will notify Coordination Monitor
				// election=false;
				waitingCoordi = true;
				timeKeeper.notify();
			}
		}

	}

	/**
	 * Coordinator message monitor send coordinator message when time out occur
	 * or process with largest ID invoke method in response to election message
	 * 
	 * @param aElectionMessage
	 * @return
	 */
	private boolean sendCoordinatorMessage(ElectionMessage aElectionMessage) {
		// if(electionResponses.size()==0)
		{
			for (int counter = 0; counter < processCount; counter++) {
				if (!crashedServers.contains(counter + 1)) {
					try {
						System.out.println("Sending Coordinator Message to: "
								+ (counter + 1));
						if (counter + 1 != processID)
							getProcess(PROCESS[counter]).coordinatorMessage(
									aElectionMessage);
						else
							coordinatorMessage(aElectionMessage);
					} catch (RemoteException ex) {
						crashedServers.add(counter + 1);
					}
				}
			}
			System.out
					.println("\t Completed The task of Sending Coordinator Messages");
			election=false;
			return true;
		}
		/*
		 * else { System.out.println("Not Sending Coordinator Message"); return
		 * false; } }
		 */
	}

	/**
	 * A Thread which resolve election messages
	 */
	public void electionMonitor() {
		Thread electionMonitor = new Thread(new Runnable() {

			public void run() {
				while (true) {
					try {
						synchronized (timeKeeper) {
							timeKeeper.wait();
							timeKeeper.wait(5000);
						}
						
						if (!response) {
							sendCoordinatorMessage(electionMessage);

							System.out
									.println("Election Moni:Timeout occur No Response Received, Sending Coordinator");
						} else {
							synchronized (coordiResponseTimeKeeper) {
								System.out
								.println("election Moni: Response Received notifing coordi monitor ");
								coordiResponseTimeKeeper.notify();
							}
						}
					}

					catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}

		});
		electionMonitor.start();
	}

	/**
	 * Election Coordinator thread which keep an eye on coordinator responses if
	 * process had received an election response previously
	 */
	public void coordinatorMessageMonitor() {
		Thread coordinatorMessageMonitor = new Thread(new Runnable() {

			public void run() {
				String messageID;
				while (true) {
					synchronized (coordiResponseTimeKeeper) {
						try {
							coordiResponseTimeKeeper.wait();

							synchronized (noCoordiResponseTimeKeeper) {
								noCoordiResponseTimeKeeper.wait(5000);
							}

							if (waitingCoordi) {
								System.out
										.println("Cordi Moni: Time Out has occured, no Coordination message Received");
								System.out.println("intiating Election");
								messageID = "" + processID + ""
										+ (electionCounter++);
								initiateElection(new ElectionMessage(processID,
										Integer.parseInt(messageID)));
							} else{
								System.out.println("Cordi Moni: Coordination message was Received, Election official Over");
												
								election = false;
								
							}

						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}

				}
			}
		});

		coordinatorMessageMonitor.start();

	}

	/**
	 * A Thread which keep an eye on the heart beat of leader(if apply)
	 */
	public void leaderHeartBeatMonitor() {
		Thread leaderHeartBeatMonitor = new Thread(new Runnable() {
			public void run() {

				String messageID;
				Random randomWait = new Random();

				while (true) {
					try {
						Thread.sleep(3000 + randomWait.nextInt(5000));

						if (leader != -1 && processID != leader) {
							if (!getProcess(PROCESS[leader - 1])
									.heartBeatChecker() && !election) {
								System.out
										.println("Detected Issue,leader is sleeping:"
												+ leader);
								messageID = "" + processID + ""
										+ (electionCounter++);
								System.out.println("Intiating A Election");
								electionMessage = new ElectionMessage(
										processID, Integer.parseInt(messageID));
								initiateElection(new ElectionMessage(processID,
										Integer.parseInt(messageID)));
							}
						}
					} catch (RemoteException e) {
						// e.printStackTrace();
						System.out
								.println("\n**************** No RESPONSE FROM LEADER, LEADER HAS CRASHED, intitating election");
						crashedServers.add(leader);
						messageID = "" + processID + "" + (electionCounter++);
						electionMessage = new ElectionMessage(processID,
								Integer.parseInt(messageID));
						initiateElection(electionMessage);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		});
		leaderHeartBeatMonitor.start();
	}

	/**
	 * A user thread which flip the state of process
	 */
	public void stateFliper() {
		Thread stateMonitor = new Thread(new Runnable() {

			String userInput;

			public void run() {
				System.out
						.println("*State Fliper thread* Press s for sleep w for getting awake");
				String messageID;
				Scanner sc = new Scanner(System.in);
				while (true) {
					try {
						System.out.println("Ready: ");
						userInput = sc.nextLine();
						if (userInput.equalsIgnoreCase("S")) {
							System.out.println("Swiching To sleep State");
							updateProcessState(false);
							leader = -1;
						} else if (userInput.equalsIgnoreCase("W")) {
							if (!processState) {
								updateProcessState(true);
								messageID = "" + processID + ""
										+ electionCounter++;
								initiateElection(new ElectionMessage(processID,
										Integer.parseInt(messageID)));
								System.out.println("Thread Has Returned");
							}
						} else if (userInput.equalsIgnoreCase("l"))
							System.out.println("Leader: " + leader);
					} catch (Exception Ex) {
						Ex.printStackTrace();
					}
				}
			}
		});
		stateMonitor.start();
	}

	/**
	 * Internal Thread for managing election messages, notify election monitor
	 */
	public void electionMessageResponder() {
		Thread electionMessageResponder = new Thread(new Runnable() {

			public void run() {
				while (true) {
					synchronized (electionMessageLock) {
						try {
							if (electionMessageQue.size() == 0)
								electionMessageLock.wait();
							electionMessageProcessor(electionMessageQue.poll());
						} catch (InterruptedException e) {
						}
					}
				}
			}
		});
		electionMessageResponder.start();
	}

}
