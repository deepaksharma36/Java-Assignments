
class MyQueue {

	private Node head =null;
	private Node tail =null;
	private int size;
	public int getSize() {
		return size;
	}
	public void push(byte[] data)
	{
		Node newNode = new Node(data);
		if (head==null)
			{head=newNode;
			tail=newNode;}
		else
		{
			tail.setNext(newNode);
			tail=newNode;
		}
		size++;
		
	}
	public byte[] pop() throws Exception
	{   if(size>0)
	{
		byte[] pop;
		Node tempHead;
		pop= head.getBlock();
		tempHead=this.head;
		this.head=this.head.getNext();
		tempHead.setNext(null);
		size--;
		return pop;
		
	}
	else
	{
		
			throw new Exception("poping from Empty queue");
	}
	}


}

 class Node{
	
	private byte[] block = new byte[1024];
	public byte[] getBlock() {
		return block;
	}
	private Node next; 
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	Node(byte[] block)
	{
		this.block=block;
		this.next=null;
	}
	
}
