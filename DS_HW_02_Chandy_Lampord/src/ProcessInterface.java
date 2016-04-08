//import files are places here.
import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This is a remote interface for illustrating RMI client.
 * 
 * @author Sharma, Deepak DS5930
 * 
 */
public interface ProcessInterface extends Remote {
	
	//public static final int REPLICATIONCOUNT=5;
	 
	/*public static final String[] SERVERS = { "glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu",
		"glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu",
		"glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu" };*/
	public static final String[] PROCESS = { "glados.cs.rit.edu", "kansas.cs.rit.edu", "newyork.cs.rit.edu"};
			//"newyork.cs.rit.edu", "yes.cs.rit.edu", "kinks.cs.rit.edu", "medusa.cs.rit.edu", "joplin.cs.rit.edu",
			//"delaware.cs.rit.edu", "buddy.cs.rit.edu", "arizona.cs.rit.edu" };
	/**
	 * For receiving money from remote process
	 * @param amount
	 * @param processID
	 * @return
	 * @throws RemoteException
	 */
	public boolean receiveMoney(int amount, int processID) throws RemoteException;
	/**
	 * For receiving marker from remote process
	 * @param processID id of remote process
	 * @throws RemoteException
	 */
	public void receiveMarker (int processID) throws RemoteException;
	
}
