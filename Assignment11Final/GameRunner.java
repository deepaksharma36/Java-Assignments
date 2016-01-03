
/**
 * this class explore the opportunities for starting a new game this thread
 * independently keep a watch on number of players in the array for two and four
 * players.And when it reaches the appropriate count, it will create a new
 * object of the controller class and pass the players.
 * 
 * @author Deepak Sharma ds5930
 * @author Sree Lakshmi Kurra sk9040
 *
 */
public class GameRunner extends Thread {
	GameServer aGameServer;

	public GameRunner(GameServer aGameServer) {
		this.aGameServer = aGameServer;

	}

	/**
	 * in this run method,it explores the opportunities for starting a new game
	 * this thread independently keep a watch on number of players in the array
	 * for two and four players.And when it reaches the appropriate count, it
	 * will create a new object of the controller class and pass the players.
	 */

	public void run() {
		while (true) {
			if (aGameServer.getFourPlayerGameCount() == 4) {
				// aGameServer.getaConnect4FieldFourPlayer().setSizeBoard(11,25);
				new Controller(aGameServer.getaConnect4FieldFourPlayer(), aGameServer.getFourPlayerGame()).start();
				aGameServer.setaConnect4FieldFourPlayer(new Connect4Field());
				aGameServer.setFourPlayerGame(new PlayerInterface[4]);
				aGameServer.setFourPlayerGameCount(0);
			}

			if (aGameServer.getTwoPlayerGameCount() == 2) {
				System.out.println("Intiating A new Two Player Game");
				System.out.println(aGameServer.getTwoPlayerGame()[0]);
				System.out.println(aGameServer.getTwoPlayerGame()[1]);
				new Controller(aGameServer.getaConnect4FieldTwoPlayer(), aGameServer.getTwoPlayerGame()).start();
				aGameServer.setaConnect4FieldTwoPlayer(new Connect4Field());
				aGameServer.setTwoPlayerGame(new PlayerInterface[2]);
				aGameServer.setTwoPlayerGameCount(0);
			}
		}

	}

}
