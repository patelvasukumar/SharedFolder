package Interface;

public class CreditCardPayment implements Payment {

	private double transactionFees;
	private double x;
	
	public CreditCardPayment(double transactionFees) {
		this.transactionFees=transactionFees;
	}
	
	@Override 
	public void makePayment(double x) {
		
		System.out.println("Transaction: " + x);
		this.x = x;
	}
	
	@Override
	public void showTransactionDetails() {
		
		System.out.println("Transaction Fee: " + this.transactionFees);
		System.out.println("Total: " + (transactionFees + x));
		
	}
	
	
	
}
