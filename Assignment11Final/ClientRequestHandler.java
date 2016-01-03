import java.net.*;
import java.io.*;
import java.net.Socket;


/**
 * This class create player when it receive a new new request for playing. this
 * will interact the details with the player and also creates the object of
 * player n stores it in the data structure array
 * 
 * @author Deepak Sharma ds5930
 * @author Sree Lakshmi Kurra sk9040
 *
 */
public class ClientRequestHandler extends Thread {

	Socket aSocket;
	GameServer aGameServer;
	char[] GamePiece = { '*', '^', '#', '$' };

	public ClientRequestHandler(Socket aClient, GameServer aGameServer) {
		this.aSocket = aClient;
		this.aGameServer = aGameServer;

	}

	/**
	 * in run method, it interacts with the player and creates the player object
	 * and stores the information in appropriate arrays.
	 *
	 */
	public void run() {
		try {
			PrintWriter out = new PrintWriter(aSocket.getOutputStream(), true);
			BufferedReader in;
			in = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			out.println("With how many players you wants to play: 4 or 2 ");
			out.println(
					"Server has " + aGameServer.getFourPlayerGameCount() + "players who wants to play 4 player game ");
			out.println(
					"Server has " + aGameServer.getTwoPlayerGameCount() + "players who wants to play 2 players game ");

			String choice = in.readLine();
			// out.println(choice);
			while (!choice.equals("2") && !choice.equals("4")) {
				choice = in.readLine();
				// out.println(choice);
			}
			System.out.println(choice);
			out.println("Please Provide Your Name");
			String name;
			while ((name = in.readLine()) == null) {
				// out.println(name);
			}
			System.out.println("Player Name is" + name);
			if (choice.equals("4") && aGameServer.getFourPlayerGameCount() < 4) {
				aGameServer.getFourPlayerGame()[aGameServer.getFourPlayerGameCount()] = new Player(
						aGameServer.getaConnect4FieldFourPlayer(), name,
						GamePiece[aGameServer.getFourPlayerGameCount()], aSocket);
				aGameServer.setFourPlayerGameCount(aGameServer.getFourPlayerGameCount() + 1);
			}
			if (choice.equals("2")) {
				System.out.println("Creating New Player");
				aGameServer.getTwoPlayerGame()[aGameServer.getTwoPlayerGameCount()] = new Player(
						aGameServer.getaConnect4FieldTwoPlayer(), name, GamePiece[aGameServer.getTwoPlayerGameCount()],
						aSocket);
				aGameServer.setTwoPlayerGameCount(aGameServer.getTwoPlayerGameCount() + 1);
			}
		} catch (Exception e) {
			System.out.println("Not able to Create Player");
		}
	}

}
