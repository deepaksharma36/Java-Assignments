

import java.util.Scanner;

/**
 * view class takes inputs and shows the output on the interface/console as per
 * the requirement of the game at various stages.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 *
 */
public class View {
	/**
	 * shows the output to the players
	 * 
	 * @param s
	 *            input sent by the controller to show it on the
	 *            interface/console.
	 */
	public void showOutput(Object s) {
		System.out.println(s);

	}

	/**
	 * takes the input from the players.
	 * 
	 * @return the user input to the controller.
	 */
	public int userInput() {
		int userInput;
		Scanner sc = new Scanner(System.in);
		userInput = sc.nextInt();

		return userInput;

	}

}
