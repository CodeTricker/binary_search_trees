public class List<T> 
{

	private ListNode<T> firstNode;
	private ListNode<T> lastNode;
	
	private String name;
	
	
	
	//////////////////////
	
	
	
	
	
	
	
	
	
	
	public T getFirstNodeData() throws EmptyListException
	{
		if (isEmpty())
			throw new EmptyListException( name );
		
		return firstNode.getData();
	}
	
	
	
	
	
	
	
	
	
	//////////////////////
	
	
	
	
	
	public List()
	{
		this( "list" );
	}
	
	public List( String listname )
	{
		name = listname;
		firstNode = lastNode = null;
		
	}
	
	public void insertAtFront( T insertItem )
	{
		
		if (isEmpty())
			firstNode=lastNode= new ListNode<T>(insertItem);
		else
			firstNode = new ListNode<T>( insertItem, firstNode );
		
		
	}
	
	public void insertAtBack( T insertItem )
	{
		ListNode<T> node = new ListNode<T>( insertItem );
		if ( isEmpty() )
			firstNode=lastNode=node;   
		else
		{
			lastNode.nextNode = node;
			lastNode = node;
		}
		
	}
	
	
	public T removeFromFront() throws EmptyListException
	{
		
	
		
		if (isEmpty())
			throw new EmptyListException( name );
		
		T removedItem = firstNode.getData();
		
		if (firstNode==lastNode)
			firstNode = lastNode = null;
		else
			firstNode = firstNode.nextNode;
			
		return removedItem; 
		
		
	}
	
	
	
	
	
	public T removeFromBack() throws EmptyListException
	{
		
		if (isEmpty())
			throw new EmptyListException(name);
		
		T removedItem = lastNode.getData();
		
		if (firstNode==lastNode)
			firstNode = lastNode = null;
		else
		{
			ListNode<T> current = firstNode;
			
			while(current.nextNode != lastNode)
			{
				current = current.nextNode;
				
			}
			
			current.nextNode = null;
			lastNode = current;
						
		}
		
		return removedItem;
		
		
		
		
		
	}
	
	
	
	
	
	public boolean isEmpty()
	{
		
		return firstNode == null;
		
	}
	
	
	public void print()
	{
		
		if (isEmpty())
		{
			System.out.printf( "The %s is Empty", name );
			return;
		}
		
		System.out.printf( "The %s is: ", name );
		
		ListNode<T> current = firstNode;
		
		while (current != null)
		{
			System.out.printf( "%s", current.getData() );
			current=current.nextNode;
		}
		
		System.out.println("\n");
		
		
	}
	
	
	
	
}
