package core.domainmodels.customer;

import core.domainmodels.supermarket.product.Discount;
import core.domainmodels.supermarket.product.Product;
import core.types.CustomerType;
import core.types.PaymentMethod;
import core.types.UnitType;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BillTest {

    @Test
    public void getBill() {
        Basket basket = new Basket();
        Product product = new Product(
                "1222223",
                BigDecimal.TEN,
                true,
                new Discount(0),
                2,
                UnitType.Quantity
        );
        basket.addProduct(product);
        Customer customer = new Customer(basket, "1", CustomerType.Child, BigDecimal.ONE, PaymentMethod.Card);
        Bill bill = new Bill();

        BigDecimal result = bill.getBill(customer);
        assertEquals(BigDecimal.valueOf(20), result);
    }
}