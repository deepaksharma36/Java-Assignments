public class S6 extends S5 {

  public int instanceV = 6;		

  public void both()	{
	instanceV = 100;
  }
  public String toString()      {
        return "S6: " + instanceV;
  }
  public int superA()      {
	  return super.instanceV;
  }


  public static void main(String args[]) {
 	S6 aS6 = new S6();
	S5 aS5 = (S5)aS6;
	
	System.out.println("1. aS6 =" + aS6 );
	System.out.println("2. aS5 =" + aS5 );

	System.out.println("3. aS6.superA(): " + aS6.superA());
	// System.out.println("3. aS6.superA(): " + super.S6.instanceV);

  }
}

