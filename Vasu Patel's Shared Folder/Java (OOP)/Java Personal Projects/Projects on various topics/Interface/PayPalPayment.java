package Interface;

public class PayPalPayment implements Payment {

	private double transactionFees;
	private double x;
	
	public PayPalPayment(double transactionFees) {
		this.transactionFees = transactionFees;
	}
	
	@Override 
	public void makePayment(double x) {
		
		System.out.println("Transaction: " + x);
		this.x = x;
	}
	
	@Override
	public void showTransactionDetails() {
		
		System.out.println("Transaction Fee: " + transactionFees);
		System.out.println("Total: " + (transactionFees + x));
		
	}
	
}
