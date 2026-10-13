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
 *  Node class that has data and previous and next node location
 */
class Node {
	
	/*
	 * generic data type and next and previous nodes
	 */
    public int mData; 
    public Node next; 
    public Node previous; 
 
    /**
     * constructor
     * @param d - int data type parameter 
     */
    public Node(int d) { mData = d; }

    /**
     * method to print the data
     */
    public void displayNode() { System.out.print(mData + " "); }
    
} 

/**
 * Doubly linked list that insert, delete, and has first and last node reference
 */
class DoublyLinkedList {
    
	/**
	 * node references
	 */
	private Node first;
    private Node last;

    /**
     * constructor
     */
    public DoublyLinkedList() {
        first = null;
        last = null;
    }

    /**
     *  check if list has no nodes
     * @return - boolean value
     */
    public boolean isEmpty() {
        return first == null;
    }

  /**
   * insert new node at the front of the list
   * @param num - parameter for int
   */
    public void insertFirst(int num) {
       
    	
    	Node newNode = new Node(num);
    	newNode.next = first;
    	newNode.previous = null;
    	
    	
    	if(first != null) {
    		first.previous = newNode;
    	} else {
    		last = newNode;
    	}
    	
    	
    	first = newNode;
    	
	}

    /**
     *  insert new node at the end of the list
     * @param num - int parameter 
     */
    public void insertLast(int num) {
       
    	
    	Node newNode = new Node(num);
		newNode.next = null;
		
		if( isEmpty() ) {
			
			newNode.previous = null;
			first = newNode;
			last = newNode;
			
		} else {
			
			
			last.next = newNode;
			newNode.previous = last;
			last = newNode;
			
		}
		
	}

    /**
     * insert newNumber after first occurrence of numToFind
     * @param numToFind - int parameter
     * @param newNumber - int parameter
     * @return - boolean return 
     */
	public boolean insertAfter(int numToFind, int newNumber) {
		
		Node current = first;
		
		/* find the node with matching data */
		while(current != null && current.mData != numToFind) {
			current = current.next;
		}
		
		/* not found */
		if(current == null) {
			return false; /* return false */
		}
		
		/* create the new node, link newNode forward, link newNode back, link current forward to newNode */
		Node newNode = new Node(newNumber);
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
     * @return - return node - int type 
     */
    public Node deleteFirstNode() {
        
    	/* empty list */
    	if(first == null) {
    		return null; /* return null */
    	}
    	
    	/* hold the node to remove */
    	Node removedNode = first;
    	
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
     * @return - remove and return the last node
     */
    public Node deleteLastNode() {
      
    	/* empty list */
    	if(last == null) {
    		return null; /* return null */
    	}
    	
    	/* hold the node to remove */
    	Node removedNode = last;
    	
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
    * @param index - int parameter
    * @return - return the node - int type 
    */
    public Node deleteSpecificNode(int index) {
	 
    	/* empty list */
    	if(first == null) {
    		return null; /* return null */
    	}
    	
    	Node current = first;
    	
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
    *  print all nodes from first to last with their index
    */
    public void printForwardsWithIndex() {
        
    	Node current = first;
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
    	
    	for (Node temp = first; temp != null; temp = temp.next) {
    		count++; /* count total nodes */
    	}

    	
    	for(Node current = last; current != null; current = current.previous) {
    		
    		System.out.println("Node[" + (count-1) + "]: " + current.mData);
    		count--;
    	}
    	
    	
     }
    
}



