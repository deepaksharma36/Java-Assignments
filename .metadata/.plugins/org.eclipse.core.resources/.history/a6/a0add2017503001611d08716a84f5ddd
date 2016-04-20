//import files are here.

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
		
		Registry aRegistry;
		
		try {
			String RMIPortNum=args[0];
			int processID = Integer.parseInt(args[1]);
			int processCount = Integer.parseInt(args[2]);
			Process exportedProcess = new Process(processCount,processID,RMIPortNum);
			aRegistry = LocateRegistry.createRegistry(Integer.parseInt(RMIPortNum));
				aRegistry.rebind("server", exportedProcess);
			System.out.println("Process is Up and Running...");
			exportedProcess.requestResolver();
			System.out.println("Press Enter when all Process are running");
			Scanner sc = new Scanner(System.in);
			sc.nextLine();
			
			
			exportedProcess.criticalSectionSeeker();
			exportedProcess.criticalSectionExecutor();
			
			exportedProcess.run();
			

		} catch (Exception e) {
			e.printStackTrace();
			
			}
			
		}
	}


