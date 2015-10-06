
//import statements are placed here
import java.util.Scanner;

/**
 * view class takes inputs and shows the output on the interface/console as per
 * the requirement of the calculator.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 *
 */
public class View {
	/**
	 * takes the input using scanner method.
	 * 
	 * @return the user input to the controller.
	 */
	public String takeInput() {

		System.out.println("enter the mathematical expression :");
		Scanner scan = new Scanner(System.in);
		String calcStringExpression = scan.nextLine();

		return calcStringExpression;
	}

	/**
	 * shows the output
	 * 
	 * @param result
	 */
	public void showresult(String result) {
		System.out.println(result);
	}

}
