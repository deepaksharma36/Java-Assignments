/**
 * Number.java
 * 
 * Version:
 *      1.8.0_40
 * Revisions:
 * 
 */
//import java.util.StringBuffer;
/**
 * this program is to print all the prime numbers between 2 to 100,000 where
 * - its reverse is a prime number,
 * - the index of prime number and the index of reverse of the prime number 
 *   are also the reverse of one another,
 * - the binary form of the prime number is a palindrome.
 * 
 * @author      kurra, sree lakshmi SK9040
 * @author      sharma, deepak DS5930
 */
class Numbers
{
   /**
    * this method finds all the prime numbers between 2 and 100,000. 
    * @return returns all the primeNumbers in an array.
    */
  private static int[] primeNumberFinder()
  {
	  int [] primeNumber=new int[10000];
	  int iteration =1;
	  int number =0;
      int index=1;
      int flag=0;
      primeNumber[0]=2;
       //this iteration runs from 2 to 100,000 to check for prime numbers
       for (iteration = 2; iteration <= 100000; iteration++)         
       {    
             flag=0; 
             // this iteration runs from square root of the iterated number 
            // above to 1 to check for the prime numbers.
             for(number =(int)Math.sqrt(iteration)+1; number> 1; number--)
               {
                 if(iteration%number==0 )
                  {
                    flag=1;  
                  }
         
               }
      
              if (flag ==0)
             {
               primeNumber[index]= iteration;
               index=index+1;
             }
       }
         return (primeNumber);
  }
 
  /**
   * this method finds the reverse of all the prime numbers.
   * @param  array of primeNumbers.
   * @return array of reverse of prime numbers.
   */
  private static int[] reverseOfNumbers(int[] primeNumber )
  {   
       int[] reverse = new int[10000];
       int itrator=0;
       //this iteration runs on all prime numbers to produce the reverse of the primeNumbers.
      for(itrator=0;itrator<primeNumber.length;itrator++)
      {
          reverse[itrator] = reverseNumber(primeNumber[itrator]);
      }
      
      return reverse;
  }
 /**
  * this method only reverses the number.
  * @param primeNumber it is an integer number.
  * @return returns the reverse of the input number.
  */
  public static int reverseNumber(int primeNumber){      
	  //converting the number into String.
	  String reverseSinglePime = Integer.toString(primeNumber); 
	  //reversing the string using reverse method of StringBuffer class
	  String reverse = new StringBuffer(reverseSinglePime).reverse().toString();
      //converting string to integer using parseInt method.
      Integer primeInteger = Integer.parseInt(reverse);
      return primeInteger;
  }  
  /**
   * this method will covert the decimal prime numbers into binary format.
   * @param PrimeNumber 
   * @return returns binary representation of the input prime number.
   */
    private static String[] BinaryOfPrimes(int[] PrimeNumber) {
       int remainder,dummyI,PrimeNum;
        String[] binaryPrime = new String[PrimeNumber.length];
        // runs iteration from 0 to the total number of prime numbers in the
        //primeNumber array to convert them into binary format.
       for(dummyI=0;dummyI<PrimeNumber.length;dummyI++)     	  
       {
       PrimeNum=PrimeNumber[dummyI];    
       String output=""; //to store binary representation of the prime number.
       while(PrimeNum>0) // runs until the prime number is grater than 0.
       {
       remainder = PrimeNum %2; 
       PrimeNum=PrimeNum/2;
       output=output+Integer.toString(remainder);
       }
       
       binaryPrime[dummyI]=output;
       }
       return binaryPrime;
}
/**
 * this method checks the if the index of prime number is the reverse of the
 *  index of the reverse of prime number.
 * @param primeNumbers array of prime numbers.
 * @param reverseNumbers array of reverse of prime numbers.
 * @return returns the array of those prime numbers which satisfy the above.
 */
   private static int[] keychecker(int[] primeNumbers,int[] reverseNumbers){
       int dummyI=0,dummyJ=0,newY=0,keyPrime=0,keyReverse=0,keyReversePrime=0,
    		   counter=0;
       int[] keys = new int[primeNumbers.length];
       		// iterating over the primeNumers and finding them in reversePrimeNumbers'
            for(dummyI=0;dummyI<primeNumbers.length;dummyI++){
            newY=primeNumbers[dummyI];
            if(newY>0)
            {   
            	//iteration which runs from 0 to the length of reverse of
            	//prime numbers 
              for(dummyJ=0;dummyJ<reverseNumbers.length;dummyJ++){
                if(reverseNumbers[dummyJ]>0)
                {
                	// to check for common primes in primeNumbers array and
                	//reverse of prime numbers array.
                  if(newY==reverseNumbers[dummyJ]){
                   keyPrime = dummyI+1;
                   keyReverse = dummyJ+1;
                   keyReversePrime=reverseNumber(keyPrime);
                   //checks if the index of prime number is reverse of index
                   //of the reverse of prime number.
                   if(keyReverse==keyReversePrime)
                    {
                       keys[counter]=newY;
                       counter=counter+1;
                    }
                   }
               }
              }
            }
               }

       return keys;
           }
    /**
     * this method checks if the binary number of prime is a palindrome or not.
     * @param str string of binary representation of any number.
     * @return the true if the number is palindrome, or false otherwise.
     */
    public static boolean isPalindrome(String str)
    {   
    	 //termination condition for recursion.
        	if(str.length()==1) 
            {
              return true;
            }	
        	
        	else if(str.length()==2 && str.charAt(0)==str.charAt(1)) 
            {
             return true;
             }
        	
        	else if(str.length()>2 && str.charAt(0)==str.charAt(str.length()-1))
            {
        		//recursive call using recursion.
               return isPalindrome(str.substring(1,str.length()-1)); 
            }
        		return false;
        	
    }
    /**
     * this method prints the prime numbers which satisfies the palindrome 
     * condition.
     * @param str string of numbers which satisfies all the conditions.
     */
    public static void finalPrimePrinting(String str)
    {
    	int dummyA,temp=0;
    	for(dummyA=str.length()-1;dummyA>=0;dummyA--)
    	{
    		 //to convert the binary number to decimal number.
    		temp= temp+Integer.parseInt(Character.toString(str.charAt(dummyA)))*(int)Math.pow(2, str.length()-1-dummyA);;
    	}
    	System.out.println(temp);
    	
    	
    }
    /**
     * this method checks if the binary representation of prime numbers is 
     * palindrome of not using isPalindrome method. 
     * @param BinaryPrimeNum string array of prime numbers in binary format.
     */
    public static void palindrome(String[] BinaryPrimeNum ){
        int dummyI;
         
        for(dummyI=0;dummyI<BinaryPrimeNum.length;dummyI++)
        {             
            if(isPalindrome(BinaryPrimeNum[dummyI]))
            {
              finalPrimePrinting(BinaryPrimeNum[dummyI]);
            }
        }
            
    }
   /**
    * this main function calls all the necessary methods to output the prime 
    * numbers according to the conditions stated.
    * @param args 
    */
    public static void main (String[] args)
    { int [] primeNumber;
      int[] OnlyprimeNumberRev;
      int[] sameIndexPrimeNum;
      String[] BinaryPrimeNum;
    //to generate prime numbers.
    primeNumber=primeNumberFinder(); 
    // to reverse the prime numbers.
    OnlyprimeNumberRev=reverseOfNumbers(primeNumber); 
    //to check if the index of prime number is the reverse of the index of reverse of prime number.
    sameIndexPrimeNum = keychecker(primeNumber,OnlyprimeNumberRev); 
    // to convert the prime number to binary format.
    BinaryPrimeNum=BinaryOfPrimes(sameIndexPrimeNum);
    // to check if the binary format of the prime number is a palindrome or not.
     palindrome(BinaryPrimeNum);
   }
  
    
}