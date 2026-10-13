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
 * this class has linkedlist which is specific to int type 
 */
public class TestDLLApp {
	
	/**
	 * parameterless constructor 
	 */
	public TestDLLApp() {}
	
	/**
	 * main() method to run the code
	 * @param args - arguments 
	 */
	public static void main(String[] args) {

		/* DoublyLinkedList which is a int type */
		DoublyLinkedList list = new DoublyLinkedList();
		
		/* inserting on first and last position, and printing forwards and backwards
		 * inserting on the last position, and inserting after a specific position,
		 * and printing all the elements from first to last */
		
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
        
        System.out.println("\nThe program is made by Vasukumar Patel\n");

	
	} 


} 
