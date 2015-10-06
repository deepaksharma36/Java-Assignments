
/**
 * this class is a simple calculator which solves the input expression using
 * digits and operators in the precedence order and calculates the output.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 * 
 */
public class Model {
	public static int indexCounter = 0;
	public static int parenthesesCounter = 0;
	public static String error = "NoError";

	/**
	 * this main function takes the valid input from the user and prints the
	 * output after calculating according to the precedence order.
	 * 
	 * @param args
	 *            string array to store the input array from user.
	 */
	public float calculate(String[] args) {
		float ans = calculator_controller(args);
		if (parenthesesCounter == 0) {
			return ans;
		} else {
			error = "Invalid Input as parentheses are not balanced";

			return 0.0f;
		}
	}

	/**
	 * this method checks if the input operators and input Mathematical
	 * expression are valid or invalid.
	 * 
	 * @param calcString
	 * @return returns true for valid input operator and false for invalid input
	 *         operator.
	 */
	public static boolean isValidOperator(String operator, int digitSize, Boolean lastWasDigit, boolean lastWasOpe)

	{
		int counter = 0;
		if (digitSize > 0 && lastWasDigit == true && lastWasOpe == false) {
			String[] check = { "+", "-", "*", "/", "%", "(", ")", "^" };
			for (counter = 0; counter < check.length; counter++) {
				if (check[counter].equals(operator)) {
					return true;
				}

			}
			return false;
		} else if (lastWasDigit == false && lastWasOpe == true && operator.equals("(")) {
			return true;
		}

		else {
			error = "Invalid Expression from validation Funtion";

			return false;
		}

	}

	/**
	 * this method compares the precedence of two operators.
	 * 
	 * @param precedence
	 *            string array which holds the precedence order of the
	 *            operators.
	 * @param operatorNew
	 *            first operator used while comparing the two operators
	 * @param operatorOld
	 *            second operator used while comparing the two operators
	 * @return returns 1 if operatorNew is higher or equal in the precedence
	 *         order than operatorOld, else returns 2.
	 */
	public static int getPrecedence(String[] precedence, String operatorNew, String operatorOld) {
		int dummyA, preOpe1 = 0, preOpe2 = 0;
		// this iterates over the precedence array to determine the priority
		// order of the input operators.
		for (dummyA = 0; dummyA < precedence.length; dummyA++) {
			if (operatorNew.equals(precedence[dummyA])) {
				preOpe1 = dummyA;
			}

			if (operatorOld.equals(precedence[dummyA])) {
				preOpe2 = dummyA;
			}

		}
		if (preOpe1 >= preOpe2) {

			return 1;

		} else {
			return 2;
		}

	}

	/**
	 * This method convert Mathematical expression into its equivalent form
	 * where all the operators appear in the descending order of precedence. For
	 * solving sub expressions of mathematical expressions within parentheses,
	 * this method calls recursively.
	 * 
	 * @param calcString
	 *            mathematical expression from User
	 * @return Output of the provided Mathematical expression
	 */
	public static float calculator_controller(String[] calcString) {
		float variable1, variable2, sFlag = 0;
		float temp;
		int precedenceNumber;
		boolean lastWasDigit = false, lastWasOpe = true;
		String optNew, tempOpt;
		// new String array of length 6;
		String[] precedence = { "+", "-", "%", "/", "*", "^" };

		// for a valid input, last element should be a numeric value.
		if (!calcString[calcString.length - 1].matches("(\\+)?(\\-)?\\d+(\\.\\d+)?")
				&& !calcString[calcString.length - 1].equals(" ") && !calcString[calcString.length - 1].equals(")")) {
			error = "Invalid Expression as last element is not digit " + "or closing parenthsis";

			return 0;
		}
		// creating a stack to store integers in float.
		MyStack<Float> digits = new MyStack<Float>(calcString.length);
		// creating a stack to store all the operators.
		MyStack<String> operator = new MyStack<String>(calcString.length);
		// using stack data structure to implement calculator.
		for (; indexCounter <= calcString.length - 1; indexCounter++) {
			// checks for the digits in the string. Once found, extracts the
			// numeric values from the input expression and pushes it into the
			// stack.
			if (calcString[indexCounter].matches("(\\+)?(\\-)?\\d+(\\.\\d+)?")) {
				if ((lastWasOpe == true && lastWasDigit == false)) {
					digits.push(Float.parseFloat(calcString[indexCounter]));
					lastWasDigit = true;
					lastWasOpe = false;
				} else {
					error = "Invalid Expression as it starts from an operator";
					// System.out.println("Invalid Expression as it starts from
					// an operator");
					// System.exit(0);
					return 0;
				}

			}
			// to extract valid operators for non numerical values using
			// isValidOperator function.
			else if (isValidOperator(calcString[indexCounter], digits.size(), lastWasDigit, lastWasOpe)) // 119
																											// if
																											// condition
			{
				lastWasDigit = false;
				lastWasOpe = true;
				if (calcString[indexCounter].equals(")")) {
					// termination condition for recursive calls
					parenthesesCounter--;
					temp = simple_Expression_Solver(operator, digits);
					return temp;

				}

				else if (calcString[indexCounter].equals("(")) {
					// recursive call for solving sub expressions
					// problems
					indexCounter++;
					parenthesesCounter++;
					digits.push(calculator_controller(calcString));
					lastWasDigit = true;
					lastWasOpe = false;
					continue;

				}
				// optNew holds the new operator that is encountered while
				// reading the user input string.
				optNew = calcString[indexCounter];
				if (operator.size() > 0) {
					// tempOpt holds the previous
					// operator which is popped from the top of the
					// stack to compare with the current
					// operator.
					tempOpt = operator.pop();
					// when the precedence of previous operator is
					// lower than the precedence of the present
					// operator, then operators are pushed back into the
					// stack.
					precedenceNumber = getPrecedence(precedence, optNew, tempOpt);
					if (precedenceNumber == 1) {
						operator.push(tempOpt);
						operator.push(optNew);
					}
					// if the precedence of current operator is
					// lower than the precedence of previous
					// operator, then the operation will be
					// performed with the higher precedence operator
					// and the result is pushed into the float
					// stack.
					else { // runs until the above condition is
							// satisfied.
						while (getPrecedence(precedence, optNew, tempOpt) == 2) {
							sFlag = 0;
							variable1 = digits.pop();
							variable2 = digits.pop();
							temp = PerformOperation(tempOpt, variable2, variable1);
							digits.push(temp);
							if (!operator.empty()) {
								tempOpt = operator.pop();
								sFlag = 1;
							} else {
								// if the operator stack is empty,
								// then breaks the while loop.
								break;

							}
						}
						// to push the current and previous
						// operators back into the stack.
						if (sFlag == 1) {
							operator.push(tempOpt);
						}
						operator.push(optNew);

					}
				} else {

					operator.push(optNew);
				}

			} else {
				error = "Invalid input as sytex validations has failed";

			}
		} // for terminator
		return simple_Expression_Solver(operator, digits);

	}

	/**
	 * This method Solved the equivalent expression (all operators appears in
	 * the descending order) of the provided input Mathematical expression
	 * 
	 * @param operator
	 * @param digits
	 * @return
	 */
	public static float simple_Expression_Solver(MyStack<String> operator, MyStack<Float> digits)

	{
		float variable1, variable2;
		// once the entire input character string is pushed into the stack we
		// perform operations from higher precedence order to lower precedence
		// order. it runs until the operator stack is empty.
		while (!operator.empty()) {
			if (digits.size() == 1) {
				// System.out.println(digits.pop());
			}

			if (digits.size() >= 2) {
				variable1 = digits.pop();
				variable2 = digits.pop();
				digits.push(PerformOperation(operator.pop(), variable2, variable1));

			}
		}
		return digits.pop();

	}

	/**
	 * to perform the respective operations ('+','-','%','*','/')
	 * 
	 * @param operation
	 *            operator to perform the operation between value1 and value2.
	 * @param value1
	 *            input value 1
	 * @param value2
	 *            input value 2
	 * @return returns the output after the calculation.
	 */
	public static float PerformOperation(String operation, float value1, float value2)

	{
		float temp = 1;
		if (operation.equals("+")) {
			return value1 + value2;

		}

		else if (operation.equals("-")) {
			return value1 - value2;
		} else if (operation.equals("*")) {
			return value1 * value2;
		} else if (operation.equals("/")) {
			if (value2 != 0) {
				return value1 / value2;
			} else {
				error = "Invalid, undefined while divided by zero";

				return 1.0f;
			}
		} else if (operation.equals("%")) {
			return value1 % value2;
		}

		else if (operation.equals("^")) {
			temp = value1;
			while (value2 > 1) {
				temp = temp * value1;
				value2--;
			}
			return temp;
		}

		else {
			error = "Invalid Operator!";

			return 1.0f;
		}

	}
}

/**
 * This class implements Generic Stack Data Structure for Storing the values and
 * operator for calculator problem
 * 
 * @param <TYPE>
 *            Generic Stack class can be used for various type of the
 */
class MyStack<TYPE> {
	private int counterIndex = -1;
	private TYPE[] simpleStack;

	/**
	 * This constructor create an generic array of provided size
	 * 
	 * @param size
	 *            size of the Stack
	 */
	@SuppressWarnings("unchecked")
	public MyStack(int size) {
		try {
			simpleStack = (TYPE[]) new Object[size];
		} catch (Exception e) {

		}
	}

	/**
	 * This method perform pop operation of the stack DS
	 * 
	 * @return return the last inserted value from the stack
	 */
	public TYPE pop() {
		try {
			counterIndex = counterIndex - 1;
			return this.simpleStack[counterIndex + 1];
		} catch (Exception e) {

			Model.error = e.toString();
			return null;
		}

	}

	/**
	 * This method perform push operation of the stack DS
	 * 
	 * @param val
	 *            Insert new value at the top of the stack
	 */
	public void push(TYPE val) {
		counterIndex = counterIndex + 1;
		simpleStack[counterIndex] = val;

	}

	/**
	 * This method provide information about the state of the stack
	 * 
	 * @return False if no element present in stack else True
	 */
	public boolean empty() {
		if (counterIndex == -1)
			return true;
		return false;

	}

	/**
	 * This method return the size of the stack
	 * 
	 * @return size of stack
	 */
	public int size() {
		return counterIndex + 1;
	}
}
