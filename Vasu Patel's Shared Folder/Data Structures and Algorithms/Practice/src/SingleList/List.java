package SingleList;

public class List {

	private Node head;
	
	public List() { head = null; }

	public void addAtHead(String newData) {
		
		Node newNode = new Node(newData);
		newNode.updateNode(head);
		head = newNode;
		
	}
	
	public void display() {
		
		Node temp = head;
		
		while(temp != null) {
			System.out.println(temp);
			temp = temp.getNode();
		}
		
	}
	
	public Node deleteAtHead() {
		
		if(head == null) {
			return null;
		}
		
		Node removedHead = head;
		head = head.getNode();
		return removedHead;
		
	}
	
	public Node delete(String oneToDelete) {
	    Node removedOne = null;
	    Node temp = head;
	    Node prev = null;

	    while (temp != null) {
	        if (temp.toString().equals(oneToDelete)) {
	            removedOne = temp;

	           
	            if (temp == head) {
	                head = temp.getNode();
	            } else {
	              
	                prev.updateNode(temp.getNode());
	            }
	            break;
	        }

	        prev = temp;
	        temp = temp.getNode();
	    }

	    return removedOne;
	}

	
	public static void main(String[] args) {
	
		List list = new List();
		list.addAtHead("Linda");
		list.addAtHead("George");
		list.addAtHead("Vasu");
		list.display();
		
		Node removedOne = list.deleteAtHead();
		System.out.println("\nAfter delete, the list is");
		list.display();
		System.out.println("The one deleted is " + removedOne);
		list.display();
		list.delete("Linda");
		list.display();
		
	}
	
	
}
