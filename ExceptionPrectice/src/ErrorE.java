public class ErrorE {

  private void thisMethodThrowsAnE(int index) throws Exception, Error {
	
	if ( index == 0 )	{
		System.out.println("How many times I got executed" );
		throw new Error("in thisMethodThrowsAnException");
	} else {

		System.out.println("fuckedup" );
		throw new Exception("in thisMethodThrowsAnException");
	}

  }

  private void caller()	{
	for ( int index = 0; index < 3; index ++ )	{
		try {
			thisMethodThrowsAnE(index);
		} catch (Exception e)	{
			e.printStackTrace();
		} catch (Error e)	{
			e.printStackTrace();
		} finally	{
			System.out.println("Finally");
			System.out.println("Ok, a few things to clean up" );
		}
	}
  }

  public static void main(String[] args) {
	new ErrorE().caller();
  }
}
