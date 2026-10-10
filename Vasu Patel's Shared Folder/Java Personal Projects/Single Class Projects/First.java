package ObjAndCons;

public class First {

	// Value
	public int x = 1;
	
	// Method
	public double vasu() {
		double vasu=x;
		return vasu;
	}
	
	// Cons
	First(int x){this.x=x;}
	
	First(int x , double y) {this.x=x + 1; this.x=(int) y;}
	
	// Drive
	public static void main(String[] args) {
		First myObj = new First(10,2.2);
		System.out.println(myObj.vasu());
	
	}
	
}
