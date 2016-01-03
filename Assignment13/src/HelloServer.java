import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
public class HelloServer {
	public static void main(String args[]) {
		String registryURL;
		try {
			//startRegistry(1099);
			System.out.println("Provide Host name use nslook up on server ds5930.student.rit.edu");
			Scanner sc = new Scanner(System.in);
			String hostName=sc.nextLine();
			registryURL = "rmi://"+hostName+":" + 1099 + "/hello";
			System.out.println("start registry now at 1099 manually on server");
			HelloInterface obj = new HelloImplementation();
			Naming.rebind(registryURL, obj);
		}
		catch (Exception re) {
			re.printStackTrace();
		} 
	}

	 
} 
