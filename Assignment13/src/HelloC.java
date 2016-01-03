import java.net.InetAddress;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * this class is the rmi client.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 * 
 */
public class HelloC {
	/**
	 * this method tests the local remote objects.
	 * 
	 * @param obj
	 */
	public static void localRemoteTest(HelloInterface obj) {
		try {
			if(obj.test(InetAddress.getLocalHost().getHostName().toString())) 
				System.out.println("Local Object");
			else
				System.out.println("Remote Object");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		int portNumber = 1099;
		String sPortNumber = "1099";
		try {
			System.out.println("Provide Host name use nslook up on server");
			Scanner sc = new Scanner(System.in);
			String hostName=sc.nextLine();
			HelloInterface object = (HelloInterface) Naming.lookup("rmi://"+hostName + ":"+sPortNumber + "/hello");
			System.out.println(object.toString());
			localRemoteTest(object);
			localRemoteTest(new HelloImplementation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
}
