package ec.edu.uce.payments;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPayment(PaymentType.CREDITCARD)
public class CreditCard implements Payment {
    @Override
    public String payProcess() {
        return "Pago realizado por tarjeta de crédito";
    }
}
