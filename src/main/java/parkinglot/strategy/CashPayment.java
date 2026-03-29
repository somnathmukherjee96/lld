package parkinglot.strategy;

public class CashPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.printf("✓ Payment of ₹%.2f received in Cash%n",
                amount);
    }
}
