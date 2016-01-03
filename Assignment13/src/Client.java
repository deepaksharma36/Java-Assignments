//import files are placed here
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * this client class is rmi client which access remote method of the server.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 * 
 */
public class Client {
	String choice;
	String name;
	ViewInterface clientView;

	/**
	 * this method gets the details of the player.
	 * 
	 * @throws RemoteException
	 *             throws the remote exception
	 */
	public void getPlayerDetails() throws RemoteException {
		Scanner sc = new Scanner(System.in);
		System.out
				.println("With how many players you wants to play!! : 4 or 2 ");
		choice = sc.nextLine();
		while (!choice.equals("2") && !choice.equals("4")) {
			choice = sc.nextLine();
		}
		System.out.println("Your choice is: " + choice);
		System.out.println("Please Provide Your Name");
		while ((name = sc.nextLine()) == null) {
		}
		System.out.println("Your Name is" + name);
		clientView = new View();

	}

	/**
	 * this method starts the game and envokes the remote method registration of
	 * the game server.
	 * 
	 * @param aGameServer
	 *            this is the remote object of the game server at the client
	 *            side.
	 */
	public void startGame(GameServerInterface aGameServer) {
		try {
			getPlayerDetails();
			aGameServer.registration(name, choice, clientView);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * this main method is executed at the client side and gets the host name
	 * and port number from the user and gets the stub of the server.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {

			String hostName;
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the RMIRegistry host namer:");
			hostName = sc.nextLine();
			System.out.println("Enter the RMIregistry port number:");
			String portNum = sc.nextLine();
			String registryURL = "rmi://" + hostName + ":" + portNum + "/game";
			GameServerInterface aGameServerInterface = (GameServerInterface) Naming
					.lookup(registryURL);
			Client aClient = new Client();
			aClient.startGame(aGameServerInterface);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
