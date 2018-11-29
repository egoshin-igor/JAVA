package core.interfaces;

import core.domainmodels.supermarket.product.Product;

import java.util.List;

public interface IReport {
    void add(Product product);
    void add(List<Product> product);
    void printReport();
}
