package ec.edu.uce.payments;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPayment(PaymentType.TRANSFER)
public class Transfer implements Payment {
    @Override
    public String payProcess() {
        return "Pago realizado por transferencia bancaria";
    }
}
