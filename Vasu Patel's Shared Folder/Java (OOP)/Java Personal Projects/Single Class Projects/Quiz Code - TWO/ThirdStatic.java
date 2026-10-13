package Week4_quiz;

public class ThirdStatic {

	public static class A {
	    public A() {
	        System.out.print("A");
	    }
	}

	public static class B extends A {
	    public B() {
	        System.out.print("B");
	    }
	}

	public static void main(String[] args) {
	    A a = new B(); // This works if classes are static
	    
	}

	
}
