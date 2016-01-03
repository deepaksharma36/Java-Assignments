

//import files are placed here.
import java.util.Scanner;

/**
 * This class implements the connect four game board. In this game two players
 * either two human players, or a human player and CPU play the game. The aim is
 * to create a series of 4 pattern, with the same symbol.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 */
public class Connect4Field implements Connect4FieldInterface {
	public String error = "";
	private char[] playerSymbol = { '*', '+' };

	public void setPlayerSymbol(char[] playerSymbol) {
		this.playerSymbol = playerSymbol;
	}

	public void setPlayerDetails(String[] playerDetails) {
		this.playerDetails = playerDetails;
	}

	public void setSizeBoard(int[] sizeBoard) {
		this.sizeBoard = sizeBoard;
	}

	private String[] playerDetails = { "A", "B" };

	public String[] getPlayerDetails() {
		return playerDetails;
	}

	private int[] sizeBoard = { 8, 20 };
	private char[][] Connect4FieldBoard = new char[sizeBoard[0]][sizeBoard[1]];
	private int[] lastMove = new int[2];
	private PlayerInterface playerEA = null, playerEB = null;

	// default constructor for testing.
	public Connect4Field() {

		initConnect4FieldBoard();
		initConnect4FieldBoard();
	}

	// constructor to initiate a new game
	public Connect4Field(String game) {
		init(playerEA, playerEB);
		initConnect4FieldBoard();

	}

	/**
	 * Before dropping the piece into the field, boolean checks if the piece can
	 * be dropped or not. This is done by checking the entire column in the game
	 * board, it checks for a free space to place the piece.
	 */
	public boolean checkIfPiecedCanBeDroppedIn(int column) {

		column = column - 1;
		int row = 0;
		if (column >= 0 && column < getBoardSize()[1]) {

			while (row < sizeBoard[0]) {
				if (Connect4FieldBoard[row][column] == '.') {
					return true;
				}
				row++;
			}
		}

		return false;
	}

	/**
	 * Drops the piece in any given column at appropriate place as per the game
	 * requirement given by the player or the computer.
	 */
	public void dropPieces(int column, char gamePiece) {
		int row = 0;
		column = column - 1;
		if (column >= 0 && column < sizeBoard[1]) {
			// this explores from the bottom to up and finds first empty place
			// to fill the space with the piece.
			while (row < sizeBoard[0]) {
				if (Connect4FieldBoard[row][column] == '.') {
					Connect4FieldBoard[row][column] = gamePiece;
					lastMove[0] = row;
					lastMove[1] = column;
					break;
				}
				row++;

			}
			if (row == sizeBoard[0]) {
				error = "Invalid input column has no space ";
			}
		} else {
			error = "Invalid column";

		}

	}

	/**
	 * Checks the board after placing the last move, does he create any
	 * combination on the board to win.
	 */
	public boolean didLastMoveWin() {
		int row = lastMove[0];
		int column = lastMove[1];
		if (row - 3 >= 0 && Connect4FieldBoard[row - 3][column] != 'x') {
			if (doesItConnect(Connect4FieldBoard[row][column], row - 3, row, column, column))
				return true;
		}
		// [-xxx] checking this combination
		if (column + 3 < sizeBoard[1] && Connect4FieldBoard[row][column + 3] != 'x') {
			if (doesItConnect(Connect4FieldBoard[row][column], row, row, column, column + 3))
				return true;
		}
		// [xxx-] checking this combination
		if (column - 3 >= 0) {
			if (doesItConnect(Connect4FieldBoard[row][column], row, row, column - 3, column))
				return true;
		}
		// [xx-x] checking this combination
		if ((column - 2 >= 0 && column + 1 < sizeBoard[1]) && Connect4FieldBoard[row][column - 2] != 'x'
				&& Connect4FieldBoard[row][column + 1] != 'x') {
			if (doesItConnect(Connect4FieldBoard[row][column], row, row, column - 2, column + 1))
				return true;
		}
		// [x-xx] checking this combination
		if ((column - 1 >= 0 && column + 2 < sizeBoard[1]) && Connect4FieldBoard[row][column - 1] != 'x'
				&& Connect4FieldBoard[row][column + 2] != 'x') {
			if (doesItConnect(Connect4FieldBoard[row][column], row, row, column - 1, column + 2))
				return true;
		}
		/**
		 * x
		 *  -
		 *   x
		 *    x checking this combination
		 */

		if ((row - 3 >= 0 && column + 3 < sizeBoard[1]) && Connect4FieldBoard[row - 3][column + 3] != 'x'
				&& Connect4FieldBoard[row][column] != 'x') {
			if (doesItConnect(Connect4FieldBoard[row][column], row, row, column, column)
					&& doesItConnect(Connect4FieldBoard[row][column], row - 1, row - 1, column + 1, column + 1)
					&& doesItConnect(Connect4FieldBoard[row][column], row - 2, row - 2, column + 2, column + 2)
					&& doesItConnect(Connect4FieldBoard[row][column], row - 3, row - 3, column + 3, column + 3))
				return true;
		}
		/**
		 * - 
		 *  x
		 *    x
		 *      x checking this combination
		 */
		if ((row - 3 >= 0 && column - 3 >= 0) && Connect4FieldBoard[row - 3][column - 3] != 'x') {
			if (doesItConnect(Connect4FieldBoard[row][column], row, row, column, column)
					&& doesItConnect(Connect4FieldBoard[row][column], row - 1, row - 1, column - 1, column - 1)
					&& doesItConnect(Connect4FieldBoard[row][column], row - 2, row - 2, column - 2, column - 2)
					&& doesItConnect(Connect4FieldBoard[row][column], row - 3, row - 3, column - 3, column - 3))
				return true;
		}

		/**
		 * x 
		 *   x
		 *     -
		 *       x checking this combination
		 */

		if ((row + 3 < sizeBoard[0] && column - 3 >= 0)) {
			if (doesItConnect(Connect4FieldBoard[row][column], row, row, column, column)
					&& doesItConnect(Connect4FieldBoard[row][column], row + 1, row + 1, column - 1, column - 1)
					&& doesItConnect(Connect4FieldBoard[row][column], row + 2, row + 2, column - 2, column - 2)
					&& doesItConnect(Connect4FieldBoard[row][column], row + 3, row + 3, column - 3, column - 3))
				return true;
		}

		/**
		 * x 
		 *   x
		 *     x
		 *       - checking this combination
		 */
		if ((row + 3 < sizeBoard[0] && column + 3 < sizeBoard[1])) {
			if (doesItConnect(Connect4FieldBoard[row][column], row, row, column, column)
					&& doesItConnect(Connect4FieldBoard[row][column], row + 1, row + 1, column + 1, column + 1)
					&& doesItConnect(Connect4FieldBoard[row][column], row + 2, row + 2, column + 2, column + 2)
					&& doesItConnect(Connect4FieldBoard[row][column], row + 3, row + 3, column + 3, column + 3))
				return true;
		}

		/**
		 * x 
		 *   -
		 *     x
		 *       x checking this combination
		 */
		if (row - 2 >= 0 && column + 2 < sizeBoard[1] && row + 1 < sizeBoard[0] && column - 1 >= 0) {
			if (doesItConnect(Connect4FieldBoard[row][column], row, row, column, column)
					&& doesItConnect(Connect4FieldBoard[row][column], row + 1, row + 1, column - 1, column - 1)
					&& doesItConnect(Connect4FieldBoard[row][column], row - 1, row - 1, column + 1, column + 1)
					&& doesItConnect(Connect4FieldBoard[row][column], row - 2, row - 2, column + 2, column + 2))
				return true;
		}

		/**
		 * x 
		 *   x 
		 *     -
		 *       x checking this combination
		 */
		if ((row - 1 >= 0 && column + 1 < sizeBoard[1] && row + 2 < sizeBoard[0] && column - 2 >= 0)) {
			if (doesItConnect(Connect4FieldBoard[row][column], row, row, column, column)
					&& doesItConnect(Connect4FieldBoard[row][column], row - 1, row - 1, column + 1, column + 1)
					&& doesItConnect(Connect4FieldBoard[row][column], row + 1, row + 1, column - 1, column - 1)
					&& doesItConnect(Connect4FieldBoard[row][column], row + 2, row + 2, column - 2, column - 2))
				return true;
		}

		/**
		 * x - x x checking this combination
		 */
		if (column + 1 < sizeBoard[1] && row + 1 < sizeBoard[0] && row - 2 >= 0 && column - 2 >= 0) {
			if (doesItConnect(Connect4FieldBoard[row][column], row, row, column, column)
					&& doesItConnect(Connect4FieldBoard[row][column], row + 1, row + 1, column + 1, column + 1)
					&& doesItConnect(Connect4FieldBoard[row][column], row - 1, row - 1, column - 1, column - 1)
					&& doesItConnect(Connect4FieldBoard[row][column], row - 2, row - 2, column - 2, column - 2))
				return true;
		}
		/**
		 * x x - x checking this combination
		 */
		if (row - 1 >= 0 && column - 1 >= 0 && row + 2 < sizeBoard[0] && column + 2 < sizeBoard[1]) {
			if (doesItConnect(Connect4FieldBoard[row][column], row, row, column, column)
					&& doesItConnect(Connect4FieldBoard[row][column], row - 1, row - 1, column - 1, column - 1)
					&& doesItConnect(Connect4FieldBoard[row][column], row + 2, row + 2, column + 2, column + 2)
					&& doesItConnect(Connect4FieldBoard[row][column], row + 1, row + 1, column + 1, column + 1))
				return true;
		}

		return false;
	}

	/**
	 * checks weather last action reached to draw state or not.
	 */
	public boolean isItaDraw() {
		for (int dummyCounterY = 0; dummyCounterY < sizeBoard[0]; dummyCounterY++) {
			for (int dummyCounterX = 0; dummyCounterX < sizeBoard[1]; dummyCounterX++) {
				if (Connect4FieldBoard[dummyCounterY][dummyCounterX] == '.')
					return false;
			}
		}

		return true;
	}

	/**
	 * this method overwrites the object class method, we use this method to
	 * show the playing board to players.
	 */
	public String toString() {
		return printConnect4FieldBoard();

	}

	/**
	 * This method creates two human players or a human player and a CPU player,
	 * and stores these players in the array of player interface type, then
	 * enables them to play the game one by one.
	 */
	public void init(PlayerInterface playerEA, PlayerInterface playerEB) {

	}

	public int[] getLastMove() {
		return lastMove;
	}

	/**
	 * checks the rows or columns according to the input weather the wining
	 * pattern exists
	 * 
	 * @param value
	 *            check if this is present on all the tuples in the range of r1,
	 *            r2 and c1, c2
	 * @param r1
	 *            row 1
	 * @param r2
	 *            row 2
	 * @param c1
	 *            column 1
	 * @param c2
	 *            column 2
	 * @return if the pattern exists,then returns true else returns false.
	 */
	public boolean doesItConnect(char value, int r1, int r2, int c1, int c2) {
		for (int Y = r1; Y <= r2; Y++) {
			for (int X = c1; X <= c2; X++) {
				if (value != Connect4FieldBoard[Y][X]) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This function initiates the playing board of the game. Dots(.) is where
	 * the player can make moves, and at cross(X) player can't make moves.
	 */
	public void initConnect4FieldBoard() {
		int N = sizeBoard[0] - 1, M = sizeBoard[1] - 2 * (sizeBoard[0] - 1);
		for (int dummyCounterY = 0; dummyCounterY < sizeBoard[0]; dummyCounterY++) {
			for (int dummyCounterX = 0; dummyCounterX < N; dummyCounterX++) {
				Connect4FieldBoard[dummyCounterY][dummyCounterX] = 'x';

			}
			for (int dummyCounterX = N; dummyCounterX < N + M; dummyCounterX++) {
				Connect4FieldBoard[dummyCounterY][dummyCounterX] = '.';

			}
			for (int dummyCounterX = M + N; dummyCounterX < M + 2 * N; dummyCounterX++) {
				Connect4FieldBoard[dummyCounterY][dummyCounterX] = 'x';

			}

			N = N - 1;
			M = M + 2;

		}

	}

	/**
	 * this method returns the connect 4 field board in a string format.
	 * 
	 * @return connect 4 field board in string format.
	 */
	public String printConnect4FieldBoard() {
		String output = "";
		for (int dummyCounterY = sizeBoard[0] - 1; dummyCounterY >= 0; dummyCounterY--) {
			for (int dummyCounterX = 0; dummyCounterX < sizeBoard[1]; dummyCounterX++) {
				output = output + Character.toString(Connect4FieldBoard[dummyCounterY][dummyCounterX]) + " ";
			}
			output = output + "\n";
		}
		return output;
	}

	/**
	 * 
	 * @return The column and the row of the board.
	 */
	public int[] getBoardSize() {
		return sizeBoard;
	}

	/**
	 * 
	 * @return an array where the symbols are stored for player 1 and 2.
	 */
	public char[] getPlayerSymbol() {
		return playerSymbol;
	}

	/**
	 * this is used by CPU player to check various combinations and analyze the
	 * winning positions.
	 * 
	 */
	public void undoDropPiece() {
		Connect4FieldBoard[lastMove[0]][lastMove[1]] = '.';
	}

	/**
	 * 
	 * @return the entire board
	 */
	public char[][] getBoard() {
		return Connect4FieldBoard;
	}

}