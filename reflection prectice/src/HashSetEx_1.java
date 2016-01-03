
import java.util.HashSet;
import java.util.Set;

public class HashSetEx_1 {
	int i;
    public  static String check(Integer i)
    {
    	return String.valueOf(i);
    }
    private Set<Integer> universe;
    
    private Set<Integer> fill(int soMany) {
	Set<Integer> universe = new HashSet<Integer>();
	for ( int index = 0; index < soMany; index ++ )
		universe.add(new Integer(9999999 * index));
	return universe;
    }
    public static void main(String args[])	{
    	String name ="icandothis!";
    	name=name.replace("can", "cannot");
    	System.out.println(name);
    	Set<Integer> universe = null;
	HashSetEx_1 aHashSetEx_1 = new HashSetEx_1();
	universe = aHashSetEx_1.fill(253);
	System.out.println("1: " + universe );
   check(i);
	// universe.remove( new Integer(1) );
	// System.out.println("2: " + universe );

	universe.remove( new Integer(10) );
	// System.out.println("3: " + universe );
    }
}

       /* 
        for(Integer id : stars.keySet()) {
            ids.add(id);
            
            if(ids.size() >= keepStars)
                break;
        }
        
        return ids;
	*/
