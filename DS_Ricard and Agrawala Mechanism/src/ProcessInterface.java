//import files are places here.
import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This is a remote interface for illustrating RMI peer.
 * 
 * @author Sharma, Deepak DS5930
 * 
 */
public interface ProcessInterface extends Remote {
	
	
	 
	/*public static final String[] SERVERS = { "glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu",
		"glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu",
		"glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu" };*/
	
public static final String[] PROCESS = { "glados.cs.rit.edu", "kansas.cs.rit.edu",  "gorgon.cs.rit.edu","doors.cs.rit.edu",
			"newyork.cs.rit.edu", "yes.cs.rit.edu", "kinks.cs.rit.edu", "medusa.cs.rit.edu", "joplin.cs.rit.edu",
			"delaware.cs.rit.edu", "buddy.cs.rit.edu", "arizona.cs.rit.edu" };
	

	/**
	 * 
	 * @param amount for sending/depositing
	 * @param message 
	 * @return
	 * @throws RemoteException
	 */
	public boolean deposit(int amount, String message,int[] time) throws RemoteException;

	/**
	 * This method will be invoked by remote server/process, when it will be seeking Critical Section
	 * The process will take necessary action according to its state and Vector Clock
	 * @param requistersID Id of the invoker/seeker of CS
	 * @param time Time since it is seeking Critical Section
	 * @throws RemoteException
	 */
	public void tokenRequest(int requistersID, int[] time, int[] vectorClock) throws RemoteException;
	/**
	 * This method will be invoked for sending ok to the remote server who is seeking CS
	 * @param processID Id of the Remote Server/ sender of the OK
	 * @param respondertime, local TIme of responser for updating the recipient time for vector clock  
	 * @throws RemoteException
	 */
	public void reponseTokenRequest(int processID,int[] respondertime ) throws RemoteException;
}
