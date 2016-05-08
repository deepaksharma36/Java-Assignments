

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TrackerServer
{
	public static void main(String args[])
	{
		try{
			
			
			
			
			
			
			
			
			
		Tracker remote = new Tracker(args[0]);
		Registry registry = LocateRegistry.createRegistry(Integer.parseInt(args[0]));
		registry.rebind("FileServer", remote);
		}catch(RemoteException ex){
			ex.printStackTrace();
		}
	}
}
