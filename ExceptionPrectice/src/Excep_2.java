/**
 * This class plays with exceptions
 *
 * @version   $Id$
 *
 * @author    hp bischof
 *
 * Revisions:
 *	$Log$
 */

public class Excep_2 {

  private void f(int n) throws NullPointerException,
			       InterruptedException {
	System.out.println("f(" + n + ")" );
	switch (n)	{
	    case 1: System.out.println("hehe");
	    break;
	    //case 2:  throw new NullPointerException("1");
		
		default: throw new InterruptedException("default");
	}
  }

  public static void main(String[] args) {
	for (int index = 1; index < 3; index ++ )	{
		try {
			new Excep_2().f(index);
		} catch (NullPointerException e)	{
			e.printStackTrace();
		}
		
		catch (Exception e)	{
			System.out.println(e.getMessage() );
		}
		
	}
  }
}
