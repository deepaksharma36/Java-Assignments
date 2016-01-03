import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 * view class implements the remote interface ViewInterface. the object of this
 * class is used by the server for invoking show and take input ouput from
 * various clients. view class takes inputs and shows the output on the
 * interface/console as per the requirement of the game at various stages.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 * 
 */
public class View extends UnicastRemoteObject implements ViewInterface {
	protected View() throws RemoteException {
		super();

	}

	/**
	 * shows the output to the players
	 * 
	 * @param s
	 *            input sent by the controller to show it on the
	 *            interface/console.
	 */
	public void showOutput(Object s) throws RemoteException {
		System.out.println(s);

	}

	/**
	 * takes the input from the players.
	 * 
	 * @return the user input to the controller.
	 */
	public int userInput() throws RemoteException {
		int userInput;
		Scanner sc = new Scanner(System.in);
		userInput = sc.nextInt();

		return userInput;

	}

}
