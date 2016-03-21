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
	
	//public static final int REPLICATIONCOUNT=5;
	 
	/*public static final String[] SERVERS = { "glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu",
		"glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu",
		"glados.cs.rit.edu", "glados.cs.rit.edu", "glados.cs.rit.edu" };*/
	public static final String[] SERVERS = { "glados.cs.rit.edu", "kansas.cs.rit.edu", "doors.cs.rit.edu", "gorgon.cs.rit.edu",
			"newyork.cs.rit.edu", "yes.cs.rit.edu", "kinks.cs.rit.edu", "medusa.cs.rit.edu", "joplin.cs.rit.edu",
			"delaware.cs.rit.edu", "buddy.cs.rit.edu", "arizona.cs.rit.edu" };
	

	/**
	 * Finding the server in chord which has id Space
	 * @param idSpace
	 * @param request server which has enquired about Id space
	 * @return call back object of the server which take care of giving id Space
	 * @throws RemoteException
	 */
	public ServerInterface findDestinationServer(int idSpace, ServerInterface request) throws RemoteException;
	/**
	 * Method for leaving chord, by invoking this method node can leave it self
	 * @throws RemoteException
	 */
	public void leave() throws RemoteException;
	/**
	 * when a node call add method for joining chord this method forward it request to various peer 
	 * while find ID space. 
	 * @param HostName
	 * @throws RemoteException
	 */
	public void addNode(String HostName)throws RemoteException;
	/**
	 * Remote server call this method for sending file to destination
	 * @param keyWord 
	 * @param file
	 * @param data
	 * @throws RemoteException
	 */
	public void insertFile(String keyWord, String file, byte[] data) throws RemoteException;
	/**
	 * When remote sever find error in request then it invoke this method of requester for updates
	 * regarding his request.
	 * @param error
	 * @throws RemoteException
	 */
	public  void Error(String error) throws RemoteException;
	/**
	 * remote server can get the range of other peer
	 * @return array of range
	 * @throws RemoteException
	 */
	public int[] getRange() throws RemoteException ;
	/**
	 * remote server can set range of its peer
	 * @param range
	 * @throws RemoteException
	 */
	public void setRange(int[] range) throws RemoteException ;
	/**
	 * Remote server can set predecessor of its peer
	 * @param predecessor
	 * @throws RemoteException
	 */
	public void setPredecessor(String predecessor) throws RemoteException ;
	/**
	 * for getting predecessor of peer
	 * @return
	 * @throws RemoteException
	 */
	public String getPredecessor() throws RemoteException ;
	/**
	 * for sending trails to the peer which made the request for Searching a file in chord
	 * @param hostName name of the intermidiate or destination server
	 * @throws RemoteException
	 */
	void updateSearchRequest(String hostName) throws RemoteException;
	/**
	 * to get successor of a remote peer
	 * @return
	 * @throws RemoteException
	 */
	public String getSuccessor() throws RemoteException ;
	/**
	 * when a new node join the cord, this method is called by its predecessor
	 * @param predecessor 
	 * @param successor
	 * @param minRange
	 * @param maxRange
	 * @throws RemoteException
	 */
	public void updateNewNode(String predecessor, String successor,int minRange,int maxRange ) throws RemoteException;
	/**
	 * to set the successor
	 * @param successor
	 * @throws RemoteException
	 */
	public void setSuccessor(String successor) throws RemoteException;
	/**
	 * remote sever send file to the server which has requested for the same
	 * @param keyWord
	 * @param destination
	 * @throws RemoteException
	 */
	public void sendFile(String keyWord, ServerInterface destination) throws RemoteException;
	/**
	 * for storing trail of request server while joining the chord
	 * @param hostName
	 * @throws RemoteException
	 */
	public void updateAddRequestString(String hostName) throws RemoteException;
	/**
	 * for storing trial of request server while adding file in chord
	 * @param hostName
	 * @throws RemoteException
	 */
	public void updateFileAddRequestString(String hostName) throws RemoteException;
	
	
	public void testLeave() throws RemoteException;
	public String testView() throws RemoteException;
	public String testAdd() throws RemoteException;
	public String testInsert(String Keyword ,String testFile) throws RemoteException;
	public String testSearch(String keyword) throws RemoteException;
}
