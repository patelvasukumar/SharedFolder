package ArrayList_Final;


import java.util.LinkedList;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Deque;

public class AllQueueExample {

	public static void main(String[] args) {
		
	Queue<Integer> queue = new LinkedList<>();  
	queue.add(10);
    queue.add(20);
    queue.add(30);   
    System.out.println(queue);
    queue.remove(); 
    System.out.println(queue);
    
    Deque<Integer> deque = new LinkedList<>();
    deque.addFirst(100); 
    deque.addLast(200);  
    deque.addLast(300);
    System.out.println(deque);
    deque.removeFirst(); 
    deque.removeLast();  
    System.out.println(deque);

    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
    priorityQueue.add(50);
    priorityQueue.add(10);
    priorityQueue.add(30);
    System.out.println(priorityQueue);
    priorityQueue.remove();
    System.out.println(priorityQueue);
    
		
	}
	
}
