
public class Subsets {

public static void main(String[] args)
{   long startTime=System.currentTimeMillis(); 
	int numberOfElements=Integer.parseInt(args[0]);
	int numberOfSubsets=(int)Math.pow(2, numberOfElements);
	int indexCounterI=0, indexCounterJ=0, indexCounterK=0;
	String subSets[] = new String[numberOfSubsets];
	subSets[0]="";
	for (indexCounterI=1;indexCounterI<=numberOfElements;indexCounterI++)
	{   indexCounterK=0;
	  	for(indexCounterJ=0;indexCounterJ<(int)Math.pow(2,(indexCounterI-1)) ;indexCounterJ++)
	  	{
	  		subSets[(int)Math.pow(2,(indexCounterI-1))+indexCounterK]=
	  				subSets[indexCounterJ].concat(Integer.toString((indexCounterI)));
	  		
	  		
	  		indexCounterK=indexCounterK+1;
	  	}
	}
	long endTime=System.currentTimeMillis();
	int i=0;
	System.out.println(endTime-startTime);
/*	for(i=0;i<subSets.length;i++)
	{
		System.out.println(subSets[i]);
	}*/
  	 
}
	
}
