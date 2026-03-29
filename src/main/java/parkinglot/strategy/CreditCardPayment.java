package parkinglot.strategy;

public class CreditCardPayment implements PaymentStrategy {
    private final String accountNumber;
    private final String accountHolderName;

    public CreditCardPayment(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
    }

    @Override
    public void pay(double amount) {
        System.out.printf("✓ Payment of ₹%.2f processed via Credit Card (holder: %s)%n",
                amount, accountHolderName);
    }
}
