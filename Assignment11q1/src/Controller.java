

//importing files here.
import java.util.Scanner;


/**
 * This program play's the connect four field game between two human players or
 * between a human player and the CPU.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 */
public class Controller {

	public Connect4Field aConnect4Field = new Connect4Field();
	public View aView = new View();	
	private PlayerInterface[] thePlayers = new PlayerInterface[2];

	/**
	 * This method facilitates the entire game , and checks is any player won
	 * the game, or is it a draw match. This calls various methods to perform
	 * the game, it enables players to play one by one, and checks the state of
	 * the game after every turn.
	 */

	/**
	 * 
	 * @return returns the last move of the player
	 */
	public void playTheGame() {
		int column;
		aView.showOutput(aConnect4Field);
		boolean gameIsOver = false;
		do {
			for (int index = 0; index < 2; index++) {
				System.out.println(aConnect4Field);
				if (aConnect4Field.isItaDraw()) {
					aView.showOutput("Draw");
					gameIsOver = true;
				} else {
					aView.showOutput(thePlayers[index].getName() + " it's Your Turn! play your move");
					column = thePlayers[index].nextMove();
					while (!aConnect4Field.checkIfPiecedCanBeDroppedIn(column)) {
						aView.showOutput("Invalid Turn Try again");
						column = thePlayers[index].nextMove();

					}

					aConnect4Field.dropPieces(column, thePlayers[index].getGamePiece());
					if (aConnect4Field.error != "") {
						aView.showOutput(aConnect4Field.error);
					} else {
						if (aConnect4Field.didLastMoveWin()) {
							gameIsOver = true;
							aView.showOutput("The winner is: " + thePlayers[index].getName());
							
						}
					}
				}
			}

		} while (!gameIsOver);
	}

	/**
	 * creating the players to play the connect 4 field game and initiate the
	 * game by calling the play the game method.
	 * 
	 */
	public void gameRunner() {
		System.out.println("Press 1 for playing with CPU or 2 for a two player game");
		int userInput = aView.userInput();
		if (userInput == 2) {
			for (int index = 0; index < 2; index++) {
				this.thePlayers[index] = new Player(aConnect4Field, aConnect4Field.getPlayerDetails()[index],
						aConnect4Field.getPlayerSymbol()[index]);
			}
		} else {
			this.thePlayers[0] = new Player(aConnect4Field, aConnect4Field.getPlayerDetails()[0],
					aConnect4Field.getPlayerSymbol()[0]);
			this.thePlayers[1] = new CPUplayer(aConnect4Field, "CPU", aConnect4Field.getPlayerSymbol()[1]);
		}

		playTheGame();

	}

	/**
	 * Initiating new game
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		Controller c = new Controller();
		c.gameRunner();

	}
}
