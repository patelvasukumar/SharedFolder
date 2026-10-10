package Interface;

public interface Payment {

	
	
	public void makePayment(double amount); // you cannot make a static method for abstract method.
	
	// private static int lastMethod() { return 0; }
	// protected default int lastMethod2() {} : protected is not allowed int the interface.
	// The default keyword is only allowed inside interfaces, not classes.
	
	public void showTransactionDetails();

	
}

// see the interface application. How to make interface.
// private and protected are not allowed.
// public abstract void makePayment(); If you do not write abstract, it does not matter.
// it will make it automatically because it does not have body {}.
// you can make private and protected methods but they must not be abstract method.
// we can inherit the properties of interface class by using extends.
//  you cannot create an object (instance) of an interface in Java 
// because an interface is an abstract type that only defines method signatures without implementation. 
// However, you can create objects of classes that implement the interface.
// Interfaces cannot have constructors – Only classes can have constructors to instantiate objects.
// Interfaces are abstract by default – You cannot create an instance of an interface.
// can sue static and object to call method. Also default.

// Static and Default only there two methods are allowed.

// you cannot use private at all in the interface.

// Interfaces: Use extends for inheritance between interfaces, and classes use implements to implement interfaces.
// Classes: Use extends for inheritance between classes.










