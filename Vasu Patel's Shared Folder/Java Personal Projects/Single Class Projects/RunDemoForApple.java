package Week12;

public class RunDemoForApple {
	
	public static void main(String[] args) {
		Apple[] apples = { 
				new Apple("Red", 25, 100) ,
				new Apple("Yellow", 35, 75) ,
				new Apple("Pink", 40, 80) 
				};
		int totalApples = 0;
		int totalValue = 0;
		for(int index = 0; index < apples.length; index++) {
			totalApples = totalApples + apples[index].getQuantity();
			totalValue = totalValue + apples[index].calculateTotal();
			System.out.println(apples[index].createReport());
		}
		System.out.println("Total apples in stock: " + totalApples);
		System.out.println("Total value in cents: " + totalValue);
	}
}
/*      First version, reference via array apples[index]
		for(int index = 0; index < apples.length; index++) {
			totalApples = totalApples + apples[index].getQuantity();
			totalValue = totalValue + apples[index].calculateTotal();
			System.out.println(apples[index].createReport());
		}
*/

/*      Second version, reference was resolved first.
		for(int index = 0; index < apples.length; index++) {
			Apple apple = apples[index];
			totalApples = totalApples + apple.getQuantity();
			totalValue = totalValue + apple.calculateTotal();
			System.out.println(apple.createReport());
		}
*/
/*      Third version, enhanced for loop, testing with tuna.
		for(Apple apple: apples) {
			// apple.setVariety("Tuna"); // testing.
			totalApples = totalApples + apple.getQuantity();
			totalValue = totalValue + apple.calculateTotal();
			System.out.println(apple.createReport());
		}
*/