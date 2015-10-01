
public class DoubleNode<E> {

	private E dataOne;
	
	private DoubleNode <E> left;
	private DoubleNode <E> right;
	public int leftChild=0;
	public int rightChild=0;
	
	DoubleNode(E obj)
	{
		this.setDataOne(obj);
		
		this.right=null;
		this.left=null;
	}
		public DoubleNode<E> getLeft()
		{
			return this.left;
		}
		public DoubleNode<E> getRight()
		{
			return this.right;
		}
		public void setLeft(DoubleNode<E> left)
		{
			this.left=left;
			
		}
		
		public void setRight(DoubleNode<E> right)
		{
			this.right=right;
			
		}
		public E getDataOne() {
			return dataOne;
		}
		public void setDataOne(E dataOne) {
			this.dataOne = dataOne;
		}	
	
}
