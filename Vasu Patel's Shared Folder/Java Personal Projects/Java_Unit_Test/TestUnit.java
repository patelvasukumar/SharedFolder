package Java_Unit_Test;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUnit {

    @Test
    public void test1() {
    	
    	var myObj = new FindMax();
    	
        double result = myObj.findMax(2, 4); // Expected result should be 4, as it's the max value
        assertEquals(4, result, 0.1);
    }
    
    @Test
    public void test2() {
    	
    	assertEquals(4, Sum.add(2,2.0));
    	assertTrue(Sum.add(2,2.0) == 4);
    	assertNotNull(Sum.add(2, 2.0) == 4);
    	
    	// Problem. Although it should display failure, it is showing against of it.
    	assertEquals(9,Sum.add(3, 3));
    	
    }
    
    
	@Test
	public void fiftyNineShouldReturnF() {
		var grade = new Grade();
		assertEquals('F',Grade.determineLetterGrade(2));
		
 assertThrows( IllegalArgumentException.class, () -> { Grade.determineLetterGrade(-30); } );
 
	}
    
    }
