/**
 * Processing data using Doubly Linked List CST8130 Data Structures,
 * Computer Engineering Technology-Computer Science: Level 3
 * 
 * Student Name: Vasukumar Patel
 * Student ID: 041153940
 * Program: Computer Engineering Technology - Computing Science
 * Course: Data Structure
 * Lab Section: 301
 * Professor: James Mwangi PhD.
 */

/**
 * Node class that has data and previous and next node location
 * @param <T> - generic type
 */
class Node<T> {
	
	/*
	 * generic data type and next and previous nodes
	 */
    public T mData; 
    public Node<T> next; 
    public Node<T> previous; 
 
    /**
     * constructor 
     * @param d - generic type parameter 
     */
    public Node(T d) { mData = d; }

    /**
     * method to print the data
     */
    public void displayNode() { System.out.print(mData + " "); }
    
} 

/**
 * Doubly linked list that insert, delete, and has first and last node reference
 * @param <T> - generic class - T
 */
class DoublyLinkedList<T> {
    
	/**
	 * node references
	 */
	private Node<T> first;
    private Node<T> last;

    /**
     * constructor
     */
    public DoublyLinkedList() {
        first = null;
        last = null;
    }

    /**
     * check if list has no nodes
     * @return - boolean value
     */
    public boolean isEmpty() {
        return first == null;
    }

  /**
   * insert new node at the front of the list
   * @param num - generic parameter
   */
    public void insertFirst(T num) {
       
    	/*
    	 * create node with data, and point newNode to old first
    	 * no previous for new first
    	 */
    	Node<T> newNode = new Node<>(num);
    	newNode.next = first;
    	newNode.previous = null;
    	
    	/*
    	 * if list not empty, link old first back to newNode
    	 *  if list was empty, newNode is also last
    	 */
    	if(first != null) {
    		first.previous = newNode;
    	} else {
    		last = newNode;
    	}
    	
    	/* update first to newNode */
    	first = newNode;
    	
	}

    /** 
     * insert new node at the end of the list
     * @param num - generic type 
     */
    public void insertLast(T num) {
       
    	/*  create node with data, and no next for new last */
    	Node<T> newNode = new Node<>(num);
		newNode.next = null;
		
		/*  if list empty, no previous either 
		 *  if newNode is first,  newNode is last
		 *  */
		if( isEmpty() ) {
			
			newNode.previous = null;
			first = newNode;
			last = newNode;
			
		} else {
			
			/*
			 * link old last forward to newNode
			 *  link newNode back to old last
			 *   update last to newNode
			 */
			last.next = newNode;
			newNode.previous = last;
			last = newNode;
			
		}
		
	}

    /**
     * insert newNumber after first occurrence of numToFind
     * @param numToFind - generic parameter
     * @param newNumber - generic parameter
     * @return - returns boolean value
     */
	public boolean insertAfter(T numToFind, T newNumber) {
		
		Node<T> current = first;
		
		/* find the node with matching data */
		while(current != null && current.mData != numToFind) {
			current = current.next;
		}
		
		/* not found */
		if(current == null) {
			return false; /* return false */
		}
		
		/* create the new node, link newNode forward, link newNode back, link current forward to newNode */
		Node<T> newNode = new Node<>(newNumber);
		newNode.next = current.next;
		newNode.previous = current;
		current.next = newNode;
		
		/**
		 * if not inserting at end, and
		 * link next node back to newNode, and
		 * update last if at end
		 */
		if(newNode.next != null) {
			newNode.next.previous = newNode;
		} else {
			last = newNode;
		}
		
		return true; /* return true */

	}

	/**
	 * remove and return the first node
	 * @return - return node
	 */
    public Node<T> deleteFirstNode() {
        
    	/* empty list */
    	if(first == null) {
    		return null; /* return null */
    	}
    	
    	/* hold the node to remove */
    	Node<T> removedNode = first;
    	
    	/*  only one node, list becomes empty,  move first forward,  clear back link   */
    	if (first.next == null) {
    		first = null;
    		last = null;
    	} else {
    		first = first.next;
    		first.previous = null;
    	}
    	
    	return removedNode; /* return removed node */

	}

    /**
     * remove and return the last node
     * @return - return node 
     */
    public Node<T> deleteLastNode() {
      
    	/* empty list */
    	if(last == null) {
    		return null; /* return null */
    	}
    	
    	/* hold the node to remove */
    	Node<T> removedNode = last;
    	
    	/*  only one node, list becomes empty,  move last backward,  clear forward link   */
    	if(last.previous == null) {
    		first = null;
    		last = null;
    	} else {
    		last = last.previous;
    		last.next = null;
    	}
    	
    	
    	return removedNode; /* return removed node */

	}

   /**
    * delete the node whose data equals index
    * @param index - generic type parameter 
    * @return - return the node generic type
    */
    public Node<T> deleteSpecificNode(T index) {
	 
    
    	if(first == null) {
    		return null; /* return null */
    	}
    	
    	Node<T> current = first;
    	
    	/* iterate until we return or exhaust the list */
    	while(current == first) {
    		
    		if (current.mData == index) {
    		
    		if(current == first) {
    			
    			first = current.next;
    			
    				if(first != null) {
    					first.previous = null;
    				} else {
    					last = null;
    				} 
    		  
    		} else if (current == last) {
    			last = current.previous;
    			last.next = null;
    		} else {
    			current.previous.next = current.next;
    			current.next.previous = current.previous;
    		}
    		
    		return current; /* return deleted node */
    		
    	}
    		
    		current = current.next;
    		
    	}
    	
    	 return null;  /* return null */
	}


   /**
    * print all nodes from first to last with their index
    */
    public void printForwardsWithIndex() {
        
    	Node<T> current = first;
    	int index = 0;
    	
    	while (current != null) {
    		
    		System.out.println("Node[" + index + "]: " + current.mData);
    		current = current.next;
    		index++;
    		
    	}
 
    }

  /**
   * print all nodes from last to first with their index
   */
    public void printBackwardsWithIndex() {
       
    	int count = 0;
    	
    	for (Node<T> temp = first; temp != null; temp = temp.next) {
    		count++; /*  count total nodes */
    	}

    	
    	for(Node<T> current = last; current != null; current = current.previous) {
    		
    		System.out.println("Node[" + (count-1) + "]: " + current.mData);
    		count--;
    	}
    	
    	
     }
    
}



