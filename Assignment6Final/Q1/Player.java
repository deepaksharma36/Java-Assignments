

//import files are placed here.
import java.util.Scanner;

/**
 * This class lets the player play the connect four field game between two human
 * players or between a human player and the CPU. Every player has a name and a
 * symbol(game piece) assigned. PlayerInterface methods implements this method.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 */
public class Player implements PlayerInterface {

	private String name;
	private char GamePiece;
	private Connect4Field aConnect4Field;

	/**
	 * The game which player is playing.
	 * 
	 * @param aConnect4Field
	 *            is a class which executes the functionalities of the game and
	 *            stores the state of the game.
	 * @param name
	 *            name of the player
	 * @param GamePiece
	 *            symbol assigned to the player
	 */
	public Player(Connect4Field aConnect4Field, String name, char GamePiece) {
		this.aConnect4Field = aConnect4Field;
		this.GamePiece = GamePiece;
		this.name = name;

	}

	/**
	 * returns the name of the player and in the next move, the player will
	 * provide his input in the form of column number at the place where he
	 * wants to place his game piece.
	 */
	public char getGamePiece() {

		return GamePiece;
	}

	/**
	 * return the name of the player
	 */
	public String getName() {

		return name;
	}

	/**
	 * takes player input to make his move.
	 */
	public int nextMove() {

		int userInput;
		Scanner sc = new Scanner(System.in);
		userInput = sc.nextInt();

		return userInput;
	}

}
