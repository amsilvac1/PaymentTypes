package ec.edu.uce.payments;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPayment("transfer")
public class Transfer implements Payment {
    @Override
    public String payProcess(double amount) {
        return "Pago realizado por transferencia de: " + amount;
    }
}
