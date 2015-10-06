

/**
 * This program play's the connect four field game between two human players or
 * between a human player and the CPU.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 */
public class CPUplayer implements PlayerInterface {

	private String name;
	private char GamePiece;
	private Connect4Field aConnect4Field;

	public CPUplayer(Connect4Field aConnect4Field, String name, char GamePiece) {
		this.aConnect4Field = aConnect4Field;
		this.GamePiece = GamePiece;
		this.name = name;

	}

	public char getGamePiece() {

		return GamePiece;
	}

	public String getName() {

		return name;
	}

	/**
	 * Through this method, after executing the CPU strategies, CPU provides
	 * input for the game through the move method.
	 * 
	 */
	public int nextMove() {

		int playersLastMove = aConnect4Field.getLastMove()[1];
		int hereIamWining = whereWining(aConnect4Field.getPlayerSymbol()[1]);
		// Checks if the CPU is winning the game, if true then game if over.
		if (hereIamWining > -1)
			return hereIamWining + 1;
		// Checks if human might win the game in the next move, then CPU blocks
		// the pattern
		int hereHumanWining = whereWining(aConnect4Field.getPlayerSymbol()[0]);
		if (hereHumanWining > -1)
			return hereHumanWining + 1;
		if (aConnect4Field.checkIfPiecedCanBeDroppedIn(playersLastMove + 2))
			return playersLastMove + 2;
		if (aConnect4Field.checkIfPiecedCanBeDroppedIn(playersLastMove))
			return aConnect4Field.getLastMove()[1];

		else
			return aConnect4Field.getBoardSize()[1] / 2;
	}

	/**
	 * By placing piece at various location on board, CPU player finds wining
	 * location for itself and its opponent.
	 * 
	 * @param Symbol
	 *            symbol of the player
	 * @return winning location.
	 */
	public int whereWining(char Symbol) {
		for (int index = 1; index <= aConnect4Field.getBoardSize()[1]; index++) {
			if (aConnect4Field.checkIfPiecedCanBeDroppedIn(index)) {
				aConnect4Field.dropPieces(index, Symbol);
				if (aConnect4Field.didLastMoveWin()) {
					aConnect4Field.undoDropPiece();
					return aConnect4Field.getLastMove()[1];

				} else {
					aConnect4Field.undoDropPiece();
				}
			}
		}
		return -1;

	}

}
