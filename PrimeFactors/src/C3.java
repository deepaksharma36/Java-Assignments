class C3 extends C1  implements  I4 {
	public static void main(String args[])	{
		C1 aC1 = new C1();
		C2 aC2 = new C2();
		C3 aC3 = new C3();
		I1 aI1 ; I2 aI2 = null; I3 aI3 = null; I4 aI4=null;
		
		aC1 = (C1)aC3;		// 1
		aI1 = (I1)aC3;		// 2
		//aI3 = (I3)aC2;		// 3 how how 
		aI2 = (I2)aC3;		// 4
		//aI1 = (aI1)aI2;		// 5
		aI2  = (I2)aI3;		// 6
		aI4=(I4)aC1;
		//aC1=(C1)aC2;
	}
}
