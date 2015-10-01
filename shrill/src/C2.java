 class C1 {
  public C1()	{
	System.out.println("    in C1");
  }
  public C1(int x)	{
	System.out.println("    in C1!int x");
  }

}

public class C2 extends C1  {
  final  int a;
  static int anurag=8;
  public C2()	{
	
	a=5;
	System.out.println("    in C2");
  }
  public C2(int x)	{
	super(x);
	a=5;
	System.out.println("    in C2!int x");
  }

  public static void main(String args[])	{
	
	System.out.println("new C1() ... ");
	new C1();
	System.out.println("new C2() ... ");
	new C2();
	System.out.println("new C2(int x) ... ");
	new C2( 3 );
  }

}