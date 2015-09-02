/**
 * Number.java
 * 
 * Version:
 *      1.8.0_40
 * Revisions:
 * 
 */
package calculator;

import java.util.Arrays;
import java.util.Stack;
/**
 * 
 * @author Sharma, Deepak
 * @author Kurra, Sree Lakshmi
 *
 */
public class Calculator {
	/**
	 * this method  checks if the input operator is valid or invalid.
	 * @param calcString
	 * @return returns true for valid input operator and false for invalid input operator.
	 */
	public static boolean isValidOperator(String calcString)
	{
		String[] check = {"+", "-","*","/","%"};
		//System.out.println(Arrays.asList(check));
		return Arrays.asList(check).contains(calcString);
		
		
	}
	/**
	 * this method compares the precedence of two operators.
	 * @param precedence string array which holds the precedence order of the operators.
	 * @param operatorNew first operator used while comparing the two operators
	 * @param operatorOld second operator used while comparing the two operators
	 * @return returns 1 if operatorNew is higher or equal in the precedence order than operatorOld, else returns 2.
	 */		
	public static int getPrecedence(String[] precedence,String operatorNew,String operatorOld )
	{	int dummyA,preOpe1=0,preOpe2=0;
		// this iterates over the precedence array to determine the priority order of the input operators.
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
	 * this main function takes input from the user and prints the output after calculation according to the precedence order.
	 * @param args string array to store the input array from user.
	 */
	public static void main(String[] args) {
		
		int dummyA;float variable1,variable2,sFlag=0;
		float temp; int precedenceNumber;
		String optNew,tempOpt;
		//new String array of length 6;
		String[] precedence={"+","-","%","/","*"}; 
		String[] calcString = args;//{"2","/", "2", "%", "2"};
		Stack <Float> digits = new Stack<Float>();
		Stack <String> operator = new Stack<String>();
		// using stack data structure to implement calculator.
		for(dummyA=0;dummyA<=calcString.length-1;dummyA++)
		{
			//(int)calcString.charAt(dummyA)>=48 && (int)calcString.charAt(dummyA)<=57
			if(calcString[dummyA].matches("\\d+"))
		    // extracting numerical values from mathematical input expression and 
		    //converting into integer values for storing in stack.
			{
				digits.push(Float.parseFloat(calcString[dummyA])); 	
			}
			else if(isValidOperator(calcString[dummyA]))
			{
				//Extracting valid operators for non numerical values using
				//isValidOperator function.
				////////////////////////////////////////////////////////////
				//optNew holds the new operator that is encountered while reading the user input string.
				optNew=calcString[dummyA];
				if(operator.size()>0)
				{               // tempOpt holds the previous operator which
					            //is popped from the top of the stack to be comapred with the current operator. 
				            	tempOpt=operator.pop();
				                precedenceNumber=getPrecedence(precedence,optNew,tempOpt);
				                if(precedenceNumber==1)
				              {
					              operator.push(tempOpt);
					              operator.push(optNew);
				              }
				                else
				                     { 
				                	   while(  getPrecedence(precedence,optNew,tempOpt)==2)
				                	   {sFlag=0;
				                	   variable1=digits.pop();
				                       variable2=digits.pop();
				                       //System.out.println(variable1+"   "+variable2);
				                	   
                                       temp=PerformOperation(tempOpt,variable2,variable1);
                                       //System.out.println(""temp);
					                   digits.push(temp);
					                   if(!operator.empty()) 
					                   { tempOpt=operator.pop();
					                     sFlag=1;
					                   }
					                   else
					                   {
					                	   //System.out.println("I was here");
					                	   break;
					                	   
					                   }
				                	   }
				                	   
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
		////////////////////////////////////////////////////////////////////////////////////
		//System.out.println(operator.size());
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
	 * @param operation operator to perform the operation between value1 and value2.
	 * @param value1 input value 1
	 * @param value2 input value 2
	 * @return returns the output after the calculation.
	 */
	public static float PerformOperation(String operation, float value1, float value2 )
	{
		//System.out.println(operation);
		if(operation.equals("+"))
		{
			//System.out.println("I was here");
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
				System.out.println("Invalid String! you rTrying to divide by 0");
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
