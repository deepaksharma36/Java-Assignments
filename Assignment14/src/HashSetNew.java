import java.util.*;

@SuppressWarnings({ "serial", "rawtypes" })
public class HashSetNew extends HashSet {
	
	MyHashMap< Object , Object > hashMap= new MyHashMap<Object,Object>(20000);
	public boolean add(Object obj)
	{
		return hashMap.put(obj, 1);
	}
	public boolean remove(Object obj)
	{
		return hashMap.remove(obj);
	}

	public int size()
	{
		//System.out.println(hashMap.getHashMapSize());
		return hashMap.getHashMapSize();
	}
	
	public boolean isEmpty()
	{
		return hashMap.getHashMapSize()==0 ? true : false;
	}
	public boolean contains(Object obj)
	{
		return hashMap.contains(obj);
	}
	public Iterator iterator()
	{
		return hashMap.iterator();
	}
	public void clear()
	{
	
		hashMap.clear();
		
	}
}
