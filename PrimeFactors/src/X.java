 class X {
 
           public static void main( String args[] ) {
 
               int n = 0;
 
               here:
 
               while ( true )  {
               /** while true loop will run forever if it is not broken explicitly, program
                *  counter enter in this while(true) with n=0 value. 
            	*/ 
                      while ( ( ( n != 3 ) || ( n != 5 ) ) && ( n < 4 ) )  {
                    	  /**this loop will accept all the values of n if they are less then 4 AND it is not 
                    	   * equals to the 3 and 5 at same time( Which is not possible for any n).    
                    	   *  
                    	   *  First Iteration n=0: 
                    	   *  Condition in while loop will return true for n=0.
                    	   *  Step1 checking with if condition, pre increment will increase the value of n by 1 before perfroming 
                    	   *  comparing it with == sign as compiler executes expression from left to right. compartion gets failed 
                    	   *  but value of n become 1.
                    	   *  Step2 Program checking first else-if condition, before post increment the value of n(1) was compared with 
                    	   *  1, test was passed and value of n was increased to 2. print statement will print "b/  n is 2"
                    	   *  Step3 program will come out of if-else block as last else if statement was true then
                    	   *  execute the print statement "executing break here"
                    	   *  
                    	   *  Second Iteration n=2
                    	   *  
                    	   *  Condition in while loop will return true for n=2
                    	   *  Step1 Program will check the condition for if condition, pre increment will increase the value of
                    	   *  n by 1 before comparing it with 0 as compiler executes expression from left to right. comparsion gets 
                    	   *  failed but value of n become 3.  
                    	   *   Step2 Program checking first else-if condition, before post increment the value of n(3) was compared with 
                    	   *  1, test will fail but value of n will become 4.
                    	   *   Step3 Program checking second else-if condition, before post increment the value of n(4) was compared with 
                    	   *  2, test will fail but value of n will become 5.
                    	   *  Step4 Print statement of else part will execute and d/  n is 5 will be printed.
                    	   *  
                    	   *  Third Iteration n=5
                    	   *  Condition in while loop will return false for n=5
                    	   *  */
                              if ( ++n == 0 )
                                      System.out.println("a/  n is " + n );
                              else if ( n++ == 1 )    {
                                      System.out.println("b/  n is " + n );
                              } else if ( n++ == 2 )
                                      System.out.println("c/  n is " + n );
                              else
                                      System.out.println("d/  n is " + n );

                              System.out.println("    executing break here");

                      }

                      System.out.println( n % 2 == 0 ?
                                              ( n == 4 ? "=" : "!" )
                                            : ( n % 3 != 0 ? "3" : "!3" ));
                      /**
                       * Inside the print statement first the test n%2==0 will be executed as n=5 test will fail,
                       * As test has been failed hence second part after the :'s (( n % 3 != 0 ? "3" : "!3" )) will execute. 
                       * as n%3 !=0 for n=5 this inner test will return true and String "3" will be returned to the
                       * print statement. 3 will be printed.
                       */
                      
                      break here;
              }
          }
      }