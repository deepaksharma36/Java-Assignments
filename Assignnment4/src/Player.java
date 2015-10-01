import java.util.Scanner;


public class Player implements PlayerInterface{
private String name;
private char GamePiece;
private Connect4Field aConnect4Field; 
public Player(Connect4Field aConnect4Field,String name, char GamePiece  )
{
	this.aConnect4Field=aConnect4Field;
	this.GamePiece=GamePiece;
	this.name=name;
	

}


public char getGamePiece() {
	// TODO Auto-generated method stub
	return GamePiece;
}


public String getName() {
	// TODO Auto-generated method stub
	return name;
}


public int nextMove() {
	// TODO Auto-generated method stub
	
	int userInput;
	Scanner sc = new Scanner(System.in);
	userInput= sc.nextInt();
	while(!aConnect4Field.checkIfPiecedCanBeDroppedIn(userInput))
	{
		System.out.println("Invalid input, try again");
	    return nextMove();	
		
	}
	
		return userInput;
}
	
}
