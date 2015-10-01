public class StorageTest {
    public static void main(String args[])	{
	Storage<String, String> aStorageString = new StorageFixed<String, String>();
	Storage<Integer, String> aStorageInteger = new StorageFixed<Integer, String>();
	aStorageInteger.add(3);
	aStorageInteger.add(4);
	aStorageInteger.add(5);
    
    
	System.out.println(aStorageInteger.firstElement());
	System.out.println(aStorageInteger.get(1));	
	System.out.println(aStorageInteger.lastElement());
	aStorageInteger.add(1, 10);
	System.out.println(aStorageInteger.firstElement());
	System.out.println(aStorageInteger.get(1));
	System.out.println(aStorageInteger.get(2));
	System.out.println(aStorageInteger.lastElement());
		
}
}