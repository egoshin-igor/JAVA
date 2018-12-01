package core.domainmodels.customer;

import core.domainmodels.supermarket.product.Product;

import java.util.LinkedList;
import java.util.List;

public class Basket {
    private final List<Product> products;

    public Basket() {
        products = new LinkedList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }
}
