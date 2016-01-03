/**
 * This program play's the connect four field game between two human players or
 * between a human player and the CPU.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 */
public class TestConnect4Field {

public Connect4Field aConnect4Field = new Connect4Field();
public Player aPlayer = new Player(aConnect4Field, "A", '+');
public Player bPlayer = new Player(aConnect4Field, "B", '*');

public void dropTest( int column ) {
	System.out.println("Can it be dropped in " +
			   column + ": " 	   +
			   aConnect4Field.checkIfPiecedCanBeDroppedIn(column));
}
public void testIt() {
	aConnect4Field = new Connect4Field();
	System.out.println(aConnect4Field);
	dropTest(-1); //validation
	dropTest(0);
	dropTest(1);
	aConnect4Field.dropPieces(1, '+');
	System.out.println(aConnect4Field);
	aConnect4Field.dropPieces(1, '*');
	System.out.println(aConnect4Field);
	aConnect4Field.didLastMoveWin();
	aConnect4Field.isItaDraw();
	aConnect4Field.init(aPlayer, bPlayer);
	
}

/**
 * Initiating new game
 * @param args
 */
public static void main( String[] args ) {
	//creates an object of connect four field for initiating a new game by 
	//calling play the game function. 
	new TestConnect4Field().testIt();
	Connect4Field newGame = new Connect4Field("New Game");
	newGame.playTheGame();
	
	
	
	
	}
}
