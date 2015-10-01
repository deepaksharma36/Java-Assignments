/**
 * Calcuator.java
 * 
 * Version:
 *      1.0.0
 * Revisions:
 * 
 */

//import statements are placed here.
import java.util.Arrays;
import java.util.Stack;
/**
 * this is a simple calculator which calculates the input expression using
 * digits and operators in the precedence order.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 *
 */
    public class Calci {
	public static int indexCounter = 0;
	public static int parenthesesCounter=0;
	/**
	 * this method checks if the input operator is valid or invalid.
	 * 
	 * @param calcString
	 * @return returns true for valid input operator and false for invalid input
	 *         operator.
	 */
	public static boolean isValidOperator(String operator, int digitSize,
			Boolean lastWasDigit, boolean lastWasOpe)

	{
		if (digitSize > 0 && lastWasDigit == true && lastWasOpe == false) {
			String[] check = { "+", "-", "*", "/", "%", "(", ")", "^" };
			return Arrays.asList(check).contains(operator);
		} else if (lastWasDigit == false && lastWasOpe == true
				&& operator.equals("(")) {
			return true;
		}
		/*
		 * else if(lastWasDigit==true && lastWasOpe==false && operator==")") {
		 * return true; }
		 */

		else {
			System.out.println("Invalid Expression from validation Funtion");
			System.exit(0);
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
	public static int getPrecedence(String[] precedence, String operatorNew,
			String operatorOld) {
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
	 * this main function takes input from the user and prints the output after
	 * calculation according to the precedence order.
	 * 
	 * @param args
	 *            string array to store the input array from user.
	 */
	public static float calculator_controller(String[] calcString) {
		float variable1, variable2, sFlag = 0;
		float temp;
		int precedenceNumber;
		boolean lastWasDigit = false, lastWasOpe = true;
		String optNew, tempOpt;
		// new String array of length 6;
		String[] precedence = { "+", "-", "%", "/", "*", "^" };

		// for a valid input last element should be a numeric value
		if (!calcString[calcString.length - 1]
				.matches("(\\+)?(\\-)?\\d+(\\.\\d+)?")
				&& !calcString[calcString.length - 1].equals(" ")
				&& !calcString[calcString.length - 1].equals(")")) {
			System.out
					.println("Invalid Expression as last element is not digit");
			System.exit(0);
			return 0;
		}
		// to create a stack to store integers in float.
		Stack<Float> digits = new Stack<Float>();
		// to create a stack to store all the operators.
		Stack<String> operator = new Stack<String>();
		// using stack data structure to implement calculator.
		for (; indexCounter <= calcString.length - 1; indexCounter++) { // System.out.println(indexCounter);
																		// checks
																		// for
																		// the
																		// digits
																		// in
																		// the
																		// string.
																		// Once
																		// found,
																		// extracts
																		// the
																		// numeric
																		// value
																		// from
																		// the
																		// input
																		// expression.
																		// Converts
																		// into
																		// integer
																		// value
																		// to
																		// push
																		// it
																		// into
																		// the
																		// stack.
			if (calcString[indexCounter].matches("(\\+)?(\\-)?\\d+(\\.\\d+)?")) {
				if ((lastWasOpe == true && lastWasDigit == false)) {
					digits.push(Float.parseFloat(calcString[indexCounter]));
					lastWasDigit = true;
					lastWasOpe = false;
				} else {
					System.out
							.println("Invalid Expression first starts from operator");
					System.exit(0);
					return 0;
				}

			}
			// Extracting valid operators for non numerical values using
			// isValidOperator function.
			else if (isValidOperator(calcString[indexCounter], digits.size(),
					lastWasDigit, lastWasOpe)) // 119 if condition
			{
				lastWasDigit = false;
				lastWasOpe = true;
				if (calcString[indexCounter].equals(")")) { // termination
					parenthesesCounter--;	
					System.out.println(parenthesesCounter + "( incountered");// condition
					temp = simple_Expression_Solver(operator, digits);

					System.out.println(temp);
					return temp;// simple_Expression_Solver(operator, digits);

				}

				else if (calcString[indexCounter].equals("(")) {
					indexCounter++;
					parenthesesCounter++;
					System.out.println(parenthesesCounter+") incountered");
					digits.push(calculator_controller(calcString));

					lastWasDigit = true;
					lastWasOpe = false;
					continue;

				}
				// optNew holds the new operator that is encountered while
				// reading the user input string.
				optNew = calcString[indexCounter];
				if (operator.size() > 0) { // tempOpt holds the previous
											// operator which
											// is popped from the top of the
											// stack to
											// compare with the current
											// operator.
					tempOpt = operator.pop();
					// when the precedence of previous operator is
					// lower than the precedence of the present
					// operator, then operators are pushed into the
					// stack.
					precedenceNumber = getPrecedence(precedence, optNew,
							tempOpt);
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
							temp = PerformOperation(tempOpt, variable2,
									variable1);
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
						// to push the curent and previous
						// operator back into the stack.
						if (sFlag == 1) {
							operator.push(tempOpt);
						}
						operator.push(optNew);

					}
				} // 164 line
				else {

					operator.push(optNew);
				}

			} // else IF valid operator
			else {

				System.out.println("Invalid input as input validation failed");
				System.exit(0);
			}
		} // for terminator
		return simple_Expression_Solver(operator, digits);

	}
	public static float simple_Expression_Solver(Stack<String> operator,
			Stack<Float> digits)

	{
		float variable1, variable2;

		// once the entire input character string is pushed into the stack we
		// perform operations from higher precedence order to lower precedence
		// order.
		// runs until the operator stack is empty.
		while (!operator.isEmpty()) {
			if (digits.size() == 1) {
				System.out.println(digits.pop());
			}

			if (digits.size() >= 2) {
				variable1 = digits.pop();
				variable2 = digits.pop();
				digits.push(PerformOperation(operator.pop(), variable2,
						variable1));

			}
		}
		return digits.pop();

	}
	public static void main(String[] args) {

		// String[] testing = {"1","+","(","2","*","(","3","+" ,"5",")","*",
		// "3",")","+","1"};
		String[] testing1 = { "2", "+", "(", "2", "/", "3", ")", "+", "1" };
		// String[] testing2 = {"2","+","(","3","^","2","^","4","*","1"};
		float ans =calculator_controller(args);
		if (parenthesesCounter==0)
		   System.out.println(ans);
		else
			System.out.println("Invalid Input as parentheses are not balalanced" );
		

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
	public static float PerformOperation(String operation, float value1,
			float value2)

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
				System.out.println("Invalid, undefined while divided by zero");
				System.exit(0);
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
			System.out.println("Invalid Operator!");
			System.exit(0);
			return 1.0f;
		}

	}
}

    class MyStack<TYPE>{
    	private int counterIndex=-1;
    	private TYPE[] simpleStack;
    	public MyStack(int size)
    	{
    	simpleStack = (TYPE[]) new Object[size];
    	//new TYPE[size];
    	}	
    	public TYPE pop()
    	{  try
    		{
    		counterIndex=counterIndex-1;
    		return this.simpleStack[counterIndex+1];
    		}
    	catch(Exception e)
    	{
    		System.out.println(e);
    		return null;
    	}
    		
    	
    	}
        public void push(TYPE val)
        {
        	counterIndex=counterIndex+1;
        	simpleStack[counterIndex]=val;
        	
        }
        
        public boolean isEmpty()
        {
        	if(counterIndex==-1)
        		return true;
        		return false;
        	
        }
    }