package Factory;

/** Step2 Create concrete classes implementing the same interface.*/

public class Triangle implements Shape {

    @Override
    public void draw() {
       System.out.println("Inside Triangle::draw() method.");
    }
 }
