package Factory;

/** Step 3 Create a Factory class to generate object of concrete class based on given information.
*  Credits: tutorials point 
*
*/

public class ShapeFactory {
           //no constructors are allowed
   //use getShape method to get object of type shape 
   public Shape getShape(String shapeType){
      if(shapeType == null){
         return null;
      }		
      if(shapeType.equalsIgnoreCase("CIRCLE")){
         return new Circle();
         
      } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
         return new Rectangle();
         
      } else if(shapeType.equalsIgnoreCase("TRIANGLE")){
         return new Triangle();
      }
      
      return null;
   }
}

