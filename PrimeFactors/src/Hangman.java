

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * this program enables multiple/single player(s) to play the game hangman.
 * 
 * @author Deepak Sharma DS5930
 * @author Sree Lakshmi Kurra SK9040
 *
 */
public class Hangman {
	/**
	 * this main method initializes all the players and enables the players to
	 * play the game one after the other.
	 * 
	 * @param args
	 *            string contains path of the dictionary from where the words
	 *            are chosen at random, and also the name(s) of the player(s).
	 */
	public static void main(String[] args) {
		String word = generateWord(args[0]);
		int numberOfPlayers = 0;
		Player[] players;
		numberOfPlayers = (args.length) - 1;
		// array of an array which can hold the object of player class.
		players = new Player[numberOfPlayers];
		for (int dummyCounter = 0; dummyCounter < numberOfPlayers; dummyCounter++) {
			players[dummyCounter] = new Player(args[dummyCounter + 1], word);

		}
		while (Player.noOfActivePlayer > 0) {
			for (int dummyCounter = 0; dummyCounter < numberOfPlayers; dummyCounter++) {
				while ((players[dummyCounter].isPlayerActive())) {
					players[dummyCounter].playGame();
				}
			}
		}
		leaderBoard(numberOfPlayers, players);
	}

	/**
	 * this method picks the random word from the provided file to be guessed by
	 * the users.
	 * 
	 * @return the word that is chosen at random from the file.
	 */
	public static String generateWord(String fileName) {
		String word = "";
		Random rand = new Random();
		int counter = 0;
		int wordNumber = rand.nextInt(99170);
		Scanner sc;
		String line = "";
		try {
			sc = new Scanner(new File(fileName));
			while (sc.hasNext() && counter < wordNumber) {
				counter = counter + 1;
				line = sc.nextLine();
			}
			// for testing remove comments of the below line 
			//System.out.printf("%s ", line);
			word = line;
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return word;
	}

	/**
	 * this method gets the top five players whose scores are the highest among
	 * all the players.
	 * 
	 * @param numberOfPlayers
	 *            number of players playing the game
	 * @param players
	 *            array of all players who played the game
	 */
	public static void leaderBoard(int numberOfPlayers, Player[] players) {
		int maxScore = 0;
		String leader = "";
		int flag = 0;
		int numberOfRecords = 5;
		if (numberOfPlayers < 5) {
			numberOfRecords = numberOfPlayers;
		}
		int[] excludeCases = new int[numberOfRecords];
		System.out.println("Position" + "  " + "Player Name" + "  " + "Score");
		// checks the scores of all the players to get the top five high scorers
		// and displays the player nameswith their scores.
		for (int dummyCounter = 1; dummyCounter <= numberOfRecords; dummyCounter++) {
			maxScore = 0;
			for (int play = 1; play <= numberOfPlayers; play++) {
				flag = 0;
				if (players[play - 1].getScore() >= maxScore) {
					for (int dummyCounter1 = 1; dummyCounter1 <= numberOfRecords; dummyCounter1++) {
						if (excludeCases[dummyCounter1 - 1] == play) {
							flag = 1;
						}
					}
					if (flag == 0) {
						maxScore = players[play - 1].getScore();
						leader = players[play - 1].getName();
						excludeCases[dummyCounter - 1] = play;
					}
				}
			}
			System.out.println(dummyCounter + "  " + leader + "  " + maxScore);

		}
	}
}

class Player {
	/**
	 * @param playerName
	 *            name of the player playing the game
	 * @param score
	 *            score of the player
	 * @param playerActive
	 *            returns false if the player reached the maximum count or if
	 *            the player guessed all the letters right in the word. Else
	 *            true.
	 * @param noOfActivePlayer
	 *            has the number of active players playing the game.
	 */
	private String playerName;
	private int score;
	private Game myGame;
	private int correctGusses = 0;
	private boolean playerActive = true;
	public static int noOfActivePlayer;

	/**
	 * 
	 * @param name
	 *            player name
	 * @param word
	 *            word to be guessed
	 */
	public Player(String name, String word) {
		playerName = name;
		score = 0;
		noOfActivePlayer++;
		myGame = new Game(word);
		myGame.hangmanDrawing(0, 9, 0, 9, " ");

	}

	// this method plays the game if the player is active by calling all the
	// methods.
	// if the player is active, player is asked to choose a letter of the word.
	// if the letter is present in the word, then players answer and the score
	// is updated.
	// else the guesses count will increase by 1. the hangman drawing is drawn
	// and and the picture is displayed.
	public void playGame() {
		if (getPlayerActive()) {
			System.out.println("Hi " + getPlayerName() + " ! It's your turn");
			String guessCharacter = guessWord();
			if (myGame.iswordPresent(guessCharacter) > -1) {
				updatePlayerAnswer(guessCharacter);
				updateScore(10);

			} else {
				myGame.IncreaseGuessesCount();
				myGame.drawHangman(myGame.getGuessesCount());
				myGame.showHangman();
			}
			showPlayerAnswer();
			setPlayerActive(isPlayerActive());
		}
	}

	/**
	 * this method lets the user guess a letter in the word and checks if the
	 * letter is present in the word.
	 * 
	 * @return the letter guessed by the player
	 */
	public String guessWord() {
		System.out.println("Guess any charactor of below " + myGame.getWord().length() + "  charactors word");
		for (int dummyCounter = 0; dummyCounter < myGame.getWord().length(); dummyCounter++) {
			System.out.print(this.myGame.getPlayerAnswer()[dummyCounter]);
		}
		System.out.println("");
		Scanner scan = new Scanner(System.in);
		String myGuess = scan.nextLine();
		return myGuess;

	}

	// when the letter is present in the word, the empty character is replaced
	// with the letter(s) of the word.
	public void showPlayerAnswer() {
		System.out.println("");
		for (int dummyCounter = 0; dummyCounter < myGame.getPlayerAnswer().length; dummyCounter++) {
			System.out.print(myGame.getPlayerAnswer()[dummyCounter]);
		}
		System.out.println("");
	}

	// gets the name of the player
	public String getPlayerName() {
		return playerName;
	}

	public boolean getPlayerActive() {
		return playerActive;

	}

	public void setPlayerActive(boolean playerActive) {
		this.playerActive = playerActive;
	}

	// When the existing player is inactive, then the number of active players
	// decrements by 1.
	public boolean isPlayerActive() {
		if (myGame.getGuessesCount() >= myGame.GameOverCount) {
			noOfActivePlayer--;
			setPlayerActive(false);
			return false;
		} else if (correctGusses == myGame.getWord().length()) {
			setPlayerActive(false);
			noOfActivePlayer--;
			return false;

		} else
			return true;
	}

	public int getScore() {
		return score;
	}

	// updates the score(s) of the player(s)
	public void updateScore(int score) {
		this.score = this.score + score;
	}

	public String getName()

	{
		return playerName;
	}

	public void increaseCorrectGusses() {
		correctGusses++;
	}

	// updates the user(s) guessed letter in the word.
	public void updatePlayerAnswer(String c) {

		// Creating new Answer array for a player

		for (int dummyCounter = 0; dummyCounter < myGame.getWord().length(); dummyCounter++) {
			if (c.toLowerCase().equals(Character.toString(myGame.getWord().toLowerCase().charAt(dummyCounter)))) {
				myGame.getPlayerAnswer()[dummyCounter] = c;
				increaseCorrectGusses();
			}

		}

	}

}

class Game {
	private String[] playerAnswer;
	private String word;
	private int guessesCount = 0;
	public final int GameOverCount = 8;
	private String[][] hangmanDrawing = new String[10][10];

	public Game(String word) {
		this.word = word;
		playerAnswer = new String[getWord().length()];
		// create setplayeranswer

		intialPlayerAnswer("?");
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

	public void intialPlayerAnswer(String str) {
		for (int dummyCounter = 0; dummyCounter < getWord().length(); dummyCounter++) {
			playerAnswer[dummyCounter] = str;
		}
	}

	// checks if the guessed letter is present in the word.
	public int iswordPresent(String guess) {
		for (int dummyCounter = 0; dummyCounter < getWord().length(); dummyCounter++) {
			if (Character.toString(getWord().charAt(dummyCounter)).toLowerCase().equals(guess.toLowerCase()))
				return dummyCounter;

		}
		return -1;

	}

	public String[] getPlayerAnswer() {
		return playerAnswer;
	}

	// this methods assigns the values to the 2D array for the hangman
	// to be drawn.
	public void hangmanDrawing(int r1, int r2, int c1, int c2, String element) {
		for (int dummyCounter1 = r1; dummyCounter1 <= r2; dummyCounter1++) {
			for (int dummyCounter2 = c1; dummyCounter2 <= c2; dummyCounter2++) {

				this.hangmanDrawing[dummyCounter1][dummyCounter2] = element;
			}
		}
	}

	// this methods updates hangman 2D array according to the counter.
	public void drawHangman(int incorrectGusses) {
		switch (incorrectGusses) {
		case 8:
			hangmanDrawing(6, 6, 7, 7, "\\");
			hangmanDrawing(7, 7, 8, 8, "\\");
		case 7:
			hangmanDrawing(6, 6, 5, 5, "/");
			hangmanDrawing(7, 7, 4, 4, "/");
		case 6:
			hangmanDrawing(3, 3, 7, 7, "\\");
			hangmanDrawing(4, 4, 8, 8, "\\");
		case 5:
			hangmanDrawing(3, 3, 5, 5, "/");
			hangmanDrawing(4, 4, 4, 4, "/");
		case 4:
			hangmanDrawing(2, 2, 5, 5, "(");
			hangmanDrawing(2, 2, 7, 7, ")");
		case 3:
			hangmanDrawing(0, 5, 6, 6, "|");
		case 2:
			hangmanDrawing(0, 0, 2, 6, "-");

		case 1:
			hangmanDrawing(9, 9, 0, 4, "-");
			hangmanDrawing(0, 9, 2, 2, "|");
			break;

		}
	}

	// this methods draws the updates hangman.
	public void showHangman() {
		for (int dummyCounter1 = 0; dummyCounter1 <= 9; dummyCounter1++) {
			for (int dummyCounter2 = 0; dummyCounter2 <= 9; dummyCounter2++) {
				System.out.print(hangmanDrawing[dummyCounter1][dummyCounter2]);
			}
			System.out.println();
		}
	}

}