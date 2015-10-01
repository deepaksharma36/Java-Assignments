public class XXX {

	static XXX xObj = new XXX();
	/**
	 * @param args
	 */
	private int outer=9;
	private static int outerstatic=8; 
	class nonStatic {
		
        //static  int i =5;
		void c() {
		    outer=99;
		    
		    outerstatic=999;
			Static nObj = new Static();
			nObj.c();
			Static.b();
			g();
			f(); // non static inner class can access private/public /protacted
					// static/non static method/variable of outer class
					//
			System.out.println("nonstatic class nonstatic method");
			Static.b();
		}
		
		  //static void n() { System.out.println("PAY heed"); }
		  
		 // static method can only be declared in a static or top level type
		 

	}

	static class Static {
		// nothing from the outside is accessible 
		static int i =5;
		int oo=9;
		static void b() {
			outerstatic=7;
			//c();
			nonStatic nObj = new XXX().new nonStatic();
			nObj.c();
			//oo=9;
			f(); //outer static method can be called 
			new Static().c();
			System.out.println("static class static method");
		}

		void c() {
			//outer=99;
			outerstatic=7;
			nonStatic nObj = new XXX().new nonStatic();
			nObj.c();
			f();
			//g(); can't calls the non static method in the static 
			System.out.println("static class non static method");
			new XXX().outer=007;
		}

	}

	static void f() {
        
		System.out.println("main class  static method");
	}

	void g() {
		
		x();// non static to non static possible
		f();// non static to static possible
		new nonStatic().c();
		new XXX().new nonStatic().c();
		//new XXX().new Static().c();
		System.out.println("main class non static method");

	}

	void x() {
		System.out.println("main class non static method");
		new nonStatic().c();
		c();
		

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		f(); // static method can call static method
		new XXX().g(); // non static method can be called only by creating
					// object in a non static method
		XXX.Static.b();
		new XXX().new nonStatic().c();
		
		//xObj.new Static().c();
		
		Static.b();
		new Static().c();
		new Static().b();// just warning method be is static can be accessed in
							// static way

	}

}
