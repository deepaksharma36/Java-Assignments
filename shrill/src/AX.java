class AX extends aaa {

  public int x;		

  public aaa a(int x)	{
	System.out.print("= in son");
	return this;
  }

  public static void main(String args[])	{
	AX aAX = new AX();
	aaa  aA  = (aaa)aAX;

	System.out.println("aAX.a(42)	" + aAX.a(42) ); //son
	System.out.println("aAX.a(43)	" + aAX.aa(43) );//dad

	System.out.println("aA.aa(44)	" + aA.aa(44) ); //dad
	System.out.println("aA.a(45)	" + aA.a(45) ); // son
  }
}
