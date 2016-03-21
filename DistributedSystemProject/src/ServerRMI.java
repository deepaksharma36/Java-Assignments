//import files are here.
import java.net.InetAddress;
import java.rmi.AccessException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * This Class will Initiate the RMI server on the port no Assign by Network Administrator.
 * @author Sharma, Deepak DS5930
 * 
 */

public class ServerRMI {
	/**
	 * this remote server creates registry at giver Localhost and port number,then
	 * registers serve object on the registry.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		boolean entry=false;
		Registry aRegistry;
		int[] range={0,Integer.parseInt(args[3])-1};
		try {
			int RMIPortNum=Integer.parseInt(args[0]);
			
			Server exportedServer = 
			new Server(RMIPortNum,InetAddress.getByName(args[1]).getHostAddress(),args[2],Integer.parseInt(args[3]));
			if (InetAddress.getLocalHost().getHostAddress().equals(InetAddress.getByName(args[1]).getHostAddress()))
				{System.out.println("Starting entry Server and forming Chord");
				exportedServer.setRange(range);
				exportedServer.setSuccessor(InetAddress.getLocalHost().getHostAddress());
				exportedServer.setPredecessor(InetAddress.getLocalHost().getHostAddress());
				entry=true;}
			
			aRegistry = LocateRegistry.createRegistry(RMIPortNum);
			aRegistry.rebind("server", exportedServer);
			//if(!entry)
			//exportedServer.add();
			//exportedServer.view();
			
			
			System.out.println("Server is Up and Running...");
			
		} catch (Exception e) {
			e.printStackTrace();
			
			}
			
		}
	}


