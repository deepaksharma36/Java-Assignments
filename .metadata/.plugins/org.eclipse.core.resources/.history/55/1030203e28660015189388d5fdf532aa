
public class StorageFixed<E, V> implements Storage<E, V>{
    private Node<E,V> Start;
    private Node<E,V> End;
    private int size=0;
    private final int storageSize=10000;
	StorageFixed()
	{
		this.Start=null;
	}

	public boolean add(E e) {
		if(size<storageSize)
		{
		Node<E,V> copyRef =this.Start;
		   Node <E,V> newNode= new Node<E,V>(e,null);
		   copyRef=Start;
		   if(Start!=null)
		   {
			while(copyRef.getNext()!=null)
			{
				copyRef=copyRef.getNext();
			}
			if(copyRef.getNext()==null)
			{
			copyRef.setNext(newNode);
			End=copyRef.getNext();
			size++;
			   System.out.println("new nodes aaaa added");
			  
			}
		   }
		   else if(Start==null)
		   {   System.out.println("First node bbb added");
		       size++;
			   Start=newNode;
			   End=Start;
		   }
		   return true;
		}
		else
		{
		return false;
		}
	}

	@Override
	public void add(int index, E element) {

		Node<E, V> temp,temp1;
		temp=Start;
		if(index<size)
		{
		while(index>1 && temp.getNext()!=null)
		{
			temp=temp.getNext();
			index--;
		}
	    Node<E, V> newNode = new  Node<E, V>(element,null);
	    temp1=temp.getNext();
	    temp.setNext(newNode);
	    newNode.setNext(temp1);
		}
    
		
	}

	@Override
	public void addElement(E obj) {
		   Node<E,V> copyRef =this.Start;
		   Node <E,V> newNode= new Node<E,V>(obj,null);
		   copyRef=Start;
		   if(Start!=null)
		   {
			while(copyRef.getNext()!=null)
			{
				copyRef=copyRef.getNext();
			}
			if(copyRef.getNext()==null)
			{
			copyRef.setNext(newNode);
			copyRef=copyRef.getNext();
			End=copyRef;
			size++;
			   System.out.println("new nodes added from here");
			}
		   }
		   else if(Start==null)
		   {   System.out.println("First node added");
		       size++;
			   Start=newNode;
			   End=Start;
		   }
		
	}

	public void addElement(E obj, V elem) {
		   Node<E,V> copyRef =this.Start;
		   Node <E,V> newNode= new Node<E,V>(obj,elem);
		   copyRef=Start;
		   if(Start!=null)
		   {
			while(copyRef.getNext()!=null)
			{
				copyRef=copyRef.getNext();
			}
			if(copyRef.getNext()==null)
			{
				size++;
			copyRef.setNext(newNode);
			End=copyRef.getNext();
			   System.out.println("new nodes added >>>>");
			}
		   }
		   else if(Start==null)
			   
		   {   size++;
			   System.out.println("First node added");
			   Start=newNode;
			   End=Start;
		   }
		
	}

	public int capacity() {
		// TODO Auto-generated method stub
		return size;
	}


	public void clear() {
        Node<E, V> temp;
        temp=Start;
		while(Start.getNext()!=null)
        {temp=Start;
		Start=Start.getNext();
		temp=null;
        }	    
		Start=null;
		
	}

	@Override
	public E firstElement() {
		// TODO Auto-generated method stub
		return Start.getDataOne();
	}

	@Override
	public E get(int index) {
		Node<E, V> temp;
		temp=Start;
		while(index>0)
		{
			temp=temp.getNext();
			index--;
		}
		return temp.getDataOne();
	}

	@Override
	public E lastElement() {
		// TODO Auto-generated method stub
		return End.getDataOne();
	}
	public Object	clone()
	{  
		Node<E, V> temp=Start;
		Node<E, V> StartCopy;
		
		Node<E, V> copy = new Node<E,V>(Start.getDataOne(), Start.getDataTwo());
		StartCopy=copy;
		while(temp.getNext()!=null)
		{
			temp=temp.getNext();
			Node<E, V> newNode = new Node<E,V>(temp.getDataOne(), temp.getDataTwo());
			copy.setNext(newNode);
			copy=copy.getNext();
			
		}
		return (Object)StartCopy;
	}

}
