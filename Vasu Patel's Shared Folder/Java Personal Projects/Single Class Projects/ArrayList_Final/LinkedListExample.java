package ArrayList_Final;

import java.util.LinkedList;

public class LinkedListExample {

	public static void main(String[] args) {
	
	LinkedList<String> cars = new LinkedList<>();
	
	cars.add("Volvo");
	cars.add("BMW");
	cars.add("Ford");
	cars.add("Mazda");
	cars.add("Maruti-Suzuki");

	System.out.println(cars);
	
	cars.addFirst("Tesla");
	cars.addLast("Honda");
	
	System.out.println(cars);
	
	}
}
