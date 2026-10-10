
public class Apple {
    private int quantity;
    
    
    public Apple() {
    	quantity = 0;
    }

	public int getQuantity() {
		return quantity;
	}

	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	public String toString() {
		String report;
		report = "Apple class with quantity of " + quantity + ".";
		return report;
	}
    
}
