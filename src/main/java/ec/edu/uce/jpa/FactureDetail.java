package ec.edu.uce.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "bill_detail")
public class FactureDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double quantity;
    private double unitPrice;

    @ManyToOne
    @JoinColumn(name = "facture_id", nullable = false)
    private FactureClient FactureClient;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // CONSTRUCTOR POR DEFECTO
    public FactureDetail() {
    }

    // GETTERS Y SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double price) {
        this.quantity = price;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public FactureClient getFactureClient() {
        return FactureClient;
    }

    public void setFactureClient(FactureClient FactureClient) {
        this.FactureClient = FactureClient;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}

