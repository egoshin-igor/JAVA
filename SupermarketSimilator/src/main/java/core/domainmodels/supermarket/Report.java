package core.domainmodels.supermarket;

import core.domainmodels.supermarket.product.Product;
import core.interfaces.IReport;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

public class Report implements IReport {
    private final List<Product> _products;
    private BigDecimal _totalRevenue = new BigDecimal(0);

    public Report() {
        _products = new LinkedList<>();
    }

    @Override
    public void add(List<Product> products) {
        for (Product product: products) {
            add(product);
        }
    }

    @Override
    public void printReport() {
        for(Product product: _products) {
            System.out.println(product.getSoldInfo());
        }
        System.out.println("Total revenue: " + _totalRevenue.setScale(0, RoundingMode.HALF_UP));
    }

    private void add(Product product) {
        Product foundProduct = getByName(product.getName());
        _totalRevenue = _totalRevenue.add(product.getPrice().multiply(BigDecimal.valueOf(product.getUnits())));
        if (foundProduct != null) {
            foundProduct.put(product.getUnits());
        } else {
            _products.add(product);
        }
    }

    private Product getByName(String name) {
        for (Product product: _products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }

        return null;
    }
}
