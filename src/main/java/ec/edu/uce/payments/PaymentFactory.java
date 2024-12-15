package ec.edu.uce.payments;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

@ApplicationScoped
public class PaymentFactory {

    @Inject
    private Instance<Payment> paymentInstance;  // CDI Instance para inyectar los Payment disponibles

    public Payment getPayment(String paymentType) {
        // Seleccionamos el tipo de pago según el valor del paymentType
        switch (paymentType.toLowerCase()) {
            case "creditcard":
                return paymentInstance.select(CreditCard.class).get();
            case "paypal":
                return paymentInstance.select(Paypal.class).get();
            case "transfer":
                return paymentInstance.select(Transfer.class).get();
            default:
                throw new IllegalArgumentException("Tipo de pago no soportado: " + paymentType);
        }
    }
}

