
import java.lang.reflect.Array;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class exam<E> extends AbstractSet<E> {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 
		Set<String> ts = new TreeSet<String>();
	      ts.add("C");
	      ts.add("A");
	      ts.add("B");
	      ts.add("E");
	      ts.add("F");
	      ts.add("D");
	      System.out.println(ts);
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
