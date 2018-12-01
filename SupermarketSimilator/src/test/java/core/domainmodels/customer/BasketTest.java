package core.domainmodels.customer;

import core.domainmodels.supermarket.product.Discount;
import core.domainmodels.supermarket.product.Product;
import core.types.UnitType;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BasketTest {

    @Test
    public void addProductAndGetProducts() {
        Basket basket = new Basket();
        Product product = new Product(
                "1222223",
                BigDecimal.ONE,
                true,
                new Discount(1),
                1,
                UnitType.Quantity
        );
        String expectedProductName = "1222223";

        basket.addProduct(product);
        List<Product> result = basket.getProducts();

        assertEquals(1, result.size());
        assertEquals(expectedProductName, result.get(0).getName());
    }
}