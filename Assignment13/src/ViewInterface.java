import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This is a remote interface for illustrating RMI server.
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 *
 */
public interface ViewInterface  extends Remote {
	
	public void showOutput(Object s) throws RemoteException;
	public int userInput() throws RemoteException;
}
