/**
 * This is a sample algorithm for LCS, all the implementation will
 * implement Algo interface and provide the definition of execute method
 * Execute method will return object of result class 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040

 */
public class sampleAlgo implements Algo {

	public Result execute(String stringA,String stringB) {
		
		//startTime= System.currentTimeMillis();
		//in this class Algorithm will be implemented and execution time will be recorded
		//long stopTime = System.currentTimeMillis();
	    //long elapsedTime = stopTime - startTime;
		
		return new Result(5, "sample", 999,24,stringA.length(),stringB.length());
	}


}
