//import files are placed here.
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * this class implements hello remote interface.
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 *
 */
public class HelloImplementation extends UnicastRemoteObject  implements HelloInterface  {

	protected HelloImplementation() throws RemoteException {
		super();
			}

	@Override
	public boolean test(String callerIp) throws RemoteException {
		
		try {
			return callerIp.equals(InetAddress.getLocalHost().getHostName().toString());
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		}
		}

}
