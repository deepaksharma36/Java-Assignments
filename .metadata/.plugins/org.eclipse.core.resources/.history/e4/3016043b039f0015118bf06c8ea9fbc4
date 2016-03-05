
//import files are placed here
import java.net.*;
import java.util.HashMap;
import java.io.*;

/**
 * this class gameServer is the server which can initiate multiple games at the
 * same time and multiple players can request the server for playing the same or
 * different game.
 * 
 * @author Deepak Sharma ds5930
 * @author Sree Lakshmi Kurra sk9040
 *
 */
public class GameServer extends Thread {
	ServerSocket aServerSocket;
	final int port = 2200;
	volatile int twoPlayerGameCount = 0;
	volatile int fourPlayerGameCount = 0;
	Object playerLock2;
	Object playerLock4;
	
	Connect4Field aConnect4FieldFourPlayer = new Connect4Field();
	Connect4Field aConnect4FieldTwoPlayer = new Connect4Field();
	PlayerInterface[] twoPlayerGame = new PlayerInterface[2];
	PlayerInterface[] fourPlayerGame = new PlayerInterface[4];
	HashMap<String, ClientRequestHandler> aHashMap = new HashMap <String, ClientRequestHandler>();
	// For default port no 4242
	/**
	 * this constructor creates a new socket here.
	 */
	public GameServer() {
		try {
			aServerSocket = new ServerSocket(port);
			this.playerLock2 = new Object();
			this.playerLock4 = new Object();
		} catch (IOException io) {

		}
	}

	/**
	 * main method will run the thread of the game server.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {

		new GameServer().start();


	}

	// to get the port number
	public int getPort() {
		return port;
	}

	/**
	 * in this method when a player requests the server to play the game,then
	 * server will pass the request to client request handler thread ad it also
	 * starts the game runner thread which explores the opportunity to start a
	 * game when the number of players reach to the appropriate size.
	 */
	public void run() {
		GameRunner aGameRunner = new GameRunner(this);
		aGameRunner.start();
		try {
			DatagramSocket datagramSocket;
			datagramSocket  = new DatagramSocket(2200);
			
			
			
			for (;;) {
				byte[] buffer = new byte[1024];
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);	
				System.out.println("looking for");
					datagramSocket.receive(packet);
					System.out.println(packet.getAddress().toString()+"has requested");
					if(!aHashMap.containsKey(packet.getAddress().toString()))
					{  
						System.out.println("Creating New Client Request Handler");
						ClientRequestHandler aClientRequestHandler = new ClientRequestHandler(packet, this);
						aHashMap.put(packet.getAddress().toString(),aClientRequestHandler);
						aClientRequestHandler.start();
					}
					else
					{
						ClientRequestHandler aClientRequestHandler=aHashMap.get(packet.getAddress().toString());
						if(aClientRequestHandler.isStatus())
						{
							System.out.println("Setting new Paket");
							aClientRequestHandler.setPacket(packet);
							
							aClientRequestHandler.setNewPacket(true);
						}
					}
					

				}

				
			}
		catch (Exception e) {

			e.printStackTrace();
			
		}
		
	}
	
	public int getTwoPlayerGameCount() {
		synchronized (playerLock2) {
			return twoPlayerGameCount;
		}
	}

	public void setTwoPlayerGameCount(int twoPlayerGameCount) {
		synchronized (playerLock2) {
			this.twoPlayerGameCount = twoPlayerGameCount;
		}

	}

	public int getFourPlayerGameCount() {
		synchronized (playerLock4) {
			return fourPlayerGameCount;
		}
	}

	public void setFourPlayerGameCount(int fourPlayerGameCount) {
		synchronized (playerLock4) {
			this.fourPlayerGameCount = fourPlayerGameCount;
		}
	}

	public Connect4Field getaConnect4FieldFourPlayer() {
		synchronized (playerLock4) {

			return aConnect4FieldFourPlayer;
		}
	}

	public void setaConnect4FieldFourPlayer(Connect4Field aConnect4FieldFourPlayer) {
		synchronized (playerLock4) {
			this.aConnect4FieldFourPlayer = aConnect4FieldFourPlayer;
		}
	}

	public Connect4Field getaConnect4FieldTwoPlayer() {
		synchronized (playerLock2) {
			return aConnect4FieldTwoPlayer;
		}
	}

	public void setaConnect4FieldTwoPlayer(Connect4Field aConnect4FieldTwoPlayer) {
		synchronized (playerLock2) {
			this.aConnect4FieldTwoPlayer = aConnect4FieldTwoPlayer;
		}
	}

	public PlayerInterface[] getTwoPlayerGame() {
		synchronized (playerLock2) {
			return twoPlayerGame;
		}
	}

	public void setTwoPlayerGame(PlayerInterface[] twoPlayerGame) {
		synchronized (playerLock2) {
			this.twoPlayerGame = twoPlayerGame;
		}
	}

	public PlayerInterface[] getFourPlayerGame() {
		synchronized (playerLock4) {
			return fourPlayerGame;
		}
	}

	public void setFourPlayerGame(PlayerInterface[] fourPlayerGame) {
		synchronized (playerLock4) {
			this.fourPlayerGame = fourPlayerGame;
		}
	}

}