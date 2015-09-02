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
 * this program is to look for all the prime numbers between 2 to 100,000 where
 * - reverse is a prime number,
 * - the index of prime number and the index of reverse of the prime number are also the reverse of one another
 * - the binary form of the prime number is a palindrome
 * 
 * @author      deepak sharma
 * @author      sree lakshmi kurra
 */
class Primes
{
   /**
    * this prints all the prime numbers between 2 and 100,000.
    * @param primeNumber
    * @return
    */
  private static int[] primeNumberFinder(int [] primeNumber)
  {
  int iteration =1,k=0;
  int num =0;
       int index=1;
       int flag=0;
       ////int[] primeNumber = new int[100000];
       primeNumber[0]=2;
       //this iteration runs from 2 to 100,000 to check for prime numbers
       for (iteration = 2; iteration <= 100000; iteration++)         
       {    
             flag=0; 
           
             // 
             for(num =(int)Math.sqrt(iteration)+1; num>1; num--)
               {
                 if(iteration%num==0 )
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
  
  //elemenation function for removing non prime number for reversed primes
  /**
   * 
   * @param revPrimeNumber
   * @return
   */
  private static int[] primeNumberChecker(int [] revPrimeNumber)
  {
    int[] onlyRevPrimeNumber=new int[10000];
    int dummyI,dummyJ,flag=0,counter=0;
    for( dummyI=0; dummyI<revPrimeNumber.length;dummyI++)
      {
        flag=0;
      
      for( dummyJ=2;dummyJ<(int)Math.sqrt(revPrimeNumber[dummyI])+1;dummyJ++)
              {
                
              if ((revPrimeNumber[dummyI])%dummyJ==0)
              {
                  flag=1;
              }
                     
              }
      if (flag==0)
      {
         onlyRevPrimeNumber[counter]=revPrimeNumber[dummyI];
         counter =counter+1;
      }        
      }
      
     
        return onlyRevPrimeNumber;
    
  }
  /**
   * 
   * @param primeNumber
   * @return
   */
  private static int[] reverseOfNumbers(int[] primeNumber )
  {   
       int[] reverse = new int[10000];
       int q=0;
      for(q=0;q<primeNumber.length;q++)
      {
          reverse[q] = reverseNumber(primeNumber[q]);
      }
      
      for(int dummy=0;dummy<30;dummy++)
        {
         //System.out.println(reverse[dummy]);
        }
       
      //int [] onlyRevPrimeNumbers;
      //onlyRevPrimeNumbers=primeNumberChecker(reverse);
      for(int dummy=0;dummy<30;dummy++)
        {
         //System.out.println(onlyRevPrimeNumbers[dummy]);
        }
      return reverse;
  }
 /**
  * 
  * @param primeNumber
  * @return
  */
  public static int reverseNumber(int primeNumber){      
	  String reverseSinglePime = Integer.toString(primeNumber);
      String reverse = new StringBuffer(reverseSinglePime).reverse().toString();
      Integer primeInteger = Integer.parseInt(reverse);
      return primeInteger;
  }  
  
    private static String[] BinaryOfPrimes(int[] PrimeNumber) {
       int remainder,dummyI,PrimeNum;
        String[] binaryPrime = new String[PrimeNumber.length];
       for(dummyI=0;dummyI<PrimeNumber.length;dummyI++)
       {
       PrimeNum=PrimeNumber[dummyI];    
       String output="";
       while(PrimeNum>0)
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
 * 
 * @param primeNumbers
 * @param reverseNumbers
 * @return
 */
   private static int[] keychecker(int[] primeNumbers,int[] reverseNumbers){
       int dummyI=0,dummyJ=0,newY=0,keyPrime=0,keyReverse=0,keyReversePrime=0,counter=0;
       int[] keys = new int[primeNumbers.length];
       for(dummyI=0;dummyI<primeNumbers.length;dummyI++){
            newY=primeNumbers[dummyI];
            if(newY>0)
            {   
              for(dummyJ=0;dummyJ<reverseNumbers.length;dummyJ++){
                if(reverseNumbers[dummyJ]>0)
                {
                  if(newY==reverseNumbers[dummyJ]){
                   if (newY==73 || newY==37 )
                   {
                     //System.out.println("Your indexes J:"+(dummyJ+1)+" I here:"+(dummyI+1));
                   }
                   keyPrime = dummyI+1;
                   keyReverse = dummyJ+1;
                   keyReversePrime=reverseNumber(keyPrime);
                   if(keyReverse==keyReversePrime)
                    {
                       //System.out.println("I was looking for u only"+newY+"with Key"+dummyI);
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
     * 
     * @param str
     * @return
     */
    public static boolean isPalindrome(String str)
    {   
        	
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
               return isPalindrome(str.substring(1,str.length()-1));
            }
        		return false;
        	
    }
    /**
     * 
     * @param str
     */
    public static void finalPrimePrinting(String str)
    {
    	int dummyA,temp=0;
    	//System.out.println(str+"U r doing for me");
    	for(dummyA=str.length()-1;dummyA>=0;dummyA--)
    	{
    		
    		temp= temp+Integer.parseInt(Character.toString(str.charAt(dummyA)))*(int)Math.pow(2, str.length()-1-dummyA);;
    		
    		
    	}
    	System.out.println(temp);
    	
    	
    }
    /**
     * 
     * @param BinaryPrimeNum
     */
    public static void palindrome(String[] BinaryPrimeNum ){
        int dummyI;
         
        for(dummyI=0;dummyI<BinaryPrimeNum.length;dummyI++)
        { 
            //System.out.println("hello");
            if(isPalindrome(BinaryPrimeNum[dummyI]))
            {
              finalPrimePrinting(BinaryPrimeNum[dummyI]);
            }
        }
            
    }
   /**
    * 
    * @param args
    */
    public static void main (String[] args)
    { int [] primeNumber;
    int[] primeNumberDec = new int[10000];
    int[] OnlyprimeNumberRev;
    int[] sameIndexPrimeNum;
    String[] BinaryPrimeNum;
    
    primeNumber=primeNumberFinder(primeNumberDec);
    
    OnlyprimeNumberRev=reverseOfNumbers(primeNumber);
    
    sameIndexPrimeNum = keychecker(primeNumber,OnlyprimeNumberRev);
    
    BinaryPrimeNum=BinaryOfPrimes(sameIndexPrimeNum);
    
    palindrome(BinaryPrimeNum);
   
    
    //only for testing
    for(int dummy=0;dummy<30;dummy++)
    {
     //System.out.println(primeNumber[dummy] +"Index: " +(dummy+1));
     
    }
    
    for(int dummy=0;dummy<30;dummy++)
    {
    // System.out.println(OnlyprimeNumberRev[dummy] +"Index: " +(dummy+1));
     
    }
    //System.out.println("######################");
    for(int dummy=0;dummy<7;dummy++)
    {
     //System.out.println(BinaryPrimeNum[dummy]+ "   "+sameIndexPrimeNum[dummy]);
     //System.out.println();
     
    }
    //System.out.println(isPalindrome("1001001"));
   }
  
    
}