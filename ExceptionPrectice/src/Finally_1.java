
public class Finally_1 {

  private void caller()	 {
		try {
			throw new Exception("caller in thisMethodThrowsAnException");
		} catch (Exception e)	{
			//e.printStackTrace();
			System.out.println("caller");
		} finally	{
			System.out.println("Finally");
		}
  }
  private void exit()	 {
		try {
			throw new Exception("exit in thisMethodThrowsAnException");
		} catch (Exception e)	{
			//e.printStackTrace();
			 System.out.println("calling System.exit(0)");
			System.exit(0);
		} finally	{
			System.out.println("Finally");
		}
  }

  public static void main(String[] args) {
	new Finally_1().caller();
	new Finally_1().exit();
  }
}
