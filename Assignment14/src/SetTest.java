import java.util.Iterator;

public class SetTest {

	@SuppressWarnings("unchecked")
	public static void main(String args[]) {
		HashSetNew set = new HashSetNew();
		set.add(new String("Hello1"));
		set.add(new String("Hello2"));
		set.add(new String("Hello3"));
		set.add(new String("Hello4"));
		String str5 = new String("Hello5");
		set.add(str5);

		System.out.println("Normal Iteration testing :");
		Iterator<Object> itr = set.iterator();
		System.out.println(itr.next());
		System.out.println(itr.next());
		System.out.println(itr.next());
		System.out.println(itr.next());
		System.out.println(itr.next());

		System.out.println("\nRemoving Hello5");
		set.remove(str5);
		set.size();

		System.out.println("\nWhile loop testing :");
		Iterator<Object> itr2 = set.iterator();
		while (itr2.hasNext())
			System.out.println(itr2.next());

		System.out.println("\nCreating new set with duplicate entries :");
		HashSetNew set2 = new HashSetNew();
		set2.add("Hello");
		set2.add("Hello");
		set2.add("World");
		set2.add("World");
		String exclaim = new String("!!!");
		set2.add(exclaim);
		System.out.println("Size should be (3) : " + set2.size());
		Iterator<Object> itr3 = set2.iterator();
		while (itr3.hasNext())
			System.out.print(itr3.next() + " ");

		System.out.println("\n\nIterator removal test: ");
		Iterator<Object> itr4 = set2.iterator();
		while (itr4.hasNext())
			if (itr4.next() == exclaim)
				itr4.remove();

		Iterator<Object> itr5 = set2.iterator();
		while (itr5.hasNext())
			System.out.print(itr5.next() + " ");

		System.out.println("\n\nContains test :");
		System.out
				.println("Set contain - Hello? (Y) " + set2.contains("Hello"));
		System.out.println("Set contain - Goodbye? (N) "
				+ set2.contains("Goodbye") + "\nSize : " + set2.size());
		System.out.println("Adding Goodbye now.");
		set2.add("Goodbye");
		System.out.println("Set contain - Goodbye? (Y) "
				+ set2.contains("Goodbye") + "\nSize : " + set2.size());

		
		System.out
				.println("\n\nRemoving all elements through iterator & isEmpty test :");
		System.out.println("Set is empty? (N) " + set2.isEmpty());
		Iterator<Object> itr6 = set2.iterator();
		while (itr6.hasNext()) {
			System.out.println("Removing :" + itr6.next());
			itr6.remove();
		}
		System.out.println("Set is empty? (Y) " + set2.isEmpty());

		System.out.println("\n\nTesting failure for modification while in iterator : ");
		set2.add("1");
		set2.add("2");
		set2.add("3");
		set2.add("4");
		set2.add("5");
		int size = set2.size();
		int count = 0;
		Iterator<Object> itr7 = set2.iterator();
		while (itr7.hasNext()) {
			System.out.print(itr7.next()+" ");
			if (count++ == size-2)
				set2.remove("5");
		}
		
	}

}
