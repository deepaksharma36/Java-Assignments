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
public interface ServerInterface extends Remote {
	public static final int NUMBEROFSERVER=7;
	public static final int REPLICATIONCOUNT=5;
	 
	/*public static final String[] SERVERS = { "glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu",
		"glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu",
		"glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu" };*/
	public static final String[] SERVERS = { "glados.cs.rit.edu", "kansas.cs.rit.edu", "doors.cs.rit.edu", "gorgon.cs.rit.edu",
			"newyork.cs.rit.edu", "yes.cs.rit.edu", "kinks.cs.rit.edu", "medusa.cs.rit.edu", "joplin.cs.rit.edu",
			"delaware.cs.rit.edu", "buddy.cs.rit.edu", "arizona.cs.rit.edu" };
	/**
	 * This method will be invoked by Server while copying 'file' on on other Server location
	 * @param file name of the file to be copied on client 
	 * @param data byte array of file contents. 
	 * @throws RemoteException
	 */
	
	public void insertFile(String file, byte[] data) throws RemoteException;
	/**
	 * This method will be invoked by Client in order to inquiring for file 
	 * @param name name of the File inquired by client
	 * @param destination Client's remote object from which request has been received
	 * @param requestCode array containing coordinates of the Server 
	 * @throws RemoteException
	 */
	public void getFile(String name, ClientInterface destination,int[] requestCode) throws RemoteException;
}
