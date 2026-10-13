import java.util.Scanner;

public class Driver {
	
	public static final int MIN_RANGE = 10;
	public static final int MAX_RANGE = 50;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int numbersToCollect;
		int[] numbers;
		int count = 0;
		NumbersInfo numbersInfo;
		
		System.out.println("How many numbers would you like to collect?");
		numbersToCollect = input.nextInt();
		numbers = new int[numbersToCollect];
		
		while (count < numbersToCollect) {
			int number;
			System.out.printf("Enter a number between %d and %d (inclusive): ", MIN_RANGE, MAX_RANGE);
			number = input.nextInt();
			if (number < MIN_RANGE || number > MAX_RANGE)
				System.out.println("Number not in range");
			else {
				numbers[count] = number;
				++count;
			}
		}
		
		numbersInfo = new NumbersInfo();
		numbersInfo.setNumbers(numbers);
		
		System.out.println(numbersToCollect + " numbers were entered and " + numbersInfo.countOddNumbers() + " of them were odd.");
		input.close();
	}
}
