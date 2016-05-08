import java.util.ArrayList;



public class HirshbergAlgorithm implements Algo {
int numberofRecursiveCall=0;
	public Result execute(String stringA, String stringB) {
		long sTime = System.currentTimeMillis();
		StringBuilder result=AlgorithmC(new StringBuilder(stringA), new StringBuilder(stringB));
		long stTime = System.currentTimeMillis();
		return new Result(result.length(), result.toString(), stTime-sTime, this.numberofRecursiveCall,stringA.length() , stringB.length());
	}
	public ArrayList<Integer> AlgorithmB(StringBuilder A,StringBuilder B){
		
	
	int[][] table = new int[2][B.length()+1];
		
		for (int counter=0;counter<= A.length();counter++){
			for (int innerCounter=0;innerCounter<= B.length();innerCounter++)
			{
				if(innerCounter==0 || counter==0)
				table[counter%2][innerCounter]=0;
				else if(A.charAt(counter-1)==B.charAt(innerCounter-1))
					table[1][innerCounter]=table[0][innerCounter-1]+1;
				else
					table[1][innerCounter]=table[1][innerCounter-1]>table[0][innerCounter]?table[1][innerCounter-1]:table[0][innerCounter];
			}
			for (int innerCounter=0;innerCounter<= B.length();innerCounter++)
				{table[0][innerCounter]=table[1][innerCounter];
				table[1][innerCounter]=0;}
		}
		ArrayList<Integer> result =new ArrayList<Integer>();
		for(int counter=0;counter<table[0].length;counter++)
			   result.add(table[0][counter]);
		return result;
	}
	public StringBuilder   AlgorithmC(StringBuilder A, StringBuilder B){
	    int m = A.length();
	    int n = B.length();
	    numberofRecursiveCall++;
	    if (m == 0)
	        return new StringBuilder("");
	    else if( A.length() == 1)
	    {
	    	if(B.indexOf(A.toString())!=-1)
	    		return A;
	    	else
	    		return new StringBuilder("");
	    }
	    else if( B.length() == 1)
	    {
	    	if(A.indexOf(B.toString())!=-1)
	    		return B;
	    	else
	    		return new StringBuilder("");
	    }
	    else
	    	
	    {
	        int i = m/2;
	        StringBuilder A1= new StringBuilder(A.substring(0, i));
	        StringBuilder A2= new StringBuilder(A.substring(i, A.length()));
	        StringBuilder A3= new StringBuilder(A.substring(i, A.length()));
	        StringBuilder B2 =new StringBuilder(B.toString());
	        B2.reverse();
	        A2.reverse();
	        
	        ArrayList<Integer> L1 = AlgorithmB(A1, B);
	        ArrayList<Integer> L2 = AlgorithmB(A2, B2);
	        int k = getK(L1,L2);
	        System.out.println(k);
	        
	        StringBuilder B3 =new StringBuilder(B.substring(0,k));
	        StringBuilder B4 =new StringBuilder(B.substring(k,n));
	        StringBuilder C1= AlgorithmC(A1, B3);
	        System.out.println(C1.toString());
	        StringBuilder C2 = AlgorithmC(A3, B4);
	        System.out.println(C2.toString());
	        return  new StringBuilder(C1.append(C2.toString()));
	
}
	    }
	private int getK(ArrayList<Integer> L1 ,ArrayList<Integer> L2){
		int max=0;
		int k=0;
		int s=L1.size()-1;
		for (int counter=0;counter<s;counter++)
			if (max<L1.get(counter)+L2.get(s-counter)){
				max=L1.get(counter)+L2.get(s-counter);
				k=counter;
			}
		return k;
	}

public static void main(String[] args)
{
	HirshbergAlgorithm hsb = new HirshbergAlgorithm();
	
	System.out.println(hsb.AlgorithmC(new StringBuilder("1101"), new StringBuilder("10")));
	System.out.println("Final");
}
}








