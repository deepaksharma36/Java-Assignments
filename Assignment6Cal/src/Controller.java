
/**
 * This class controller interacts with the view and the model to solve the
 * mathematical expression
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 *
 */
public class Controller {
	public void runCalculator() {
		Model m = new Model();
		View v = new View();
		float result = m.calculate(stringToStaringArray(v.takeInput()));

		//
		if (Model.error != "NoError") {
			v.showresult(Model.error);
		} else {

			v.showresult(Float.toString(result));
		}
	}

	/**
	 * converts string to string array for calculate method of the model.
	 * 
	 * @param calcStringExpression
	 * @return
	 */
	public String[] stringToStaringArray(String calcStringExpression) {
		String[] strAry;
		String[] StrAryCompress;
		char lastChar = '(';
		int arryCounter = -1;

		strAry = new String[calcStringExpression.length()];
		for (int StringCounter = 0; StringCounter < calcStringExpression.length(); StringCounter++) {
			if ((int) calcStringExpression.charAt(StringCounter) >= 48
					&& (int) calcStringExpression.charAt(StringCounter) <= 57) {
				if ((int) lastChar >= 48 && (int) lastChar <= 57) {
					strAry[arryCounter] = strAry[arryCounter]
							+ Character.toString(calcStringExpression.charAt(StringCounter));

					lastChar = calcStringExpression.charAt(StringCounter);
				} else {
					arryCounter++;
					strAry[arryCounter] = Character.toString(calcStringExpression.charAt(StringCounter));
					lastChar = calcStringExpression.charAt(StringCounter);
				}
			} else {
				if (calcStringExpression.charAt(StringCounter) != ' ') {
					arryCounter++;
					strAry[arryCounter] = Character.toString(calcStringExpression.charAt(StringCounter));
					lastChar = calcStringExpression.charAt(StringCounter);
				}

			}
		}
		StrAryCompress = new String[arryCounter + 1];
		for (int i = 0; i <= arryCounter; i++)
			StrAryCompress[i] = strAry[i];

		return StrAryCompress;

	}

	/**
	 * main method invokes run calculator method of the controller by creating
	 * an object of the controller
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Controller c = new Controller();
		c.runCalculator();

	}
}
