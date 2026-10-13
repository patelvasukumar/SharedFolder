public class NumbersInfo {
	
	private int[] numbers;
	
	public NumbersInfo() {
		numbers = null;
	}
	
	public int[] getNumbers() {
		return numbers;
	}

	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}

	public int countOddNumbers() {
		int numCount = 0;
		int oddCount = 0;
		
		if (numbers == null) {
			
			return 0;
		} else 
		do {
			if (numbers[numCount] % 2 == 1) {
				++oddCount;
			}
			++numCount;
		} while(numCount < numbers.length);
		
		return oddCount;
	}
}