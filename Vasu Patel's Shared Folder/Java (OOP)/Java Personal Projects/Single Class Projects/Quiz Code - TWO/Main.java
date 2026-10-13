package Week4_quiz;

 class A {
    public A() {
        System.out.print("A");
    }
}

 class B extends A {
    public B() {
        this(4); // Calls the constructor of B that takes an int parameter
        System.out.print("B");
    }

    public B(int x) {
        System.out.print(x); // Prints the value of x
    }
}

public class Main {
    public static void main(String[] args) {
        B b = new B();
    }
}

/*
 * Yes, if a subclass constructor is invoked, the parent class constructor will always be invoked
 * automatically before the subclass constructor does its work. This happens because in Java, a
 * subclass relies on its parent class to properly initialize the inherited parts of the object.
 */ 

