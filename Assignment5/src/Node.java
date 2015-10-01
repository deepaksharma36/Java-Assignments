public class Node <E,V>
	{
	private E dataOne;
	private V dataTwo;
	private Node <E,V> next;
	
	Node(E obj, V elm)
	{
		this.setDataOne(obj);
		this.setDataTwo(elm);
		this.next=null;
	}
		public Node<E,V> getNext()
		{
			return this.next;
		}
		public void setNext(Node<E,V> next)
		{
			this.next=next;
		}
		public E getDataOne() {
			return dataOne;
		}
		public void setDataOne(E dataOne) {
			this.dataOne = dataOne;
		}
		public V getDataTwo() {
			return dataTwo;
		}
		public void setDataTwo(V dataTwo) {
			this.dataTwo = dataTwo;
		}
		
		
	}