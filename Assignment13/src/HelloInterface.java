//import files are placed here.
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * this helloInterface is a remote interface for illustrating RMI client helloC.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 * 
 */
public interface HelloInterface extends Remote {

	public boolean test(String aString) throws RemoteException;
}
