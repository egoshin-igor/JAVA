package core.domainmodels.customer;

import core.domainmodels.supermarket.product.Product;

import java.math.BigDecimal;
import java.util.List;

public class Bill {
    public BigDecimal getBill(Customer customer) {
        List<Product> products = customer.getProductsFromBasket();
        BigDecimal result = new BigDecimal(0);
        for (Product product : products) {

            BigDecimal price = product.getPrice(customer.getCustomerType());
            int units = product.getUnits();
            result = result.add(price.multiply(BigDecimal.valueOf(units)));
        }

        return result;
    }
}
