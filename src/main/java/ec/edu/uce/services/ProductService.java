package ec.edu.uce.services;

import ec.edu.uce.jpa.Product;
import jakarta.ejb.Stateless;

@Stateless
public class ProductService extends GenericServiceImpl<Product> {
    public ProductService() {
        super(Product.class);
    }
}