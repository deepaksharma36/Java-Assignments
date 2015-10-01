public class TestFastCompetition	{

	FastCompetition<String> aStringStorage;
	public TestFastCompetition()	{
	}

	private void failure(String reason)	{
		System.err.println("You should never see this."); 
		System.err.println("Your program did not pass the test");
		System.err.println("Reason: " + reason);
		System.exit(0);
	}
	
	private void print(String reason)	{
		System.err.println("Reason: " + reason);
	}
	private void addTest()	{
		for ( int index = 0; index < 10000; index ++ )	{
			if ( ! (aStringStorage.add("hello"+ index) ) )
				failure("add");
			if ( aStringStorage.size() != index+1 )
				failure("size");

		}
	}
	private void containTest()	{
		for ( int index = 0; index < 10000; index ++ )	{
			if ( ! (aStringStorage.contains("hello"+ index) ) )
				failure("contains"+ index);
		}
	}
	private void sortTest()	{
		aStringStorage.sort();
		
		for ( int index = 0; index < 10000 - 1; index ++ )	{
			String thisOne = aStringStorage.elementAt(index);
			String nextOne = aStringStorage.elementAt(index+1);
			if ( thisOne.compareTo(nextOne) > 0 )
				failure("sortTest");
		}
	}
	private void removeTest()	{
		for ( int index = 0; index < 10000; index ++ )	{
			if ( ! (aStringStorage.remove("hello" + index)  ) )
				failure("remove " + index);
			if ( aStringStorage.size() != 10000 - index - 1)
				failure("remove.size"+ index);myShortTest();
		}
		if ( ! (aStringStorage.contains("hello" + 1)  ) )
			print("contains - expected");
		if ( ! (aStringStorage.remove("hello" + 1)  ) )
			print("remove - expected");
		
	}
	
	private void myShortTest()
	{
		//aStringStorage;
		for(int i=0; i<6;i++)
		{
			//System.out.println(aStringStorage.SortedArray[i]);
		}
	}
	
	private void myremoveTest()
	{   
		aStringStorage.add("hello5");
		aStringStorage.add("hello2");
		aStringStorage.add("hello6");
		aStringStorage.add("hello1");
		aStringStorage.add("hello4");
		aStringStorage.add("hello9");
		aStringStorage.add("hello3");
		aStringStorage.add("hello8");
		aStringStorage.add("hello10");
		//aStringStorage.sort();
		System.out.println(aStringStorage.elementAt(0));
		System.out.println(aStringStorage.elementAt(1));
		System.out.println(aStringStorage.elementAt(2));
		System.out.println(aStringStorage.elementAt(3));
		System.out.println(aStringStorage.elementAt(4));
		System.out.println(aStringStorage.elementAt(5));
		System.out.println(aStringStorage.elementAt(6));
		System.out.println(aStringStorage.elementAt(7));
		
		/*System.out.println(aStringStorage.elementAt(3));
		System.out.println(aStringStorage.elementAt(1));
		System.out.println(aStringStorage.elementAt(5));
		System.out.println(aStringStorage.elementAt(2));
		*/

		//aStringStorage.add("hello90");
		//aStringStorage.add("hello7");
		//aStringStorage.add("hello91");
		//aStringStorage.remove("hello5");
	   //aStringStorage.contains("hello8");
	   //aStringStorage.contains("hello7");
	   
		/*aStringStorage.add("hello5");
		aStringStorage.add("hello1");
		aStringStorage.add("hello3");
		aStringStorage.add("hello2");
		aStringStorage.add("hello4");
		//aStringStorage.add("hello91");
		aStringStorage.remove("hello1");
	   aStringStorage.contains("hello4");
	   //aStringStorage.contains("hello7");*/
	}
	private void stressTest( FastCompetition<String> aStringStorage)	{
		long startTime = System.currentTimeMillis();
	
		this.aStringStorage = aStringStorage;

		addTest();
		containTest();
		 sortTest();
		removeTest();
       // myremoveTest();
		
		//myShortTest();
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-startTime);
		
	}
	
	public static void main(String args[] )     {
		TestFastCompetition aTestFastCompetition = new TestFastCompetition();
		FastCompetition<String> aFastCompetition = new FastCompetition<String>(10000000);
		aTestFastCompetition.stressTest(aFastCompetition);
		
	}
}
