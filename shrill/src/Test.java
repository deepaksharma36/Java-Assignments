/*
 * Test.java
 * 
 * Doubts regarding array of object creation
 */
class AA {
	int x = 1;
}
public class Test {
	public static void main(String[] args) {
		int array_int[]; 
		array_int = new int[5]; 
		AA cl_a = new AA(); // reference to a object
		System.out.println("1 " + cl_a);
		AA[] cl_array;// reference to the array
		cl_array = new AA[5]; //released memory 
		cl_array[0]= new AA();
		System.out.println("2 " + cl_array); //print reference of clary
		System.out.println("3 " + cl_array[0]); //print reference of clary
		try {
			System.out.println("4 " + cl_array[0].x);
		} catch (Exception e) {
			System.out.println("5 " + e);

		}
	}

}