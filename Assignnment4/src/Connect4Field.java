
//import files are placed here.
import java.util.Scanner;

/**
 * This class implements the connect four game board. In this game two players
 * either two human players, or a human player and CPU play the game. The aim is
 * to create a series of 4 pattern, with the same symbol. 
 * @author Srilu * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040 *
 */
	public class Connect4Field implements Connect4FieldInterface {
	private int[] sizeBoard = { 8, 20 };
	private char[][] Connect4FieldBoard = new char[sizeBoard[0]][sizeBoard[1]];
	private int[] lastMove = new int[2];
	private PlayerInterface playerEA = null, playerEB = null;
	private PlayerInterface[] thePlayers = new PlayerInterface[2];
	private char[] playerSymbol = { '*', '+' };
	private String[] playerDetails = { "A", "B" };

	// default constructor for testing.
	public Connect4Field() {

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

	// drop th epiece in any gven column at appropriate place as per the game
	// req
	public void dropPieces(int column, char gamePiece) {
		int row = 0;
		column = column - 1;
		// System.out.println(column+"see the value");
		if (column >= 0 && column < sizeBoard[1]) {
			// this explores from boton to up n finds first empty place to fill
			// with the piece.
			while (row < sizeBoard[0]) {
				if (Connect4FieldBoard[row][column] == '.') {
					Connect4FieldBoard[row][column] = gamePiece;
					lastMove[0] = row;
					lastMove[1] = column;
					break;
				}
				row++;

			}
			if (row == sizeBoard[0])
				System.out.println("Invalid input column has no space " + row + column);
		} else {
			System.out.println("Invalid column");
		}

	}

	// cheks while placing the last move, does he create any combimnation on the
	// board to win.
	public boolean didLastMoveWin() {
		// TODO Auto-generated method stub
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
			// System.out.println(column);
			// System.out.println(row);
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
		 * - x x x checking this combination
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
		 * - x x x checking this combination
		 */
		if ((row - 3 >= 0 && column - 3 >= 0) && Connect4FieldBoard[row - 3][column - 3] != 'x') {
			if (doesItConnect(Connect4FieldBoard[row][column], row, row, column, column)
					&& doesItConnect(Connect4FieldBoard[row][column], row - 1, row - 1, column - 1, column - 1)
					&& doesItConnect(Connect4FieldBoard[row][column], row - 2, row - 2, column - 2, column - 2)
					&& doesItConnect(Connect4FieldBoard[row][column], row - 3, row - 3, column - 3, column - 3))
				return true;
		}

		/**
		 * x x x - checking this combination
		 */

		if ((row + 3 < sizeBoard[0] && column - 3 >= 0)) {
			if (doesItConnect(Connect4FieldBoard[row][column], row, row, column, column)
					&& doesItConnect(Connect4FieldBoard[row][column], row + 1, row + 1, column - 1, column - 1)
					&& doesItConnect(Connect4FieldBoard[row][column], row + 2, row + 2, column - 2, column - 2)
					&& doesItConnect(Connect4FieldBoard[row][column], row + 3, row + 3, column - 3, column - 3))
				return true;
		}

		/**
		 * x x x - checking this combination
		 */
		if ((row + 3 < sizeBoard[0] && column + 3 < sizeBoard[1])) {
			if (doesItConnect(Connect4FieldBoard[row][column], row, row, column, column)
					&& doesItConnect(Connect4FieldBoard[row][column], row + 1, row + 1, column + 1, column + 1)
					&& doesItConnect(Connect4FieldBoard[row][column], row + 2, row + 2, column + 2, column + 2)
					&& doesItConnect(Connect4FieldBoard[row][column], row + 3, row + 3, column + 3, column + 3))
				return true;
		}

		/**
		 * x - x x checking this combination
		 */
		if (row - 2 >= 0 && column + 2 < sizeBoard[1] && row + 1 < sizeBoard[0] && column - 1 >= 0) {
			if (doesItConnect(Connect4FieldBoard[row][column], row, row, column, column)
					&& doesItConnect(Connect4FieldBoard[row][column], row + 1, row + 1, column - 1, column - 1)
					&& doesItConnect(Connect4FieldBoard[row][column], row - 1, row - 1, column + 1, column + 1)
					&& doesItConnect(Connect4FieldBoard[row][column], row - 2, row - 2, column + 2, column + 2))
				return true;
		}

		/**
		 * x x - x checking this combination
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

	// checks weather last action reached to draw state or not
	public boolean isItaDraw() {
		for (int dummyCounterY = 0; dummyCounterY < sizeBoard[0]; dummyCounterY++) {
			for (int dummyCounterX = 0; dummyCounterX < sizeBoard[1]; dummyCounterX++) {
				if (Connect4FieldBoard[dummyCounterY][dummyCounterX] == '.')
					return false;
			}
		}
		
		return true;
	}

	// this methods overites the obg method, we use it to print the board.
	public String toString() {
		printConnect4FieldBoard();
		return "";
	}

	/**
	 * This method creates two human players or a human player and a CPU player,
	 * and stores these players in the array of player interface type, then
	 * enables them to play the game one by one.
	 */
	public void init(PlayerInterface playerEA, PlayerInterface playerEB) {
		
		System.out.println("Press 1 for playing with CPU or 2 for a two player game");
		int userInput;
		Scanner sc = new Scanner(System.in);
		userInput = sc.nextInt();
		if (userInput == 2) {
			for (int index = 0; index < 2; index++) {
				this.thePlayers[index] = new Player(this, playerDetails[index], playerSymbol[index]);
			}
		} else {
			this.thePlayers[0] = new Player(this, playerDetails[0], playerSymbol[0]);
			this.thePlayers[1] = new CPUplayer(this, "CPU", playerSymbol[1]);
		}

	}

	/**
	 * This method facilitates the entire game , and checks is any player won
	 * the game, or is it a draw match. This calls various methods to perform
	 * the game, it enables players to play one by one, and checks the state of
	 * the game after every turn.
	 */
	public void playTheGame() {
		int column;
		System.out.println(this);
		boolean gameIsOver = false;
		do {
			for (int index = 0; index < 2; index++) {
				System.out.println(this);
				if (isItaDraw()) {
					System.out.println("Draw");
					gameIsOver = true;
				} else {
					column = thePlayers[index].nextMove();
					dropPieces(column, thePlayers[index].getGamePiece());
					if (didLastMoveWin()) {
						gameIsOver = true;
						System.out.println("The winner is: " + thePlayers[index].getName());
					}
				}
			}

		} while (!gameIsOver);
	}

	/**
	 * 
	 * @return returns the last move of the player
	 */
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
	 * the player can make moves, and at cross(X) player cant make moves.
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

	public void printConnect4FieldBoard() {
		for (int dummyCounterY = sizeBoard[0] - 1; dummyCounterY >= 0; dummyCounterY--) {
			for (int dummyCounterX = 0; dummyCounterX < sizeBoard[1]; dummyCounterX++) {
				System.out.print(Connect4FieldBoard[dummyCounterY][dummyCounterX]);
			}
			System.out.println("");
		}
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
	 * this is used by CPU player to check various combinations and analyse the
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

        public static void main( String[] args ) {
    	
    	Connect4Field newGame = new Connect4Field("New Game");
    	newGame.playTheGame();
    	
    	
    	
    	
    	}
}