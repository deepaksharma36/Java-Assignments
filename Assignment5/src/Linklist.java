
public class Linklist {
  
	private node Start;
	public Linklist()
	{
		Start=null;
		//Start.next=null;
	}
	
	
	
	public void addNode(int newValue)
	{  
	}
	public void printLinklist()
	{
		node i ;
		   i=Start;
			   //System.out.println(Start.value);
			   //System.out.println(Start.next.value);
			   //System.out.println(Start.next.next.value);
			   while(i!=null)
			   {
				   System.out.println(i.value);
				   i=i.next;
			   }
		
	}
	
	
	
}


