import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

 class X{
		public String name;
		public String Fname;
	 X(String name, String Fname)
	 {
		 this.Fname=Fname;
		 this.name=name;
	 }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Fname == null) ? 0 : Fname.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		X other = (X) obj;
		if (Fname == null) {
			if (other.Fname != null)
				return false;
		} else if (!Fname.equals(other.Fname))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}

public class basics {

	public void basicMethod()
	{

		X aX =new X("beta","bap");
		X bX =new X("son","pa");
		X cX =new X("beta","bap");		
		
		Map<X,String> myMap= new LinkedHashMap<X,String>();
		myMap.put(aX, "1");
		myMap.put(bX, "2");
		myMap.put(cX, "3");
		for(X a : myMap.keySet())
			System.out.println(a.name +"   "+ a.Fname);
		Collection aCollection = new HashSet();
		Collection bCollection = new HashSet();
		aCollection.add("hello");
		bCollection.add("adele");
		Set aSet = new HashSet();
		aSet.add(aCollection);
		aSet.add(bCollection);
		
		Iterator myIterator =aSet.iterator(); 
		while(myIterator.hasNext())
		{
			System.out.println(myIterator.next());
		}
		
		Collection cCollection = new HashSet();
		cCollection.addAll(aCollection);
		cCollection.addAll(bCollection);
		
		List b = new ArrayList<String>();
		
		
		Iterator myIterator2 =cCollection.iterator(); 
		while(myIterator2.hasNext())
		{
			System.out.println(myIterator2.next());
		}
		

		
		
	}
	
	public static void main(String[] args){
	String deepak="deepak",sri="deepax";
	System.out.println(deepak.compareTo(sri)); 
	basics b = new basics();
	b.basicMethod();
}
}