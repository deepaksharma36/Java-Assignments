/*
 * Execetion: java -ea Assertion_1
 */
import java.util.Scanner;  
    
public class Assertion_1{  
 public static void main( String args[] ){  
  
  Scanner scanner = new Scanner( System.in );  
  System.out.print("Enter ur age ");  
    
  int value = scanner.nextInt();  
  assert value>=18:" Not valid";  
  
  System.out.println("value is "+value);  
 }   
}  
  
 class myAssertion_1 {
	public void method( int value ) {
		assert 0 == value: "NotValid";
		System.out.println("wth");
		System.out.println("value = " + value );
		
	}

	public static void main( String[] args ) {
		Assertion_1 asertM = new Assertion_1();
		asertM.method( 1 );
		asertM.method( -1 );
	}
}
