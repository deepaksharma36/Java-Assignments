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
 *  digits and operators in the precedence order.
 * @author Sharma, Deepak  DS5930
 * @author Kurra, Sree Lakshmi SK9040
 *
 */
public class Calculator {
	/**
	 * this method  checks if the input operator is valid or invalid.
	 * @param calcString
	 * @return returns true for valid input operator and false for invalid
	 *  input operator.
	 */
	public static boolean isValidOperator(String calcString, int digitSize, 
			Boolean lastWasDigit, boolean lastWasOpe)
	{   if(digitSize>0 && lastWasDigit==true && lastWasOpe==false)
	{
		String[] check = {"+", "-","*","/","%"};	
		return Arrays.asList(check).contains(calcString);
	}
	else
	{
		System.out.println("Invalid Expression");
		System.exit(0);
		return false;
	}
		
		
	}
	/**
	 * this method compares the precedence of two operators.
	 * @param precedence string array which holds the precedence order of 
	 * the operators.
	 * @param operatorNew first operator used while comparing the two
	 *  operators
	 * @param operatorOld second operator used while comparing the two
	 *  operators
	 * @return returns 1 if operatorNew is higher or equal in the precedence
	 *  order than operatorOld, else returns 2.
	 */		
	public static int getPrecedence(String[] precedence,String operatorNew,
			String operatorOld )
	{	int dummyA,preOpe1=0,preOpe2=0;
		// this iterates over the precedence array to determine the priority
		//order of the input operators.
		for(dummyA=0;dummyA<precedence.length;dummyA++)
	{
		if(operatorNew.equals(precedence[dummyA]))
		{
			preOpe1=dummyA;
		}
		
		if(operatorOld.equals(precedence[dummyA]))
		{
			preOpe2=dummyA;
		}
		
	}
		if (preOpe1>=preOpe2)
		{
		  
			return 1;
			
		}
		else
		{
		   return 2;
		}
		
		}
	/**
	 * this main function takes input from the user and prints the output
	 *  after calculation according to the precedence order.
	 * @param args string array to store the input array from user.
	 */
	public static void main(String[] args) {
		
		int dummyA;float variable1,variable2,sFlag=0;
		float temp; int precedenceNumber;
		boolean lastWasDigit=false, lastWasOpe=true;
		String optNew,tempOpt;
		//new String array of length 6;
		String[] precedence={"+","-","%","/","*"}; 
		String[] calcString = args;//
		//for a valid input last element should be a numeric value
		if(!args[args.length-1].matches("(\\+)?(\\-)?\\d+(\\.\\d+)?") 
				&& !args[args.length-1].equals(" "))
		{
			System.out.println("Invalid Expression");
			System.exit(0);
		}
		//to create a stack to store integers in float.
		Stack <Float> digits = new Stack<Float>();
		//to create a stack to store all the operators. 
		Stack <String> operator = new Stack<String>();
		// using stack data structure to implement calculator.
		for(dummyA=0;dummyA<=calcString.length-1;dummyA++)
		{
			//checks for the digits in the string. Once found, extracts the
			//numeric value from the input expression.
			//Converts into integer value to push it into the stack.
			if(calcString[dummyA].matches("(\\+)?(\\-)?\\d+(\\.\\d+)?"))
		    {
				if (lastWasOpe==true && lastWasDigit==false)
				{digits.push(Float.parseFloat(calcString[dummyA]));
				 lastWasDigit=true;
				 lastWasOpe=false;
				}
				else
				{ System.out.println("Invalid Expression");
				  System.exit(0);
				}
					
				
			}
			//Extracting valid operators for non numerical values using
			//isValidOperator function.
			else if(isValidOperator(calcString[dummyA], digits.size(),
					lastWasDigit,lastWasOpe ))
			{	
				lastWasDigit=false;
				lastWasOpe=true;
				//optNew holds the new operator that is encountered while 
				//reading the user input string.
				optNew=calcString[dummyA];
				if(operator.size()>0)
				{               // tempOpt holds the previous operator which
					            //is popped from the top of the stack to 
								//compare with the current operator. 
				            	tempOpt=operator.pop();
				            	//when the precedence of previous operator is
				            	//lower than the precedence of the present
				            	//operator, then operators are pushed into the
				            	//stack.
				                precedenceNumber=getPrecedence(precedence,
				                		optNew,tempOpt);
				                if(precedenceNumber==1)
				              {
					              operator.push(tempOpt);
					              operator.push(optNew);
				              }
				                //if the precedence of current operator is
				                //lower than the precedence of previous 
				                //operator, then the operation will be 
				                //performed with the higher precedence operator
				                //and the result is pushed into the float 
				                //stack. 
				                else
				                     { //runs until the above condition is 
				                	   //satisfied.
				                	   while(  getPrecedence(precedence,optNew,
				                			   tempOpt)==2)
				                	   {sFlag=0;
				                	   variable1=digits.pop();
				                       variable2=digits.pop();				                       			                	   
                                       temp=PerformOperation(tempOpt,variable2,
                                    		   variable1);
                                       digits.push(temp);
					                   if(!operator.empty()) 
					                   { tempOpt=operator.pop();
					                     sFlag=1;
					                   }
					                   else
					                   {
					                	   //if the operator stack is empty, 
					                	   //then breaks the while loop.
					                	   break;
					                	   
					                   }
				                	   }
				                	   //to push the curent and previous 
				                	   //operator back into the stack.
				                	   if(sFlag==1)
				                	   operator.push(tempOpt);
				                	   				                	   
					                   operator.push(optNew); 
				                     
				                     }
				}
				else
				{
					//System.out.println(optNew);
					operator.push(optNew);
				}
				
				
			}
			else
			{
				
				System.out.println("Invalid input");
				System.exit(0);
			}
		}
		//once the entire input character string is pushed into the stack we
		//perform operations from higher precedence order to lower precedence 
		//order.
		// runs until the operator stack is empty.
		while(!operator.isEmpty())
		{
			if(digits.size()==1)
			{
				System.out.println(digits.pop());	
			}
			
		if(digits.size()>=2)
		{
			variable1=digits.pop();
			variable2=digits.pop();
			digits.push(PerformOperation(operator.pop(),variable2 , variable1));
			
		}
		}
		  System.out.println(digits.pop());
		}
	/**
	 * to perform the respective operations ('+','-','%','*','/')
	 * @param operation operator to perform the operation between value1 and
	 *  value2.
	 * @param value1 input value 1
	 * @param value2 input value 2
	 * @return returns the output after the calculation.
	 */
	public static float PerformOperation(String operation, float value1, 
			float value2 )
	{		
		if(operation.equals("+"))
		{			
			return value1+value2 ;
			
		}
		
		else if(operation.equals("-"))
		{
			return value1-value2 ;
		}
		else if(operation.equals("*"))
		{
			return value1*value2 ;
		}
		else if(operation.equals("/"))
		{
			if(value2!=0)	
			{
			return value1/value2 ;
			}
			else
			{
				System.out.println("Invalid, undefined while divided by zero");
				System.exit(0);
				return 1.0f;
			}
		}
		else if(operation.equals("%"))
		{
			return value1%value2 ;
		}
		else
		{
			System.out.println("Invalid Operator!");
			System.exit(0);
			return 1.0f;
		}

	}

}
