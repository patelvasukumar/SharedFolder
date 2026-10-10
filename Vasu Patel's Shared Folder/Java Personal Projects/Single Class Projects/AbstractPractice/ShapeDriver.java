package AbstractPractice;

public class ShapeDriver {
	
	public static void main(String[] args) {
		
			Circle circleObj = new Circle(1);
			var rectangleObj = new Rectangle(1,1);
		
		    System.out.printf("%5.2f\n",circleObj.area());
			System.out.println(circleObj.perimeter());
			System.out.println(rectangleObj.area());
			System.out.println(rectangleObj.perimeter());
			
	}
	
}
