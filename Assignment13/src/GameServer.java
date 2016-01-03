//import files are places here.
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 * this gameServer class interacts with various client players who request for
 * game.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 * 
 */
public class GameServer extends UnicastRemoteObject implements
		GameServerInterface {

	Scanner sc = new Scanner(System.in);
	
	volatile int twoPlayerGameCount = 0;
	volatile int fourPlayerGameCount = 0;
	Object playerLock2;
	Object playerLock4;
	Connect4Field aConnect4FieldFourPlayer = new Connect4Field();
	Connect4Field aConnect4FieldTwoPlayer = new Connect4Field();
	PlayerInterface[] twoPlayerGame = new PlayerInterface[2];
	PlayerInterface[] fourPlayerGame = new PlayerInterface[4];
	char[] GamePiece = { '*', '^', '#', '$' };

	public GameServer() throws RemoteException {

		super();
		this.playerLock2 = new Object();
		this.playerLock4 = new Object();

	}

	/**
	 * this method implements game server interface's registration method. this
	 * creates players according to the request requested by the client and
	 * places the player in respective arrays of two/four player.
	 */
	public synchronized boolean registration(String name, String choice,
	
			ViewInterface aView) throws RemoteException {
	

		if (choice.equals("4") && getFourPlayerGameCount() < 4) {
			getFourPlayerGame()[getFourPlayerGameCount()] = new Player(
					getaConnect4FieldFourPlayer(), name,
					GamePiece[getFourPlayerGameCount()], aView);
			setFourPlayerGameCount(getFourPlayerGameCount() + 1);
			return true;
		}
		if (choice.equals("2")) {
			System.out.println("Creating New Player");
			getTwoPlayerGame()[getTwoPlayerGameCount()] = new Player(
					getaConnect4FieldTwoPlayer(), name,
					GamePiece[getTwoPlayerGameCount()], aView);
			setTwoPlayerGameCount(getTwoPlayerGameCount() + 1);
			return true;
		}
		return false;

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

	public void setaConnect4FieldFourPlayer(
			Connect4Field aConnect4FieldFourPlayer) {
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