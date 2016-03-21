import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is for testing the core functionalities of chord 
 * @author deepak Sharma ds5930
 *
 */
public class Testing {

	public  final String[] SERVERS = {  "kansas.cs.rit.edu",  "gorgon.cs.rit.edu",
		"newyork.cs.rit.edu"};//, "yes.cs.rit.edu", "kinks.cs.rit.edu"};//, "medusa.cs.rit.edu", "joplin.cs.rit.edu",
		//"delaware.cs.rit.edu", "buddy.cs.rit.edu", "arizona.cs.rit.edu" };
	public final String[] SERVERSALL={ "glados.cs.rit.edu", "kansas.cs.rit.edu",  "gorgon.cs.rit.edu",
	"newyork.cs.rit.edu"};
	public final String rmiPort;
	
	Testing(String rmiPort)
	{
		this.rmiPort=rmiPort;
	}
private ServerInterface ServerContact(String hostName) throws RemoteException{
		
		String registryURL = "rmi://" + hostName + ":" + rmiPort + "/server";
		try {
			ServerInterface aServer = (ServerInterface) Naming
					.lookup(registryURL);
			return aServer;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

public void testFileAdd() throws RemoteException{

	Scanner sc = new Scanner(System.in);
	String fileName;
	String keyword;
	for (String server: SERVERSALL){
		System.out.println("sending file from "+server+" in chord system ");
		ServerInterface testingServer = ServerContact(server);
		System.out.println("Provide keyword for the file");
		keyword=sc.nextLine();
		System.out.println("Provide FileName for the file");
		fileName=sc.nextLine();
		System.out.println(testingServer.testInsert(keyword,fileName));
			
		}

}

public void viewTest() throws RemoteException{
	for (String server: SERVERSALL){
		ServerInterface testingServer = ServerContact(server);
		
			System.out.println("viewing "+server);
			System.out.println(testingServer.testView());
		}
	
}

public void testAdd() throws RemoteException
{
	for (String server: SERVERS){
		ServerInterface testingServer = ServerContact(server);
		System.out.println("adding "+server);
			System.out.println(testingServer.testAdd());

		}
	/*
	for (String server: SERVERS){
		ServerInterface testingServer = ServerContact(server);
		
			System.out.println("viewing "+server);
			System.out.println(testingServer.testView());
		}
	*/
	
}

public void testleave() throws RemoteException
{   ArrayList<String> removed = new ArrayList<String>();
	for (String server: SERVERS){
		ServerInterface testingServer = ServerContact(server);
		System.out.println("removing "+server);
			testingServer.testLeave();
			viewTest();
			removed.add(server);
	/*		for (String Viewserver: SERVERS){
				if (!removed.contains(Viewserver)){
				ServerInterface testingView = ServerContact(server);
					testingServer.testView();}
				}*/
			
		}
	
	
	
}

public void testFileSearch() throws RemoteException{
	
	Scanner sc = new Scanner(System.in);
	String keyword;
	
	for (String server: SERVERSALL){
		ServerInterface testingServer = ServerContact(server);
		System.out.println("Finding file from "+server+" in chord system ");
		System.out.println("Provide keyword for the file");
		keyword=sc.nextLine();
			System.out.println(testingServer.testSearch(keyword));}
}

public void simpleTest() throws RemoteException{
	ServerInterface testingServer = ServerContact(SERVERSALL[0]);
	ServerInterface testingServer2 = ServerContact(SERVERS[2]);
	System.out.println(testingServer.testInsert("keyword","testFile"));
	System.out.println(testingServer2.testSearch("keyword"));
}

public static void main(String[] args) {
		Testing aTesting = new Testing(args[0]);
		try {
			System.out.println("**********************Node Add Test*****************************");
			aTesting.testAdd();
			System.out.println("**********************Node View Test****************************");
			aTesting.viewTest();
			System.out.println("**********************File Add Test*****************************");
			aTesting.testFileAdd();
			System.out.println("**********************File Search Test*****************************");
			aTesting.testFileSearch();

			System.out.println("**********************Node Leave Test***************************");
			aTesting.testleave();
			//aTesting.simpleTest();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}
