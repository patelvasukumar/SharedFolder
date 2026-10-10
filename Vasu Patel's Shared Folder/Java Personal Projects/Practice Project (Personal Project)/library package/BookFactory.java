package library;


public class BookFactory {

	public Book getBook(String type) {
	
		if (type.equalsIgnoreCase("FictionBook")) {
	    
			return new FictionBook();
	        
		} else if (type.equalsIgnoreCase("NonFictionBook")) {
	    
			return new NonFictionBook();
	        
		}
	        return null;
	    }
	}
	

