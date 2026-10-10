package Final;

public class Fifth {

	public static void main(String[] args) {
		
		
		
		String question = "5 BJP politicians' name?";
		System.out.printf("%s%n", question);
		
		String[] politicians = {"Modi","Yogi","Amit Shah","Advani","Rupani"};
		
		int counter = 0;
		
		while(counter < politicians.length) {
			System.out.println(politicians[counter]);
			counter++;
		}

		
		
		System.out.println();
		System.out.println("5 joker politicians:");
		
		String[] joker = {"Rahul", "Biden", "Justin", "Jig Pig"};
		
		for(String counting : joker) {
			System.out.println(counting);
		}
		
		
		
		}
	}
	

