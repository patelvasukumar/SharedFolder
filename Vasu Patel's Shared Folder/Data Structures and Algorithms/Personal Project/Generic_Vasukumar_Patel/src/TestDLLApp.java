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
 * This class has generic type objects and generic method operates on every data be they int, string or float
 * it is a linkedlist
 */
class TestDLLApp {
	
	/**
	 * parameterless constructor 
	 */
	public TestDLLApp() {}
	
	/**
	 * main() method to run the code
	 * @param args - arguments 
	 */
	public static void main(String[] args) {


		/* DoublyLinkedList which is a integer type */
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		
		/* inserting on first and last position, and printing forwards and backwards
		 * inserting on the last position, and inserting after a specific position,
		 * and printing all the elements from first to last */
		
		System.out.println("\nInteger:");
        list.insertFirst(22);
        list.insertFirst(44);
        list.insertFirst(74);
        list.insertFirst(97);

        list.insertLast(9);
        list.insertLast(33);
        list.insertLast(55);

   	    System.out.println("Linked List: [Forward with indices]:");
        list.printForwardsWithIndex();
        System.out.println();
        System.out.println("Linked List: [Backward with indices]:");
        list.printBackwardsWithIndex();

        System.out.println();
        System.out.println("After deletions:");
        System.out.println("Linked List: [Forward with indices]:");
        list.deleteFirstNode();
        list.deleteLastNode();
        list.deleteSpecificNode(74);
        list.printForwardsWithIndex();

        System.out.println();
        System.out.println("After insertions:");
        System.out.println("Linked List: [Forward with indices]:");
        list.insertAfter(22, 69);
        list.insertAfter(33, 88);
        list.printForwardsWithIndex();
        
        System.out.println("\nDouble:");
        
        /* DoublyLinkedList which is a double type */
        DoublyLinkedList<Double> doubleList = new DoublyLinkedList<>();
        
        /* inserting on first and last position, and printing forwards and backwards
		 * inserting on the last position, and inserting after a specific position,
		 * and printing all the elements from first to last */
        
        doubleList.insertFirst(22.2);
        doubleList.insertFirst(44.2);
        doubleList.insertFirst(74.2);
        doubleList.insertFirst(97.2);

        doubleList.insertLast(9.2);
        doubleList.insertLast(33.2);
        doubleList.insertLast(55.2);

   	    System.out.println("Linked List: [Forward with indices]:");
        doubleList.printForwardsWithIndex();
        System.out.println();
        System.out.println("Linked List: [Backward with indices]:");
        doubleList.printBackwardsWithIndex();

        System.out.println();
        System.out.println("After deletions:");
        System.out.println("Linked List: [Forward with indices]:");
        doubleList.deleteFirstNode();
        doubleList.deleteLastNode();
        doubleList.deleteSpecificNode(74.2);
        doubleList.printForwardsWithIndex();

        System.out.println();
        System.out.println("After insertions:");
        System.out.println("Linked List: [Forward with indices]:");
        doubleList.insertAfter(22.2, 69.2);
        doubleList.insertAfter(33.2, 88.2);
        doubleList.printForwardsWithIndex();
        System.out.println();
        
        /* DoublyLinkedList which is a string type */
        DoublyLinkedList<String> stringList = new DoublyLinkedList<>();

        /* inserting on first and last position, and printing forwards and backwards
		 * inserting on the last position, and inserting after a specific position,
		 * and printing all the elements from first to last */
        
        stringList.insertFirst("Vasu");
        stringList.insertFirst("Patel");
        
        stringList.insertLast("Algonquin");
        stringList.insertLast("Ottawa");
        
   	    System.out.println("Linked List: [Forward with indices]:");
        stringList.printForwardsWithIndex();
        System.out.println();
        System.out.println("Linked List: [Backward with indices]:");
        stringList.printBackwardsWithIndex();
        
        System.out.println();
        System.out.println("After deletions:");
        System.out.println("Linked List: [Forward with indices]:");
        stringList.deleteFirstNode();
        stringList.deleteLastNode();
        stringList.deleteSpecificNode("Patel");
        stringList.printForwardsWithIndex();
        
        System.out.println();
        System.out.println("After insertions:");
        System.out.println("Linked List: [Forward with indices]:");
        stringList.insertAfter("Vasu","Patel");
        stringList.insertAfter("Patel","India");
        stringList.printForwardsWithIndex();
        
        System.out.println("\nThe program is made by Vasukumar Patel\n");
        
	
	} 


} 

