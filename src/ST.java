import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.StringTokenizer;


public class ST 
{
	
	/////////////////////////////////////////////////
	/////////////////////////////////////////////////
	/////////////////////////////////////////////////
	/////////////START OF CLASS TreeNode/////////////
	private class TreeNode
	{
		
		Suspect item;
		TreeNode l; // pointer to left subtree
		TreeNode r; // pointer to right subtree
		TreeNode parent; //pointer to the parent of the node
		int N; //number of nodes in the subtree rooted at this TreeNode
		
		public TreeNode(Suspect item)
		{
			if (item == null) throw new IllegalArgumentException();
			this.item = item;
			l = null;
			r = null;
			N = 0;
		}
		
		//we unlink the node
		 protected void unlink() 
		 {
			 item = null;
	         parent = l = r = null;
	     }
		
		
	}
	/////////////END OF CLASS TreeNode///////////////
	/////////////////////////////////////////////////
	/////////////////////////////////////////////////
	/////////////////////////////////////////////////
	
	////////////////////////////////////////
	//our ST variables
	private TreeNode head;
	
	protected int size;
	
	 protected IntegerComparator cmp = new IntegerComparator();
	
	
	public ST()
	{
		this.size = 0;
	}
	
	
	/////////////////////////////////////////////////
	/////////////////////////////////////////////////
	/////////////////////////////////////////////////
	/////////////////////////////////////////////////
	
	//the recursive method used by  the method searchByAFM()
	private Suspect searchByAFMRecursive(TreeNode comparedNode, int AFM)
	{
		if(comparedNode==null)
			return null;
		int result = cmp.compare(AFM,comparedNode.item.key());
		//we return the item of the node
		if (result == 0)
            return comparedNode.item;
		if (result<0)
			return searchByAFMRecursive(comparedNode.l, AFM);
		else
			return searchByAFMRecursive(comparedNode.r, AFM);
			
		
	}
	//we search a suspect by his AFM and we return the suspect
	public Suspect searchByAFM(int AFM)
	{
		return searchByAFMRecursive(head, AFM);
	}
	
	/////////////////////////////////////////////////
	/////////////////////////////////////////////////
	//the recursive method used by  the 2 methods treeNodeSearchByAFM()
	//the difference with the previous recursive method
	//is that now we return the node and not the item of the node
	private TreeNode treeNodeSearchByAFMRecursive(TreeNode comparedNode, int AFM, boolean removeCalled)
	{
		if(comparedNode==null)
			return null;
		int result = cmp.compare(AFM,comparedNode.item.key());
		//if the method remove() called update the field N
		if (removeCalled)
			comparedNode.N = countOfSubtree(comparedNode);
		//we return the node
		if (result == 0)
            return comparedNode;
		if (result<0)
			return treeNodeSearchByAFMRecursive(comparedNode.l, AFM, removeCalled);
		else
			return treeNodeSearchByAFMRecursive(comparedNode.r, AFM, removeCalled);
		
	}
	
	//this method is used by the method remove()
	//we search a suspect by his AFM and we return the node that contains this suspect
	public TreeNode treeNodeSearchByAFM(int AFM)
	{
		return treeNodeSearchByAFMRecursive(head, AFM, false);
	}
	//this method is used by the method remove()
	//we search a suspect by his AFM so that we can update the field N of the nodes that
	//contained in their subtree the node to be removed
	private void treeNodeSearchByAFM(int AFM, boolean theNodeWillBeRemoved)
	{
		treeNodeSearchByAFMRecursive(head, AFM, theNodeWillBeRemoved);
	}
	
	/////////////////////////////////////////////////
	/////////////////////////////////////////////////
	
	//the recursive method used by  the method searchByLastNAme()
	private void searchByLastNameRecursive(List<Suspect> list, TreeNode comparedNode, String last_name)
	{

		if(comparedNode==null)
			return;
		
		if (last_name.equals(comparedNode.item.getLast_name()))
			list.insertAtBack(comparedNode.item);

		
		
		searchByLastNameRecursive(list, comparedNode.l, last_name);
		
		searchByLastNameRecursive(list, comparedNode.r, last_name);
		

	}
	
	
	//we search a suspect by his Lastname and we return a list with the suspects
	//that have this lastname
	public List<Suspect> searchByLastname(String last_name)
	{
		if(last_name == null)
			return null;
		List<Suspect> list = new List<Suspect> ("Suspect list");

		
		searchByLastNameRecursive(list, head, last_name);
		
		return list;
	}
	
	/////////////////////////////////////////////////
	/////////////////////////////////////////////////
	
	//we insert a new node with  the object item, as leaf
	public void insert(Suspect item)
	{
		if (item == null) throw new IllegalArgumentException();
		//we check if the suspect already exist
		if (searchByAFM(item.key()) != null)
			return;
		
		 TreeNode comparedNode = head;
	     TreeNode lastNode = null;
	     int result = 0;
	     while (comparedNode != null) 
	     {
	    	 result = cmp.compare(item.key(), comparedNode.item.key());
	    	 //we update(increase) the field N of the comparedNode,
	    	 //the increment takes place for the nodes that are compared with the inserting node
	    	 ++comparedNode.N;
	    	 
	    	 lastNode = comparedNode;
	    	 comparedNode = result < 0 ? comparedNode.l : comparedNode.r;
	    	
	     }
	     
	     TreeNode node = new TreeNode(item);
	     node.N = 1;
	     node.parent = lastNode;
	    
	        // The new node must be a left child of lastNode
	        if (result < 0) 
	        {
	            lastNode.l = node;
	        }
	        // The new node must be a right child of lastNode
	        else if (result > 0) 
	        {
	            lastNode.r = node;
	        }
	        // The tree is empty; root must be set
	        else 
	        {
	            head = node;
	          //we update the field N of the root of the tree,
	            head.N = 1;
	        }
	        ++size;
	     
		
	}
	
	/////////////////////////////////////////////////
	/////////////////////////////////////////////////
	
	//this method is used by the method topTreeCreatorRecursive()
	//we insert a new node with  the object item, as leaf, according now to his black money
	//black money is the difference savings - taxed income
	public void insertTopSuspect(Suspect item)
	{
		if (item == null) throw new IllegalArgumentException();
		//we create a double comparator to compare the black money
		DoubleComparator doubleCmp = new DoubleComparator();
		if (searchByAFM(item.key()) != null)
			return;
		
		 TreeNode comparedNode = head;
	     TreeNode lastNode = null;
	     int result = 0;
	     double itemBlackMoney = item.getSavings() - item.getTaxed_income();
	     double comparedNodeBlackMoney =0;
	     if (comparedNode != null)
	    	comparedNodeBlackMoney = comparedNode.item.getSavings() - comparedNode.item.getTaxed_income();
	     
	     while (comparedNode != null) 
	     {
	     result = doubleCmp.compare(itemBlackMoney, comparedNodeBlackMoney);
	     
	     ++comparedNode.N;
		 lastNode = comparedNode;
		 
		 comparedNode = result <= 0 ? comparedNode.l : comparedNode.r;
		 if (comparedNode != null)
			 comparedNodeBlackMoney = comparedNode.item.getSavings() - comparedNode.item.getTaxed_income();
	     }
	     
	     TreeNode node = new TreeNode(item);
	     node.N = 1;
	     node.parent = lastNode;
	     
	     if (size == 0)
	     {
	    	 head = node;
	         //we update the field N of the root of the tree,
	         head.N = 1;
	     }
	     // The new node must be a left child of lastNode
	     else if (result <= 0) 
	     {
	        lastNode.l = node;
	     }
	     // The new node must be a right child of lastNode
	     else if (result > 0) 
	     {
	        lastNode.r = node;
	     }
	     
	     
	     
	     ++size;
	     
	     
	}
	
	
	/////////////////////////////////////////////////
	/////////////////////////////////////////////////
	
	//this recursive method is used by the methods countOfST() and countOfSubtree()
	private int countRecursive(TreeNode rootOfSubtree) 
	{
		if (rootOfSubtree == null) return 0;
		return 1 + countRecursive(rootOfSubtree.l) + countRecursive(rootOfSubtree.r); 
	}
	
	//we count the number of nodes of the whole tree
	public int countOfST() 
	{ 
		return countRecursive(head); 
	}
	//we count the number of nodes of a subtree
	private int countOfSubtree(TreeNode rootOfSubtree) 
	{
		return countRecursive(rootOfSubtree);
	}
	
	
	/////////////////////////////////////////////////
	/////////////////////////////////////////////////
	
	//this recursive method is used by the method insert_at_root
	private TreeNode insert_at_rootRecursive(TreeNode comparedNode, Suspect item, TreeNode parent)
	{
		if (comparedNode == null)
		{
			TreeNode node = new TreeNode(item);
            node.parent = parent;
            ++size;
            return node;
        }
		int result = cmp.compare(item.key(), comparedNode.item.key());
		
		if (result<0)
		{
			comparedNode.l = insert_at_rootRecursive(comparedNode.l, item, comparedNode);
			comparedNode = rotateRight(comparedNode);
        }
		else 
		{
			comparedNode.r = insert_at_rootRecursive(comparedNode.r, item, comparedNode);
			comparedNode = rotateLeft(comparedNode);
			
        }
		
		
		
		
		return comparedNode;
	}
	
	//we insert a new node with  the object item, as root of the tree using rotates
	public void insert_at_root(Suspect item)
	{
		if (item == null) throw new IllegalArgumentException();
		if (searchByAFM(item.key()) != null)
			return;
        head = insert_at_rootRecursive(head, item, null);
      //we update the field N of the new node which now is the root of the tree
        head.N = countOfST();
	}
	
	
	
	
	//we rotate left the pivot node
	private TreeNode rotateLeft(TreeNode pivot)
	{
		TreeNode parent = pivot.parent;
	    TreeNode child = pivot.r;
	     
	    if (parent == null) 
	    	head = child;
	    else if (parent.l == pivot)
	         parent.l = child;
	     else 
	         parent.r = child;	 
		 
	     
	     child.parent = pivot.parent;
	     pivot.parent = child;
	     pivot.r = child.l;
	     if (child.l != null) child.l.parent = pivot;
	     child.l = pivot;
	     //we update the field N
	     pivot.N = countOfSubtree(pivot);
	     child.N = countOfSubtree(child);
	     
	     
	     return child;
	     
	 }
	
	//we rotate right the pivot node
	private TreeNode rotateRight(TreeNode pivot) 
	{
		TreeNode parent = pivot.parent;
		TreeNode child = pivot.l;
        if (parent == null) {
            head = child;
        } else if (parent.l == pivot) {
            parent.l = child;
        } else {
            parent.r = child;
        }
        child.parent = pivot.parent;
        pivot.parent = child;
        pivot.l = child.r;
        if (child.r != null) child.r.parent = pivot;
        child.r = pivot;
        //we update the field N
        pivot.N = countOfSubtree(pivot);
	    child.N = countOfSubtree(child);
        
        return child;
    }
	
	/////////////////////////////////////////////////
	/////////////////////////////////////////////////
	
	//we remove the node given its AFM key
	public void remove(int AFM)
	{
		TreeNode nodeToRemove = treeNodeSearchByAFM(AFM);
		if (nodeToRemove == null) 
			 return;
		/////////////////////////////////////////////////
		/////////////////////////////////////////////////
		
		//in case the node to remove has 2 children
        if (nodeToRemove.l != null && nodeToRemove.r != null) 
        {
        	
        	
        	//we find the succesor
        	//and in this method we update he filed N of the nodes
        	//that we meet on our way to the succesor node
        	TreeNode succesor = succ(nodeToRemove, true);
        	//we copy the item  of the succesor node to the node to remove
        	nodeToRemove.item = succesor.item;
        	//we update the field N of the node to remove
        	--nodeToRemove.N;
        	//the node to remove is the succesor node
        	//which means we transfer to the succesor node and we work with that one        	
        	nodeToRemove = succesor;
        }
        //we store temporarily the parent of the node to remove
        TreeNode parent = nodeToRemove.parent;
        //in case the original node to remove has one child, we store it temporarily
        TreeNode child = nodeToRemove.l != null ? nodeToRemove.l : nodeToRemove.r;
        
        // The root is being removed
        if (parent == null) {
            head = child;
        }
/////////// Bypass nodeToRemove
        //we connect the parent to the child
        else if (nodeToRemove == parent.l) {
            parent.l = child;
        } else 
        {
            parent.r = child;
        }
        //if the child exists we connect it to the parent
        if (child != null) {
            child.parent = parent;
        }
        	//we update the field N of the nodes that
        	//contained in their subtree the node to be removed
        	treeNodeSearchByAFM(nodeToRemove.item.key(), true);
        //we dispose the node to remove
        nodeToRemove.unlink();
        --size;
        
	}
	
	
	
	//we finds the inorder successor of a ginen node.
	private TreeNode succ(TreeNode nodeToSucceed, boolean removeCalled) 
	{
        // The successor is the leftmost leaf of the right subtree
		//goes to the right subtree and finds the leftmost leaf
        if (nodeToSucceed.r != null) 
        {
        	TreeNode succesor = nodeToSucceed.r;
        	//we update the field N of the succesor if the method remove has calld this method
        	if (removeCalled)
        		--succesor.N;
            while (succesor.l != null) 
            {
            	succesor = succesor.l;
            	//we update the field N of the succesor if the method remove has calld this method
            	if (removeCalled)
            		--succesor.N;
            }
            return succesor;
        }
        // The successor is the nearest ancestor on the right
        //finds the parent of the node(goes up to tree) and when it turns to right it stops 
        else {
        	TreeNode succesor = nodeToSucceed.parent;
        	TreeNode childOfSuccesor = nodeToSucceed;
            while (succesor != null && childOfSuccesor == succesor.r) 
            {
            	//we update the field N of the succesor if the method remove has calld this method
            	//succesor.N = countOfSubtree(succesor);
            	childOfSuccesor = succesor;
                succesor = succesor.parent;
            }
            return succesor;
        }
    }
	
	////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////
	
	//we load an input file and we make a tree using simple inserts as leaf  
	public void  load(String filename)
	{
		////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////
		//these are our variables
		Integer AFM;
		String firstName;
		String lastName;
		Double savings;
		Double taxedIncome;
		
		
		////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////
		//we open the file and read it
		File f = null;
		BufferedReader reader = null;
		String inputLine;
		
		try
		{
		f = new File(filename);
		} 
		
		
		
		catch (NullPointerException e) 
		{
		System.err.println("File not found.");
		}
		
		try 
		{
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		} 
		
		catch (FileNotFoundException e) 
		{
		System.err.println("Error opening file.");
		}
		
		try 
		{
		
		inputLine = reader.readLine();
		while( inputLine != null && !inputLine.equals(" ") )
		{
			////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////
			
			
			
			StringTokenizer stringTokenizer = new StringTokenizer(inputLine);
			while (stringTokenizer.hasMoreElements()) 
			{
				
				
				//we store the AFM
				AFM =  Integer.parseInt(stringTokenizer.nextElement().toString());
				
				//we store the firstname
				firstName = stringTokenizer.nextElement().toString();
				
				//we store the lastname
				lastName = stringTokenizer.nextElement().toString();
				
				//we store the savings
				savings = Double.parseDouble(stringTokenizer.nextElement().toString());
				
				//we store the taxed income
				taxedIncome = Double.parseDouble(stringTokenizer.nextElement().toString());
				
				//we create a new object of suspect with the data we stored from the input file
				Suspect suspect = new Suspect(AFM, firstName, lastName, savings, taxedIncome);
				insert(suspect);
				
			
			
			}
			//end of second while
			inputLine = reader.readLine();
		}
		//end of first while
		
		
		}
		catch (IOException e) {
		System.out.println("Error reading file!");
		}
		
		try {
		reader.close();
		} 
		catch (IOException e) {
		System.err.println("Error closing file.");
		}
		
		
		
	}
	
	/////////////////////////////////////////////////
	/////////////////////////////////////////////////
	
	//this recursive method is used by the method getMeanSavings()
	private double getMeanSavingsRecursive(TreeNode currentNode)
    {
    	if(currentNode == null)
    		return 0.0;
    	
    	return currentNode.item.getSavings() + getMeanSavingsRecursive(currentNode.l) +  getMeanSavingsRecursive(currentNode.r);
    	
    }
    //we calculate the mean of savings of the suspects stored in the tree
    public double getMeanSavings()
    {
    	if(head == null)
    		return 0.0;
    	double overallSavings = getMeanSavingsRecursive(head);
    	double meanSavings = overallSavings / countOfST();
		
    	return meanSavings;
    	
    }
	
	/////////////////////////////////////////////////
	/////////////////////////////////////////////////
	
	private String toStringRecursive(TreeNode node)
	{
        if (node == null) 
        	return "";
        
        String s = toStringRecursive(node.l);
        
        s += node.item.toString() + "| ";
        
        s += toStringRecursive(node.r);

        return s;
    }
	
	//we print the items of all the nodes of the tree according to their AFM
    public void printTreeByAfm(PrintStream Stream)
    {
    	String print = toStringRecursive(head);
    	
    	System.out.println(print);
    }
    
	/////////////////////////////////////////////////
	/////////////////////////////////////////////////
    
    
	
  //this recursive method is used by the method selectMax()
	public Suspect selectMaxR(TreeNode maxNode,int k)
	{
		if(maxNode == null)
		return null;
		
		int t = (maxNode.r == null) ? 0 : maxNode.r.N;
		
		if(t > k)
		{
			return selectMaxR(maxNode.r, k);
		}
		if(t < k)
		{
			return selectMaxR(maxNode.l, k-t-1);
		}
		return maxNode.item;
	}
	
	//this method is used by the method printkMax()
	//we select the k maximum node
	public Suspect selectMax(int k)
	{
		return selectMaxR(head, k-1);
	}
	
	//we print the k max nodes
	public void printkMax(int k)
	{
		while(k>0)
		{
			System.out.println("the top suspect number " + k + " is: " + selectMax(k));
			--k;
		}
		
	}
	//this recursive method is used by the method topTreeCreator()
	private void topTreeCreatorRecursive(ST loadTopSuspectsST,TreeNode currentNode) 
	{
		if(currentNode == null)
			return;
		
		loadTopSuspectsST.insertTopSuspect(currentNode.item);

		topTreeCreatorRecursive(loadTopSuspectsST, currentNode.l);
		
		topTreeCreatorRecursive(loadTopSuspectsST, currentNode.r); 
	}
	
	//this method is used by the method loadTopSuspects()
	private void topTreeCreator(ST loadTopSuspectsST) 
	{
		topTreeCreatorRecursive(loadTopSuspectsST, head);
	}
	
	//we create a new tree from our tree,
	//according now to the black money of the suspects
	//black money is the difference savings - taxed income
	public void printTopSuspects(int k)
	{
		ST loadTopSuspectsST = new ST();
		
		topTreeCreator(loadTopSuspectsST);
		
		loadTopSuspectsST.printkMax(k);
		
		//return loadTopSuspectsST;
	}
	
	
	//////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////
	
	//this recursive method is used by the method selectMin()
	public Suspect selectMinR(TreeNode minNode,int k)
	{
		if(minNode == null)
		return null;
		
		int t = (minNode.l == null) ? 0 : minNode.l.N;
		
		if(t > k)
			return selectMinR(minNode.l, k);
		if(t < k)
			return selectMinR(minNode.r, k-t-1);
		
		return minNode.item;
	}
	
	//we dont use this method,
	//but its useful to have it in case we want to expand some functions of our program
	//we select the k minimum node
	public Suspect selectMin(int k)
	{
		return selectMinR(head, k-1);
	}
	
	public void printkMin(int k)
	{
		while(k>0)
		{
			System.out.println("the least suspect number " + k + " is: " + selectMin(k));
			--k;
		}
		
	}
	
	//////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////

}
