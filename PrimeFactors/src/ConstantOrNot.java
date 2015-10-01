/**
 * 
 * 
 * @author Deepak Sharma, ds5930
 * @author Sri ??????
 */
import java.util.Vector; // what does this line do?

/**
 * Above statement import java.util.Vector class in this program by doing this
 * we will be able to access any of the public or protected methods present in
 * this class. this class is present in the java.util package.
 * 
 */

class ConstantOrNot {

	private final int aInt = 3;
	/**
	 * initialize an int variable this variable can not change its value as it
	 * is final type and scope of the variable is limited to the ConstantOrNot
	 * class
	 */
	private final String aString = "abc";
	/**
	 * initialize an String variable this variable can not change its value as
	 * it is final type and scope of the variable is limited to the
	 * ConstantOrNot class
	 */
	private final Vector aVector = new Vector();

	/**
	 * Created an object of Vector class this and scope of the variable is
	 * limited to the ConstantOrNot class
	 */
	public void doTheJob() {
		System.out.print("Yep");

		// aInt = 3; //why would this fail?
		/**
		 * aInt is a final type variable we can't change the value of the
		 * variable after assigning it.
		 */
		// aString = aString + "abc"; //why would this fail?
		/**
		 * aString is a final type variable we can't assign it to new
		 * string/value after the first assignment.
		 */

		aVector.add("abc"); // why does this work?
		/**
		 * aVector is a final variable of type Vector it has reference of the
		 * 1st element of a Vector array. aVector.add("abc") add new element in
		 * the end of Vector but reference remain intact while invoking . this
		 * add method if we try to give it new memory, compiler will not let us
		 * do. for example aVactor= new Vector() not alowded
		 * */
	}

	public static void main(String args[]) {
		new ConstantOrNot().doTheJob();

	}
}