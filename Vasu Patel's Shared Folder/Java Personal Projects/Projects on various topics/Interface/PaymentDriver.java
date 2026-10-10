package Interface;

public class PaymentDriver {

	public static void main(String[] args) {
		
		CreditCardPayment creditObj = new CreditCardPayment(2.0);
		PayPalPayment paypalObj = new PayPalPayment(6.0);
		
		creditObj.makePayment(100);
		creditObj.showTransactionDetails();
		paypalObj.makePayment(200);
		paypalObj.showTransactionDetails();
		
	}
	
}
