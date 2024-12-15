package ec.edu.uce.jpa;


import ec.edu.uce.payments.Payment;
import ec.edu.uce.payments.QualifierPayment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;

import javax.management.Notification;
import java.util.List;

@Entity
public class FactureClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String date;
    private String paymentType;
    private double total;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany (mappedBy = "FactureClient",fetch = FetchType.EAGER)
    private List<FactureDetail> factureDetail;




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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
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

    public List<FactureDetail> getFactureDetail() {
        return factureDetail;
    }

    public void setFactureDetail(List<FactureDetail> factureDetail) {
        this.factureDetail = factureDetail;
    }

}
