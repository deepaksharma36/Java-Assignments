/**
 * 
 * @author unknown
 *
 */

       public class AA extends A {
    	   /**
    	    * Class AA is inheriting class A 
    	    */
           int aInt = 1;
         /**
          * Constructor of class AA. which will assign 
          * value 11 when any object of AA will be initialize. 
         */
           AA() {
                 aInt = 11;
                 //System.out.println(aInt+" inside constructor AA");
           }
           /**
       	 * Increase the value of aInt class field by 1
       	 * @return increased value of aInt
       	 */
           public int intPlusPlus()      {
          	 //System.out.println(aInt + " inside intPlusPlus  AA");
                 return ++aInt;
          }
           /**
       	 * This method override the toString method of A class and returns class
       	 * name followed by value of class field aInt.
       	 * default implementation in Object.toString() prints the  class 
       	 * name followed by the object's hash code. 
       	 */
          public String toString()      {
          /**
           * this.getClass gives current object in memory.
           */
                return this.getClass().getName() + ": " + aInt;
          }
          //primeFactor wali file
          public static void main(String args[]) {
           
        	  AA aAA = new AA();
        	  /**
               * Object of AA class 
               * with reference aAA 
               * constructor of A class and AA class has 
               * been invoked. aAA.aInt assigned to value 11
               * super.aInt assigned to value 11 using constructor of
               * class A.
               */
                A   aA = (A)aAA;
          	  /**
                 * In above statement reference of A class 
                 * aA was created. Previously created
                 * object aAA  upcasted to class A.
                 * and assigned to
                 * newly created reference aA of class A. 
                 */
                aAA.intPlusPlus();
                /**
                 * method intPlusPlus of object aAA 
                 * has been invoked.
                 * value of aAA.aInt become 12. 
                 */
                aA.intPlusPlus();
                /**
                 * method intPlusPlus of object aAA 
                 * has been invoked because reference aA 
                 * is also referring to the same object. 
                 * value of aAA.aInt become 13.
                 */
                System.out.println(aA);
                /**
                  
        		 * aA implicitly invokes method aAA.toString()
        		 * in print statement. 
        		 * *The only object exist in memory is of class 
                 * AA hence this.getClass().getName() gives 
                 * AA and aInt value for AA.aInt. 
        		 * Finally it returns "AA: 13" and print statement
        		 * prints the return value.
        		 */
                System.out.println(aAA);
                /**
        		  * aAA implicitly invokes method aAA.toString()
        		 * in print statement.
        		 * The only object exist in memory is of class 
                 * AA hence this.getClass().getName() gives 
                 * AAA and aInt value for AA.aInt. 
        		 * Which returns "AA: 13" and print statement
        		 * prints the return value.
        		 */
                System.out.println("aA: " + aA.aInt);
                /**
                 *Fields in Java are not overridden only 
                 *become hidden when child class has 
                 *same name Field. Field's binding to 
                 *the object in Java is not a run time
                 * phinomina it is decided at compile 
                 * time.Hence according to the type of 
                 * the object reference fileds are 
                 * accessed in java.
                 * hance in our case, aA.aInt accessed the 
                 * value of aInt filed associated with 
                 * aA reference. 
                  
                 */
          }
        }
       
       
       
       