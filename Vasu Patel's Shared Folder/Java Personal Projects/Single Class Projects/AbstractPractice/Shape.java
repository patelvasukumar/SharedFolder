package AbstractPractice;

public abstract class Shape {

	protected Shape() {
		
	}
	
	protected abstract double area();
	
	protected abstract double perimeter();
	
	protected void display() {
		System.out.println("This is a shape.");
	}
	
}

// Notes: You can not use private for abstract method.
// you have to write abstract before method and class both.
// in order to make a class abstract, you need a abstract method. Without it, it is interface.
// you can use any data type which you want.
// the access modifier which you used in the super class. You have to use those in the subclass. Same as it is in the abstract class.
// use extends in subclass when using abstract class.
// you do not need to override or to use concrete method in the subclass. They are optional.

// You cannot directly call a method or variable from an abstract class using 
// an object of the abstract class itself,
// because you cannot instantiate an abstract class.

//  you can create objects of other classes within an abstract class. 
// An abstract class can have methods, constructors, 
// and member variables like any other class, including creating instances of other classes.
// However, you cannot instantiate the abstract class itself. But inside the abstract class, 
// you can create objects of other non-abstract classes.

class Circle extends Shape {

    private double radius;  

    public Circle(double radius) {
        super();  
        this.radius = radius;
    }

    @Override
    protected double area() {
        return Math.PI * radius * radius;  
    }

    @Override
    protected double perimeter() {
        return 2 * Math.PI * radius;  
    }
}

class Rectangle extends Shape {

    private double length;
    private double width;

    public Rectangle(double length, double width) {
        super();  
        this.length = length;
        this.width = width;
    }

    @Override
    protected double area() {
        return length * width;  
    }

    @Override
    protected double perimeter() {
        return 2 * (length + width);  
    }
}
