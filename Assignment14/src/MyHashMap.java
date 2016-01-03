import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyHashMap<T, E> {

	private final int SIZE;
	private Slot<T,E> map[];
	private int hashMapSize = 0;
	private int hashModCount=0;
	@SuppressWarnings({"unchecked"})
	public MyHashMap() {
		SIZE = 10000;


		
		map = new Slot[SIZE];
	}
	
	@SuppressWarnings("unchecked")
	public MyHashMap(int size) {
		SIZE = size;
		map= new Slot[SIZE];
	}

	public boolean put(T key, E value) {
		int hashCode = Math.abs(key.hashCode()) % SIZE;
		if (hashCode < 0)
			hashCode *= -1;
		Slot<T, E> slot = map[hashCode];
		Slot<T, E> pSlot = slot;
		if (slot != null) {
			while (slot != null) {
				if (slot.getKey().equals(key)) {
					return false;
				}
				pSlot = slot;
				slot = slot.nextSlot;
			}

			Slot<T, E> newslot = new Slot<T, E>(key, value);
			pSlot.nextSlot = newslot;
			hashMapSize++;
			hashModCount++;
			return true;
		} else {
			Slot<T, E> entryInNewBucket = new Slot<T, E>(key, value);
			map[hashCode] = entryInNewBucket;
			hashMapSize++;
			hashModCount++;
			return true;
		}
	}

	public int getHashMapSize() {
		return hashMapSize;
	}

	public void setHashMapSize(int hashMapSize) {
		this.hashMapSize = hashMapSize;
	}

	public boolean contains(T findKey) {
		int hashCode = findKey.hashCode() % SIZE;
		if (hashCode < 0)
			hashCode = hashCode * (-1);
		Slot<T, E> slot = map[hashCode];
		while (slot != null) {
			if (slot.getKey().equals(findKey)) {
				return true;
			}
			slot = slot.nextSlot;
		}
		return false;
	}
	
	public boolean remove(T key)
	{
		int hashCode = Math.abs(key.hashCode()) % SIZE;
		if (hashCode < 0)
			hashCode *= -1;
		Slot<T, E> slot = map[hashCode];
		if (slot != null) {
			Slot<T, E> pSlot = slot;
			Slot<T, E> cSlot = slot.nextSlot;
			while (slot != null) {
				if (slot.getKey().equals(key)) {
					pSlot.nextSlot = cSlot;

					if (pSlot == slot)
						map[hashCode] = pSlot.nextSlot;

					hashMapSize--;
					return true;
				}
				pSlot = slot;
				slot = slot.nextSlot;
				cSlot = slot.nextSlot;
			}

		}
		return false;
	}

	public void clear() {
		for (int i = 0; i < SIZE; i++) {
			map[i] = null;
		}
		hashMapSize = 0;
	}

	@SuppressWarnings("rawtypes")
	public Iterator iterator()
	{
		return new MyHashSetIterator();
	}

	
	@SuppressWarnings("hiding")
	class Slot<T, E> {
		private final T key;
		private E value;
		public Slot<T, E> nextSlot;
		Slot(T k, E v) {
			key = k;
			value = v;
		}
		public E getValue() {
			return value;
		}
		public void setValue(E value) {
			this.value = value;
		}
		public T getKey() {
			return key;
		}
	}

	@SuppressWarnings("rawtypes")
	class MyHashSetIterator implements Iterator {
		private int mapIndex;
		private int modCount=0;
		private Slot<T, E> currentSlot;
		private Slot<T, E> nextSlot;

		public MyHashSetIterator() {
			nextSlot = null;
			currentSlot = null;
			mapIndex = -1;
			this.modCount=hashModCount;
		}
		
		public final boolean  hasNext() {
			if (currentSlot != null && currentSlot.nextSlot != null)
				return true;
			for (int b = mapIndex + 1; b < map.length; b++) {
				if (map[b] != null)
					return true;
			}
			return false;
		}
		
		public Object next() {
			if (this.modCount!=hashModCount)
				throw new ConcurrentModificationException();
			if (currentSlot != null && currentSlot.nextSlot != null) {
				currentSlot = currentSlot.nextSlot;
				nextSlot = currentSlot.nextSlot;
			} else {
				do  {
					mapIndex++;
					if (mapIndex >= map.length) {
						throw new NoSuchElementException();
					}
					
					currentSlot = map[mapIndex];
				}while(currentSlot == null);
			}
			if(currentSlot.nextSlot!=null)
				nextSlot=currentSlot.nextSlot;
			else if(mapIndex+1<map.length)
				nextSlot=map[mapIndex+1];
			else
				nextSlot=null;
			
			return currentSlot.key;
		}

		@Override
		public void remove() {
			if(currentSlot.nextSlot==null)
				mapIndex+=1;
			if(MyHashMap.this.remove(currentSlot.key))
			{
				currentSlot=nextSlot;
				this.modCount++;
			}
		}
	}
}