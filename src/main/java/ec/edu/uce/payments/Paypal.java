package ec.edu.uce.payments;


import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPayment("paypal")
public class Paypal implements Payment {
    @Override
    public String payProcess(double amount) {
        return "Pago realizado por: Paypal, monto: " + amount;
    }
}
