// What is the execution order?

public class ExceptionsAndInheritance2 extends
	ExceptionsAndInheritance1 {

//  private void importantFunction() {
  public void importantFunction() {
	System.out.println("ExceptionsAndInheritance2:importantFunction -->");
  }

  public static void main(String[] args) {
	ExceptionsAndInheritance2 e2 = new ExceptionsAndInheritance2();
	ExceptionsAndInheritance1 e1 = (ExceptionsAndInheritance2)e2;
		e2.importantFunction();
	try {
		e1.importantFunction();
	} catch (Exception e)	{
		System.out.println("Main ");
		e.printStackTrace();
	}
  }
}
