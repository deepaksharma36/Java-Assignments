//import files are here.

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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
		
		Registry aRegistry;
		
		try {
			int RMIPortNum=Integer.parseInt(args[0]);
			Server exportedServer = new Server(RMIPortNum);
			aRegistry = LocateRegistry.createRegistry(RMIPortNum);
				aRegistry.rebind("server", exportedServer);
			System.out.println("Server is Up and Running...");
		} catch (Exception e) {
			e.printStackTrace();
			
			}
			
		}
	}


