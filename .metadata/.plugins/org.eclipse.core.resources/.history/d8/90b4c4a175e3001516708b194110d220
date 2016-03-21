import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This is a remote interface for illustrating RMI server.
 * @author Sharma, Deepak DS5930

 *
 */
public interface ClientInterface  extends Remote {
	public static final int NUMBEROFSERVER=7;
	
	/*public static final String[] SERVERS = { "glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu",
		"glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu",
		"glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu" };*/
	public static final String[] SERVERS = { "glados.cs.rit.edu", "kansas.cs.rit.edu", "doors.cs.rit.edu", "gorgon.cs.rit.edu",
		"newyork.cs.rit.edu", "yes.cs.rit.edu", "kinks.cs.rit.edu", "medusa.cs.rit.edu", "joplin.cs.rit.edu",
		"delaware.cs.rit.edu", "buddy.cs.rit.edu", "arizona.cs.rit.edu" };
	//public static final String ClientIP="129.21.88.98";
	/**
	 * This remote method will be called by server for copying file at Client using remote object.
	 * @param data Byte array containing File data
	 * @param name Name of the File
	 * @param Server Name of the server
	 * @throws RemoteException
	 */
	public void getFile(byte[] data, String name,String server) throws RemoteException;
	/**
	 * Servers will update request trail by invoking this remote method
	 * @param request Name of the Server which is serving the file request.
	 * @throws RemoteException
	 */
	public void  updateRequestTrail(String request) throws RemoteException;
	/**
	 * Severs invoke this method for passing message to client Ex File does not exist 
	 * @param error Message contains
	 * @throws RemoteException 
	 */
	public void Error(String error) throws RemoteException; 
		
	
}
