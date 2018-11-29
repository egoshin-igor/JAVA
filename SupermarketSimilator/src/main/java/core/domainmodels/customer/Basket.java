package core.domainmodels.customer;

import core.domainmodels.supermarket.product.Product;

import java.util.LinkedList;
import java.util.List;

public class Basket {
    private List<Product> _products;

    public Basket() {
        _products = new LinkedList<>();
    }

    public void addProduct(Product product) {
        _products.add(product);
    }

    public List<Product> getProducts() {
        return _products;
    }
}
