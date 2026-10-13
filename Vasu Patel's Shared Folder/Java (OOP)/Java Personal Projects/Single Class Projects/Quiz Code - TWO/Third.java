package Week4_quiz;

public class Third {

    public class A {
        public A() {
            System.out.print("A");
        }
    }

    public class B extends A {
        public B() {
            System.out.print("B");
        }
    }

    public static void main(String[] args) {
        Third outer = new Third();   // Create an instance of the outer class
        A a = outer.new B();         // Create an instance of the inner class B
    }
}

