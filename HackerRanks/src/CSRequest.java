import java.util.Arrays;


public class CSRequest {
public int[] time;
public int processID;
public int[] localTime;
public CSRequest(int[] time, int processID, int[] localTime) {

	this.time=Arrays.copyOf(time, time.length);
	
	this.processID=processID;
	this.localTime=Arrays.copyOf(localTime, localTime.length);
}



}
