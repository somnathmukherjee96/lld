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
        System.out.println("₹" + amount + " is paid via credit Card");
    }
}
