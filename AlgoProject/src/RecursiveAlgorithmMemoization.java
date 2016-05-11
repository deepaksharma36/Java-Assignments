import java.util.HashMap;



public class RecursiveAlgorithmMemoization implements Algo {
	int numberOfRecursiveCall=0;
	HashMap<Strings,tempRecord > results = new HashMap<Strings, tempRecord>();
	//commSequance=new StringBuilder();
	public Result execute(String stringA, String stringB) {
		
		numberOfRecursiveCall=0;

		long sTime = System.currentTimeMillis();
		tempRecord aRecord = new tempRecord(new StringBuilder(), 0);
		tempRecord output=naiveRecusriveMemoLCS(stringA, stringB,aRecord);
		long stTime = System.currentTimeMillis();
	    long timeTaken = stTime - sTime;
		return new Result(output.length, output.commSubSequance.reverse().toString(), timeTaken, numberOfRecursiveCall-1, stringA.length(), stringB.length()) ;
	}

	
	public tempRecord naiveRecusriveMemoLCS(String A, String B,tempRecord aTempRecord){
		if (A.length()<1 || B.length()<1)
			return aTempRecord;
		numberOfRecursiveCall++;
		int l1 = A.length();
		int l2 = B.length();
		int temp1,temp2;
		if(A.charAt(l1-1)==B.charAt(l2-1)){
			Strings input =new Strings(A.substring(0,l1-1),B.substring(0,l2-1));
			if (results.containsKey(input))
				 return results.get(input);
			else
			{
				aTempRecord.commSubSequance.append(A.charAt(l1-1));
				aTempRecord.length++;
				aTempRecord = naiveRecusriveMemoLCS(A.substring(0,l1-1), B.substring(0,l2-1),aTempRecord);
				results.put(input, new tempRecord (new StringBuilder(aTempRecord.commSubSequance.toString()), aTempRecord.length+1));
				return aTempRecord;
			}
				 
		}
			
		else{
			Strings input1 =new Strings(A,B.substring(0,l2-1));
			Strings input2 =new Strings(A.substring(0,l1-1),B);
			tempRecord r1 = new tempRecord(new StringBuilder(aTempRecord.commSubSequance.toString()), aTempRecord.length);
			tempRecord r2=new tempRecord(new StringBuilder(aTempRecord.commSubSequance.toString()), aTempRecord.length);
			if(results.containsKey(input1))
				r1=results.get(input1);
			else{	
			    r1=naiveRecusriveMemoLCS(A, B.substring(0,l2-1),r1);
			    results.put(input1, r1);
			}
			if(results.containsKey(input2))
				r2=results.get(input2);
			else{	
			    r2=naiveRecusriveMemoLCS(A.substring(0,l1-1), B,r2);
			    results.put(input2, r2);
			}
			
		
			return r1.length>r2.length?r1 : r2;
		
	} 
	}

class Strings {
	String A;
	String B;
	Strings(String A, String B)
	{
		this.A=A;
		this.B=B;
	}
   public boolean compare(String A, String B){
	   if (this.A.equals(A) && this.B.equals(B))
		   return true;
	   else if (this.A.equals(B) && this.B.equals(A))
		   return true;
	   else 
		   return false;
	   
   }
   @Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + getOuterType().hashCode();
	result = prime * result + ((A == null) ? 0 : A.hashCode());
	result = prime * result + ((B == null) ? 0 : B.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Strings other = (Strings) obj;
	if (A.equals(other.B) && B.equals(other.A))
		return true;
	if (!getOuterType().equals(other.getOuterType()))
		return false;
	if (A == null) {
		if (other.A != null)
			return false;
	} else if (!A.equals(other.A))
		return false;
	if (B == null) {
		if (other.B != null)
			return false;
	} else if (!B.equals(other.B))
		return false;
	return true;
}
private RecursiveAlgorithmMemoization getOuterType() {
	return RecursiveAlgorithmMemoization.this;
}
   
}

class tempRecord{
	public tempRecord(StringBuilder commSubSequance, int length) {
	
		this.commSubSequance = commSubSequance;
		this.length = length;
	}
	StringBuilder commSubSequance;
	int length;
	
	
}


}