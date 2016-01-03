import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

//importing files here.
/**
 * This program play's the connect four field game between two human players or
 * between a human player and the CPU.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 */
public class Controller extends Thread {

	public Connect4Field aConnect4Field; // = new Connect4Field();
	public View aView = new View();
	PrintWriter out[];
	BufferedReader in[];
	private PlayerInterface[] thePlayers; // = new PlayerInterface[2];

	Controller(Connect4Field aConnect4Field, PlayerInterface[] thePlayers) {
		this.aConnect4Field = aConnect4Field;
		this.thePlayers = thePlayers;
		out = new PrintWriter[thePlayers.length];
		for (int count = 0; count < thePlayers.length; count++) {
			try {
				out[count] = new PrintWriter(thePlayers[count].getaSocket().getOutputStream(), true);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method facilitates the entire game , and checks if any player won
	 * the game, or is it a draw match. This calls various methods to perform
	 * the game, it enables players to play one by one, and checks the state of
	 * the game after every turn.
	 */

	public void playTheGame() {
		int column;

		// aView.showOutput(aConnect4Field);
		boolean gameIsOver = false;
		do {
			for (int index = 0; index < thePlayers.length; index++) {
				// for(int count=0;count<thePlayers.length;count++)
				out[index].println((aConnect4Field));
				if (aConnect4Field.isItaDraw()) {
					for (int count = 0; count < thePlayers.length; count++)
						out[count].println("Draw");
					gameIsOver = true;
				} else {
					out[index].println(thePlayers[index].getName() + " it's Your Turn! play your move");
					column = thePlayers[index].nextMove();
					while (!aConnect4Field.checkIfPiecedCanBeDroppedIn(column)) {
						out[index].println("Invalid Turn Try again");
						column = thePlayers[index].nextMove();
					}

					aConnect4Field.dropPieces(column, thePlayers[index].getGamePiece());
					if (aConnect4Field.error != "") {
						for (int count = 0; count < thePlayers.length; count++)
							out[count].println(aConnect4Field.error);
					} else {
						if (aConnect4Field.didLastMoveWin()) {
							gameIsOver = true;
							// all player get to know
							for (int count = 0; count < thePlayers.length; count++)
								out[count].println("The winner is: " + thePlayers[index].getName());

						}
					}

				}

			}

		} while (!gameIsOver);
	}

	/**
	 * Initiating new game.
	 * 
	 * @param args
	 */

	public void run() {
		try {
			playTheGame();
		} catch (Exception e) {
			System.out.println("Exiting the game Due to Error" + e);
		}
	}
}
