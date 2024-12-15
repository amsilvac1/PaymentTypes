package ec.edu.uce.payments;


import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPayment(PaymentType.PAYPAL)
public class Paypal implements Payment {
    @Override
    public String payProcess() {
        return "Pago realizado por: Paypal";
    }
}
