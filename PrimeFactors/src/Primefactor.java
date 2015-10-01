/**
 * this program takes input an integer number n from
 * command line and finds all prime numbers p_i; 1 ≤ i ≤ k; so such n = p1 * p2 * p3 â¦ * pk.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 * 
 */

public class Primefactor {
    /**
     * This method takes input from user from command line
     * and calculates its' prime factors
     * @param args args String array stores input from user
     */
	
	public static void main(String[] args)
	{   
		int number=Integer.parseInt(args[0]);
		// converted string type user input into Integer
		// using parseInt method of the Integer class
		int dummy=3;
		String output= "";
		//output variable stores the output of the program 
		System.out.print(number+"=");
		//Calculating all the factors of the 2 present in provided input number 
		while(number%2==0)
		{   output=output+"2*";    
			number=number/2;
		}
        //Calculating all the factors of various prime Number present in input Number
        for(dummy=3;dummy<=Math.sqrt(number)+1;dummy=dummy+2)
        {
          while(number%dummy==0)
          {   output=output+dummy+"*";
        	  number=number/dummy;
          }
          
        }
        // Condition for checking whether provided number itself is a prime number 
        // or multiple of a prime number which is grater than the square root of the 
        // number
        if(number>1)
        {
      	  output=output+number+"*";
        }
        
        //Printing the output
        System.out.println(output.substring(0,output.length()-1));
		
	}
}
