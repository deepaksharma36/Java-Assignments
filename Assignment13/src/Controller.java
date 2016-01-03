
/**
 * This program play's the connect four field game between two/four players.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 */
public class Controller extends Thread {
	public Connect4Field aConnect4Field; // = new Connect4Field();
	private PlayerInterface[] thePlayers; // = new PlayerInterface[2];

	Controller(Connect4Field aConnect4Field, PlayerInterface[] thePlayers) {
		this.aConnect4Field = aConnect4Field;
		this.thePlayers = thePlayers;
	}

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
		try {
			boolean gameIsOver = false;
			for (int count = 0; count < thePlayers.length; count++)
				thePlayers[count].getView().showOutput(
						aConnect4Field.toString());

			do {
				for (int index = 0; index < thePlayers.length; index++) {
					// thePlayers[index].getView().showOutput(aConnect4Field.toString());
					if (aConnect4Field.isItaDraw()) {
						for (int count = 0; count < thePlayers.length; count++)
							thePlayers[count].getView().showOutput("Draw");
						gameIsOver = true;
					} else {
						thePlayers[index].getView().showOutput(
								thePlayers[index].getName()
										+ " it's Your Turn! play your move");
						column = thePlayers[index].nextMove();
						while (!aConnect4Field
								.checkIfPiecedCanBeDroppedIn(column)) {
							thePlayers[index].getView().showOutput(
									"Invalid Turn Try again");
							column = thePlayers[index].nextMove();
						}

						aConnect4Field.dropPieces(column,
								thePlayers[index].getGamePiece());
						for (int count = 0; count < thePlayers.length; count++)
							thePlayers[count].getView().showOutput(
									aConnect4Field.toString());
						if (aConnect4Field.error != "") {

							thePlayers[index].getView().showOutput(
									aConnect4Field.error);
						} else {
							if (aConnect4Field.didLastMoveWin()) {
								gameIsOver = true;
								// all player get to know
								for (int count = 0; count < thePlayers.length; count++)
									thePlayers[count].getView().showOutput(
											"The winner is: "
													+ thePlayers[index]
															.getName());

							}
						}

					}

				}

			} while (!gameIsOver);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * creating the players to play the connect 4 field game and initiate the
	 * game by calling the play the game method.
	 * 
	 */

	/**
	 * Initiating new game
	 * 
	 * @param args
	 */

	public void run() {
		try {
			playTheGame();
		} catch (Exception e) {
			System.out.println("Exiting the game Due to Error" + e);
			e.printStackTrace();
		}
	}
}
