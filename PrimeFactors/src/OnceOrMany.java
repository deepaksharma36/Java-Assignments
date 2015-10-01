class OnceOrMany {

	public static boolean singelton(String literal, String aNewString) {
		return (literal == aNewString);
	}

	public static void main(String args[]) {
		String aString = "xyz";
		System.out.println("1.  xyz == aString: " + "xyz" == aString);
		/**
		 * OUTPUT = false,because the precidence of '+' operetor is more than 
     * '==' operator,hence concatenation between string will be performed 
     * before comparing."1.  xyz == aString" and "xyz" resultant string 
     * will be stored in String pool. Now comparision operation takes 
     * place between the two different strings but both string are stored
     * at different locations in the String pool.
		 * as the comparisions is not equal, opereator will return false.
		 */

		System.out.println("2.  xyz == aString: " + ("xyz" == aString));
		/**
		 * OUTPUT: True, 2. xyz == aString: as the expressions within the braces
     * are considered first before concatenation reference of String "xyz" 
     * was compared with aString and since the comparision of string is equal, 
     * these Strings are stored at the same location in String pool. '==' 
     * operetor retures true which was concatenated with rest of the String.
		 */

		String newString = new String("xyz");
		/**
		 * Above Statement created a new String object with value "xyz". A new
		 * memory location has been allocated in heap for this instance.
		 * compiler doesn't checked for the existing Strings with the same string
     * name. new string object is created
		 */

		System.out.println("xyz == new String(xyz)\n    "
				+ ("xyz" == newString));
		/**
		 * Output: "xyz == new String(xyz)\n   xyz False" 
		 * While solving the expression inthe braces first, '==' operator compares 
     * the references of the two strings. but since both strings are present
     * at different location in heap, operation will result false which was 
     * later concatenate with rest of the string in print statement.
		 */

		System.out.println("1: " + singelton("xyz", "xyz"));
		/**
		 * Output: 1: true, Above statement gives output true as we are
		 * comparing two String literals and All literals refer to same memory
		 * as Java keeps only one copy( in Strings pool inside main heap memory
		 * (java 1.7 and above)) which are assigned with the same value. Thus
		 * Boolean operation '==' in singlton method returns True
		 */

		System.out.println("2: " + singelton("xyz", new String("xyz")));
		/**
		 * Output: 2: false, While creating a string object, the compiler allocates
		 * the new memory to the objects on different location from the original 
     * memory location of the same value literals, as we compare the references
     * of String literals with String objects which doesn't return the same 
     * result, thus Boolean operation '==' in singlton method returns False
		 */

		System.out.println("3: " + singelton("xyz", "xy" + "z"));
		/**
		 * Output = 3: true 
		 * While the expression with braces is solved first, concatenation between
     * string "xy" and "z", String "xyz" was passed to the method singlton.
     * As compiler compares the references of two String Literals with same 
     * values, all literals refer to same memory as Java keeps only one copy( 
     * in Strings pool inside main heap memory (java 1.7 and above) )which are
		 * assigned with same value. Thus Boolean operation == in singlton
		 * method returns True
		 */

		System.out.println("4: " + singelton("x" + "y" + "z", "xyz"));
		/**
		 * Output = 4: true 
		 * 
		 * While the expression with braces is solved first, concatenation between 
     * string "x","y" and "z" String "xyz" was passed to the method singlton. 
     * As compiler is comparing reference of two String Literals with same values,
     * all literals refer to same memory as Java keeps only one copy( in Strings
     * pool inside main heap memory  (java 1.7 and above))which are assigned with 
     * same value. Thus Boolean operation == in singlton method returns True
		 */

		System.out.println("5: "
				+ singelton("x" + "y" + new String("z"), "xyz"));
		/**
		 * Output = 5: false 
		 * 
		 * As memory to the string object "z" was allocated during run time,the
     * resultant concatenated string "xyz" was created during therun time. Hence 
     * both Strings "xyz" are not present at same memory location in heap thus 
     * Boolean operation == in singlton method returns False.
		 */
		System.out.println("6: " + singelton("x" + ("y" + "z"), "xyz"));
		/**
		 * Output = 6: true
		 * While solving the expression in the inner most bracket, concatenation 
     * between string "y" and "z", returns String "yz" which was further 
     * concatenated with String "x" and the result "xyz" was passed to the method
     * singlton.Here all literals refer to same memory as Java keeps only one copy( 
		 * in Strings pool inside main heap memory (java 1.7 and above))which 
		 * are assigned with the same value. Thus Boolean operation == in singlton
		 * method returns True
		 */

		System.out.println("7: " + singelton('x' + "y" + "z", "xyz"));
		/**
		 * Output = 7: true 
		 * After concatenation between Char 'x' and String "y" String "xy" was created
		 * which was further concatenated with String "z" and resultant String "xyz"
		 * was passed to the method singlton. Now compiler is compars the reference of 
     * String Literals with same values as Java keeps only one copy( in Strings pool inside 
		 * main heap memory (java 1.7 and above)) for all the literal which are assigned 
		 * with same value. Thus Boolean operation == in singlton method returns True
		 */
	}
}