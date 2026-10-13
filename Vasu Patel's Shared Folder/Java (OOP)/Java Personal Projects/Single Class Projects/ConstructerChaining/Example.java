package ConstructerChaining;

public class Example {

	    public Example() {
	        
	    	System.out.println(" Hello, Cons.");
	        printMessage();  
	    
	    }

	    public void printMessage() {
	      
	    	System.out.println(" Hello, Method.");
	    
	    }

	    public static void main(String[] args) {
	       
	    	Example obj = new Example();  
	    }
	
}

	

