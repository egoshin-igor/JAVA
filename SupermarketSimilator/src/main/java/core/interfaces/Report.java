package core.interfaces;

import core.domainmodels.supermarket.product.Product;

import java.util.List;

public interface Report {
    void add(List<Product> product);
    void printReport();
}
