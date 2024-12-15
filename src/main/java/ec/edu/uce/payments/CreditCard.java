package ec.edu.uce.payments;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPayment("creditcard")
public class CreditCard implements Payment {
    @Override
    public String payProcess(double amount) {
        return "Pago realizado por tarjeta de crédito de: " + amount;
    }
}
