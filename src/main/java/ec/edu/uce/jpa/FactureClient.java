package ec.edu.uce.jpa;


import ec.edu.uce.payments.Payment;
import ec.edu.uce.payments.PaymentFactory;
import jakarta.inject.Inject;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class FactureClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String PaymentType;
    private String date;
    private double total;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany (mappedBy = "FactureClient")
    private List<FactureDetail> factureDetail;

    @Inject
    private PaymentFactory paymentFactory;

    @Transient
    private Payment payment;

    //CONSTRUCTOR POR DEFECTO
    public FactureClient() {
    }

    //GETTERS Y SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        this.PaymentType = paymentType;
        this.payment = paymentFactory.getPayment(paymentType);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


}
