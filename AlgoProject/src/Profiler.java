import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is the controller program, based on the selection of the nature of the inputs Strings
 * and  the algorithm this will first create test cases and then 
 * execute the test cases, then the result will be saved in an Excel Sheet.   
  * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 */
public class Profiler {

	/**
	 * @param args
	 * args[0]=Names of the LCS algorithms(comma Separated)
	 * args[1]=File name of the excel Sheet
	 * args[2]=Number of test Cases
	 * args[3]=types of elements required by strings(comma Separated)
	 */
	static{
		System.out.println("Provide below arguments for sucessful execution");
		System.out.println("Provide names of Algorithm, comma separated\n<space>\n path/Name of the excel File(.xls)" +
				"\n<Space>\nNumber of test Cases\n<Space>\n type of elements required(comma Separated)");
		System.out.println("\t Name of Algorithms\n\tNaive\n\tRecursive\n\tMemoization\n\tHSB\n\tDynamic");
		System.out.println("\t type of Elements\n\talphabetics\n\tDNA\n\tnumaric\n\tbinary");
		
	}
	public static void main(String[] args) {
		//Creating a generator
		TestCaseGenerator aTestCaseGenerator = new TestCaseGenerator();
		//creating an executor for user mentioned algorithms
		TestCaseExecutor aTestCaseExecutor = new TestCaseExecutor(args[0],args[1]);
		//creating a list of type of element(numeric/alphabetic) required in strings
		ArrayList<String> testType=new ArrayList<String>();
		int numberofTestCases=Integer.parseInt(args[2]);
		int counter=0;
		String[] stringType=args[3].split(",");
		while(counter<stringType.length)
			testType.add(stringType[counter++]);
		
		try {
			//creating test Cases in text.txt file
			File testCaseFile =
				aTestCaseGenerator.TestCasesGenerator(numberofTestCases,testType ,"test.txt");
			//Executing the textCases
				aTestCaseExecutor.TestExecutor(testCaseFile);
			
		} catch (IOException e) {
			//if test case file were not found then IO exception will be raised by
			//executor or generator
			e.printStackTrace();
	
		}
		
	}

}
