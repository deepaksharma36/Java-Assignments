//import files are places here.
import java.rmi.Remote;

/**
 * This is a remote interface for illustrating RMI client.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 * 
 */
public interface GameServerInterface extends Remote {

	public boolean registration(String Name, String Choice,
			ViewInterface aViewInterface) throws java.rmi.RemoteException;

}
