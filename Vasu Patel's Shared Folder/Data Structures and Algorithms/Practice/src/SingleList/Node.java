package SingleList;

public class Node {

	String data;
	Node next;
	
	public Node(String data) {
		this.data = data;
		this.next = null;
	}
	
	public Node() {
		this.data = null;
		this.next = null;
	}
	
	public Node getNode() { return this.next; }
	public void updateNode(Node nextNode) { this.next = nextNode; }
	
	public String toString() { return this.data; }
	
	
}
