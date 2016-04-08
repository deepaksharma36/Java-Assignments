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
	
	//public static final int REPLICATIONCOUNT=5;
	 
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
	public boolean deposit(int amount, String message) throws RemoteException;
	/**
	 * This method update the clock for peer to peer transaction using clock of each peer.
	 * @param vectorClock an array consist of vector clock for each process
	 * @throws RemoteException
	 */
	public void updateClock(int[] vectorClock) throws RemoteException;
}
