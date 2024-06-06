/**
 * A B+ tree generic node
 * Abstract class with common methods and data. Each kind of node implements this class.
 * @param <TKey> the data type of the key
 * @param <TValue> the data type of the value
 */
abstract class BPTreeNode<TKey extends Comparable<TKey>, TValue> {
	
	protected Object[] keys;
	protected int keyTally;
	protected int m;
	protected BPTreeNode<TKey, TValue> parentNode;
	protected BPTreeNode<TKey, TValue> leftSibling;
	protected BPTreeNode<TKey, TValue> rightSibling;
	protected static int level = 0;
	

	protected BPTreeNode() 
	{
		this.keyTally = 0;
		this.parentNode = null;
		this.leftSibling = null;
		this.rightSibling = null;
	}

	public int getKeyCount() 
	{
		return this.keyTally;
	}
	
	@SuppressWarnings("unchecked")
	public TKey getKey(int index) 
	{
		return (TKey)this.keys[index];
	}

	public void setKey(int index, TKey key) 
	{
		this.keys[index] = key;
	}

	public BPTreeNode<TKey, TValue> getParent() 
	{
		return this.parentNode;
	}

	public void setParent(BPTreeNode<TKey, TValue> parent) 
	{
		this.parentNode = parent;
	}	
	
	public abstract boolean isLeaf();
	
	/**
	 * Print all nodes in a subtree rooted with this node
	 */
	@SuppressWarnings("unchecked")
	public void print(BPTreeNode<TKey, TValue> node)
	{
		level++;
		if (node != null) {
			System.out.print("Level " + level + " ");
			node.printKeys();
			System.out.println();

			// If this node is not a leaf, then 
        		// print all the subtrees rooted with this node.
        		if (!node.isLeaf())
			{	BPTreeInnerNode inner = (BPTreeInnerNode<TKey, TValue>)node;
				for (int j = 0; j < (node.m); j++)
    				{
        				this.print((BPTreeNode<TKey, TValue>)inner.references[j]);
    				}
			}
		}
		level--;
	}

	/**
	 * Print all the keys in this node
	 */
	protected void printKeys()
	{
		System.out.print("[");
    		for (int i = 0; i < this.getKeyCount(); i++)
    		{
        		System.out.print(" " + this.keys[i]);
    		}
 		System.out.print("]");
	}


	////// You may not change any code above this line //////

	////// Implement the functions below this line //////
	
	
	
	/**
	 * Search a key on the B+ tree and return its associated value. If the given key 
	 * is not found, null should be returned.
	 */


	// public TValue search(TKey key) 
	// {
	// 	// Your code goes here
	// }



	/**
	 * Insert a new key and its associated value into the B+ tree. The root node of the
	 * changed tree should be returned.
	 */
	public BPTreeNode<TKey, TValue> insert(TKey key, TValue value) 
	{
			 if( this.keyTally == m)
			 {
				 System.out.print("hello");
				BPTreeInnerNode<TKey, TValue> newInnerNode = new BPTreeInnerNode<TKey, TValue>(m);// MAKE THE INNER NODE HERE
				newInnerNode.references[0] = this;// MAKE REFERENCES POINT TO PASSED IN NODE

				BPTreeLeafNode<TKey, TValue> newLeafNode=new BPTreeLeafNode<TKey, TValue>(m);

				//int transferStart= (m/2);// GIVES 

				int counter=0;
				for(int count=m/2; count<m; count++)
				{
					newLeafNode.keys[counter]=this.keys[m/2+counter];
					counter++;
				}

				
				newLeafNode.notfullInsert( key,  value);
				newInnerNode.references[1]=newLeafNode;


				 return this;
			 }
			 
			// {

			// 	
			// 	
				
			// 	
			// 	newLeafNode= (BPTreeLeafNode<TKey, TValue>) childSplit(this);

			// 	newInnerNode.references[1] = newLeafNode;// MAKE REFERENCES POINT TO PASSED IN NODE
				
			// 	notfullInsert(key, value); 

			// 	return newLeafNode;
			// }

		else 
			{
				this.notfullInsert( key, value);
				return this;
			}
			
	}



	/**
	 * Delete a key and its associated value from the B+ tree. The root node of the
	 * changed tree should be returned.
	 */


	// public BPTreeNode<TKey, TValue> delete(TKey key) 
	// {
	// 	// Your code goes here
	// }



	/**
	 * Return all associated key values on the B+ tree in ascending key order. An array
	 * of the key values should be returned.
	 */


	@SuppressWarnings("unchecked")
	public TValue[] values() 
	{
		// Your code goes here
	}


	public void notfullInsert(TKey key, TValue value)
	{
		int count=keyTally-1;

			if( this.isLeaf() )// IF IT'S A LEAF NODE
			{
					while(count >= 0 && ((TKey)this.keys[count]).compareTo((TKey)key) > 0)
					{
						this.keys[count+1]=this.keys[count];
						((BPTreeLeafNode)this).values[count+1] = ((BPTreeLeafNode)this).values[count];

						count=count-1;
					}

				this.keys[count+1] = key;// INSERT INTO KEY ARRAY
				((BPTreeLeafNode)this).values[count+1]=value;// INSERT INTO VALUE ARRAY

				keyTally++;// UPDATE KEYTALLY
			}

			else
			{
				System.out.print("Hello 2");
			}

}



// BPTreeNode<TKey, TValue>  childSplit(BPTreeNode<TKey, TValue> passedInNode  )
// {
	
// 	BPTreeLeafNode<TKey, TValue> newLeafNode = new BPTreeLeafNode<TKey, TValue>(m);// CREATE A NEW LEAF NODE

// 	

// 	for(int count =0; count < m-1; count++)
// 	{
// 		newLeafNode.keys[count]=passedInNode.keys[count];
// 	}

// 	return newLeafNode;

	//newInnerNode.references[1] = newLeafNode;// MAKE REFERENCES POINT TO NEW LEAF NODE

	// if( node.leaf != true )
	// { 
	// 	for( int count2=0; count2 < m; count2++ )
	// 	{
	// 		newNode.references[count2] = node.references[count2+m];
	// 		node.references[count2 + m]=null;

	// 	}
		
	// }

	// node.keyTally = m - 1; 

	// 	for( int count3=keyTally; count3 >= index+1; count3--  )
	// 	{
	// 		references[count3+1] = references[count3]; 
	// 	}

	// references[index+1] = newNode; 

	
	// 	for( int count4=keyTally-1; count4 >= index; count4-- )
	// 	{
	// 		keys[count4+1] = keys[count4]; 
	// 	}

	// keys[index] = node.keys[m-1]; 

	// keyTally++; 

//}




}