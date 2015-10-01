class AAA extends AA {
	/**
	 * Class AAA is inheriting class AA
	 */
	int aInt = 1;

	/**
	 * Constructor of class AAA. which will assign value 11 when any object of
	 * AA will be initialize.
	 */
	AAA() {
		aInt = 11;
	}

	/**
	 * Increase the value of aInt class field by 1
	 * 
	 * @return increased value of aInt
	 */
	public int intPlusPlus() {
		return ++aInt;
	}

	public static void main(String args[]) {
		AAA aAAA = new AAA();
		  /**
         * Object of AAA class 
         * with reference aAAA.
         * constructor of A class, AA class and AAA class has 
         * been invoked. aAA.aInt assigned to value 11
         * aInt fields of class A and AA was assigned 
         * value 11 using constructor of
         * class A and AA.
         */
		AA aAA = (AA) aAAA;
		/**
         * In above statement reference of AA class 
         * aAA was created. Previously created
         * object aAAA  upcasted to class AA.
         * and assigned to
         * newly created reference aAA of class AA. 
         */
		A aA = (A) aAA;
		/**
         * In above statement reference of A class 
         * aA was created. Previously created
         * object aAA  upcasted to class A.
         * and assigned to
         * newly created reference aA of class A. 
         */
		aAAA.intPlusPlus();
		/**
         * method intPlusPlus of object aAAA 
         * has been invoked.
         * value of aAAA.aInt become 12. 
         */
		aAA.intPlusPlus();
		/**
         * method intPlusPlus of object aAAA 
         * has been invoked because reference aAA 
         * is also referring to the same object. 
         * value of aAA.aInt become 13.
         */
		aA.intPlusPlus();
		/**
         * method intPlusPlus of object aAAA 
         * has been invoked because reference aA 
         * is also referring to the same object. 
         * value of aAA.aInt become 14.
         */
		System.out.println("aA:        " + aA);
	    /**
        
		 * aA implicitly invokes overridden method aAA.toString()
		 * in print statement.  
		 * *The only object exist in memory is of class 
         * AAA hence this.getClass().getName() gives 
         * AAA and aInt value for AA.aInt.(Due to overriding) 
		 * Finally it returns "AAA: 11" and print statement
		 * prints the return value.
		 */
		System.out.println("aAA:       " + aAA);
/**
        
		 * aAA implicitly invokes overridden method aAA.toString()
		 * in print statement.  
		 * *The only object exist in memory is of class 
         * AAA hence this.getClass().getName() gives 
         * AAA and aInt value for AA.aInt.(Due to overriding) 
		 * Finally it returns "AAA: 11" and print statement
		 * prints the return value.
		 */
		
		System.out.println("aAAA:      " + aAA);
		/**
         *Fields in Java are not overridden only 
         *become hidden when child class has 
         *same name Field. Field's binding to 
         *the object in Java is not a run time
         * phinomina it is decided at compile 
         * time.Hence according to the type of 
         * the object reference fields are 
         * accessed in java.
         * hence in our case, aAAA.aInt accessed the 
         * value of aInt filed associated with 
         * aAAA reference. 
          
         */
		System.out.println("aAAA:.aInt " + aAAA.aInt);
	}

}
