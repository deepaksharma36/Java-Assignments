/**
 * 
 * @author Unknown
 * @Version Unknown
 */
public class A {
	int aInt = 1;

	/**
	 * Constructor of Class A, Initialize class variable aInt with value 11
	 */
	A() {
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

	/**
	 * This method override the toString method of Object class and returns class
	 * name followed by value of class field aInt.
	 * default implementation in Object.toString() prints the  class 
	 * name followed by the object's hash code. 
	 */
	public String toString() {
		return this.getClass().getName() + ": " + aInt;
	}

	/**
	 * This method creates an object aA of class A then 
	 * call the method intPlusPlus of A class 
	 * @param args
	 */
	public static void main(String args[]) {
		A aA = new A();
		/**
		 * Created object of A, constructor was called 
		 * and value of aInt was set to 11 
		 */
		aA.intPlusPlus();
		/**
		 * Called method intPlusPlus which increased the value of 
		 * aInt by one. hence aInt=12 now.   
		 */
		System.out.println(aA);
		/**
		 * aA object implicitly call method 
		 * aA.toString() in print statement. which 
		 * returns "A: 12". Print statement print the same.
		 */
	}
}
