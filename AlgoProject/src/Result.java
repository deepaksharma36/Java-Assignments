/**
 * The objective of this class is to store the results received after 
 * a particular execution for specific input 
  * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 */
public class Result {
private int SLength;
public int getSLength() {
	return SLength;
}
public int getRlength() {
	return Rlength;
}
private int Rlength;
private int LCSlength;
private String comSubSeq;
private long timeTaken;
private int numberOfRecursiveCalls;
/**
 * return the length of Common LCS
 * 
 */
public int getLCSlength() {
	return LCSlength;
}
/**
 * 
 * @return the Common SubSequance
 */
public String getComSubSeq() {
	return comSubSeq;
}
/**
 * 
 * @return the time taken for executing the implementation of algorithm
 */
public long getTimeTaken() {
	return timeTaken;
}
public Result(int LCSlenght, String comSubSeq, long timeTaken, int numberOfRecursiveCalls, int Slength, int Rlength)
{
	this.LCSlength=LCSlenght;
	this.comSubSeq=comSubSeq;
	this.timeTaken=timeTaken;
	this.numberOfRecursiveCalls=numberOfRecursiveCalls;
	this.SLength=Slength;
	this.Rlength=Rlength;
}



}
