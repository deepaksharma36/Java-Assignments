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
	
	
	private PlayerInterface[] thePlayers; // = new PlayerInterface[2];

	Controller(Connect4Field aConnect4Field, PlayerInterface[] thePlayers) {
		this.aConnect4Field = aConnect4Field;
		this.thePlayers = thePlayers;
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
				thePlayers[index].playerView(aConnect4Field.toString());
				if (aConnect4Field.isItaDraw()) {
					for (int count = 0; count < thePlayers.length; count++)
						thePlayers[count].playerView("Draw");
					gameIsOver = true;
				} else {
					thePlayers[index].playerView(thePlayers[index].getName() + " Your Turn");
					column = thePlayers[index].nextMove();
					while (!aConnect4Field.checkIfPiecedCanBeDroppedIn(column)) {
						thePlayers[index].playerView("Invalid Turn Try again");
						column = thePlayers[index].nextMove();
					}

					aConnect4Field.dropPieces(column, thePlayers[index].getGamePiece());
					if (aConnect4Field.error != "") {
						
							thePlayers[index].playerView(aConnect4Field.error);
					} else {
						if (aConnect4Field.didLastMoveWin()) {
							gameIsOver = true;
							// all player get to know
							for (int count = 0; count < thePlayers.length; count++)
								thePlayers[count].playerView("The winner is: " + thePlayers[index].getName());

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
			
			
			System.out.println("Exiting the game Due to Error" );
			e.printStackTrace();
		}
	}
}
