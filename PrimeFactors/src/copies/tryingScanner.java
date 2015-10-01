package copies;

//Bimport java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class tryingScanner {
	public static void main(String[] args) {
		int numberOfPlayers = 0;
		//String playerName = "";
		//Scanner scan1;
		//ArrayList<Player> playerArr = new ArrayList<Player>();
		Player[] players; 
		// how many players are playing ?- input from user
		// create obj's of the player class same as the n, where the name of the
		// obj will be the name of the player
		// in a for loop all players will play their game o e by one by calling
		// their respective play game method
		/*System.out.println("provide the number of players playing hangman game");
		try{
		scan1= new Scanner(System.in);	
		numberOfPlayers = Integer.parseInt(scan1.nextLine());
		// I have to create Validation Function for My guess
		//scan1.close();
		}
		catch (Exception e) {
			System.out.print("I was never here");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		numberOfPlayers=(args.length)-1;
		players = new Player[numberOfPlayers];
		//scan2.close();
		for (int dummyCounter = 0; dummyCounter < numberOfPlayers; dummyCounter++)
		{
			/*System.out.println("provide the name of the player " + dummyCounter
					+ " who is playing hangman game");
			try{
			scan1 = new Scanner(System.in);
			playerName = scan1.nextLine();
			System.out.println(playerName);
			
			
			}
			catch(Exception e)
			{
				System.out.println("This is not working Exception"+e);
				
			}*/
			players[dummyCounter] = new  Player(args[dummyCounter+1]);
			
		}
	    while(Player.noOfActivePlayer>0)
	    {
	    	for ( int dummyCounter = 0; dummyCounter < numberOfPlayers; dummyCounter++)
	    	{
	    		while(players[dummyCounter].isPlayerActive())
	    		{
	    			players[dummyCounter].playGame();
	    		}
	    	}
	    }
		}
}
class Player {
	// TODO : clues should be assigned at random
	private String playerName;
	private int score;
	private Game myGame;
	private int correctGusses=0;
	private boolean playerActive = true;
	public static int noOfActivePlayer;
	

	public Player(String name) {
		playerName = name;
		score = 0;
		noOfActivePlayer++;
		myGame = new Game();

	}

	public void playGame() {
		if (getPlayerActive()) {
			System.out.println("Hi " + getPlayerName() + " ! It's your turn");
			String guessCharacter = guessWord();
			if (myGame.iswordPresent(guessCharacter) > -1){
				myGame.getPlayerAnswer()[myGame.iswordPresent(guessCharacter)] = guessCharacter;
			    updateScore(10);
			    correctGusses++;    
			}
			else
				myGame.IncreaseGuessesCount();
			showPlayerAnswer();
			setPlayerActive(isPlayerActive());
		}
	}

	public String guessWord() {
		System.out.println("Guess any charactor of below " + myGame.getWord().length() + "  charactors word" );
		for (int dummyCounter = 0; dummyCounter < myGame.getWord().length(); dummyCounter++) {
			System.out.print(this.myGame.getPlayerAnswer()[dummyCounter]);
		}
		System.out.println("");
		Scanner scan = new Scanner(System.in);
		String myGuess = scan.nextLine();
		// I have to create Validation Function for My guess
		//scan.close();
		return myGuess;

	}
	public void showPlayerAnswer() {
		System.out.println("");
		for (int dummyCounter = 0; dummyCounter < myGame.getPlayerAnswer().length; dummyCounter++) {
			System.out.print(myGame.getPlayerAnswer()[dummyCounter]);
		}
		System.out.println("");
	}

	public String getPlayerName() {
		return playerName;
	}

	public boolean getPlayerActive() {
		return playerActive;

	}

	public void setPlayerActive(boolean playerActive) {
		this.playerActive = playerActive;
	}

	public boolean isPlayerActive() {
		if (myGame.getGuessesCount() >= myGame.GameOverCount)
		{	
			noOfActivePlayer--;
			return false;
		}
		else if(correctGusses==myGame.getWord().length())
		{
			return false;
		}
		else
			return true;
	}
    public int getScore()
    {
    	return score;
    }
    public void updateScore(int score)
    {
    	this.score=this.score+score;
    }
	}

 class Game {
	private String[] playerAnswer ;
	private String word;
	private int guessesCount = 0;
	public final int GameOverCount = 8;
	public Game() {
		System.out.print("constructer called");
		int counter = 0;
		Random rand = new Random();
		// dont hardcode here
		int wordNumber = rand.nextInt(3900);
		Scanner sc;
		String line = "";
	try {
			sc = new Scanner(new File("/home/deepak/words"));

			while (sc.hasNext() && counter < wordNumber) {
				counter = counter + 1;
				line = sc.nextLine();
			}
			System.out.printf("%s ", line);
			word = line;
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   playerAnswer=new String[getWord().length()];
		// create setplayeranswer
		
		updatePlayerAnswer(-1, "?");
	}
	public String getWord() {
		
		return word;
	}
	public void IncreaseGuessesCount() {
		guessesCount = guessesCount + 1;
	}
	public int getGuessesCount() {
		return guessesCount;
	}	
	public void updatePlayerAnswer(int index, String c) {
		if (index > -1) {
			playerAnswer[index] = c;
		} else {
			// Creating new Answer array for a player
			playerAnswer = new String[getWord().length()];
			for (int dummyCounter = 0; dummyCounter < getWord().length(); dummyCounter++) 
				{
				playerAnswer[dummyCounter] = c;
				}
			}
	}
	public int iswordPresent(String guess) {
		for (int dummyCounter = 0; dummyCounter < getWord().length(); dummyCounter++) {
			if (Character.toString(getWord().charAt(dummyCounter)).equals(guess))
				return dummyCounter;

		}
		return -1;

	}
	public String[] getPlayerAnswer()
	{return playerAnswer;
		}
	}

