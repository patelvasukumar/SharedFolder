package Factory;

/** Step 4 Use the Factory to get object of concrete class by passing an information such as type.
 * Factory Design Pattern - object created depends input data passed
 * Credits: tutorials point 
*/
public class FactoryDPTest {

    public static void main(String[] args) {

      System.out.println("");
      
       ShapeFactory shapeFactory = new ShapeFactory();
 
       //get an object of Circle and call its draw method.
       Shape shape1 = shapeFactory.getShape("CIRCLE");
 
       //call draw method of Circle
       shape1.draw();
 
       //get an object of Rectangle and call its draw method.
       Shape shape2 = shapeFactory.getShape("RECTANGLE");
 
       //call draw method of Rectangle
       shape2.draw();
 
       //get an object of Square and call its draw method.
       Shape shape3 = shapeFactory.getShape("TRIANGLE");
 
       //call draw method of square
       shape3.draw();
    }
 }
