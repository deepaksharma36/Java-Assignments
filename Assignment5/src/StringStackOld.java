// this implementation implements the methods,
// but the methods are null methods;
public class StringStackOld implements StackInterfaceOld {

	public void push(Object item) {
	}

	public Object pop() {
		return null;
	}

	public Object peek() {
		return "hi";
	}

	public boolean isEmpty() {
		return true;
	}

	public static void main(String args[]) {
		StackInterfaceOld aStackInterfaceOld = new StringStackOld();

		/**
		 * Input parameter for push method is Object class type, String class
		 * extends from the Object class. A Parent class reference variable can
		 * hold the reference of a child type. Thus, the below given push method
		 * will work fine.
		 */
		aStackInterfaceOld.push("hello");

		/**
		 * In below statement, Object of Object class is hold by reference of
		 * String class which is a derived class of Object class. Though the
		 * compile time error is fixed by casting the Object type instance to
		 * String object, but at run time it may show "run time error" as we can
		 * call method of the derived class which may not be present in the base
		 * class. Here the complier has given a warning for the same.
		 */

		String aString = (String) aStackInterfaceOld.pop();
	}
	/*
	 * javac StringStackOld.java // explain this error StringStackOld.java:11:
	 * incompatible types // explain what a cast would do found :
	 * java.lang.Object // regarding possible compiler error detection required:
	 * java.lang.String String aString = aStackInterfaceOld.pop(); ^ 1 error
	 */

}