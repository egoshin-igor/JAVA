package core.domainmodels.supermarket;

import core.domainmodels.supermarket.product.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

public class Report implements core.interfaces.Report {
    private final List<Product> products;
    private BigDecimal totalRevenue = new BigDecimal(0);

    public Report() {
        products = new LinkedList<>();
    }

    @Override
    public void add(List<Product> products) {
        for (Product product: products) {
            add(product);
        }
    }

    @Override
    public void printReport() {
        for(Product product: products) {
            System.out.println(product.getSoldInfo());
        }
        System.out.println("Total revenue: " + totalRevenue.setScale(0, RoundingMode.HALF_UP));
    }

    private void add(Product product) {
        Product foundProduct = getByName(product.getName());
        totalRevenue = totalRevenue.add(product.getPrice().multiply(BigDecimal.valueOf(product.getUnits())));
        if (foundProduct != null) {
            foundProduct.put(product.getUnits());
        } else {
            products.add(product);
        }
    }

    private Product getByName(String name) {
        for (Product product: products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }

        return null;
    }
}
