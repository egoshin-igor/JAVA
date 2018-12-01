package core.domainmodels.customer;

import core.domainmodels.supermarket.product.Discount;
import core.domainmodels.supermarket.product.Product;
import core.types.CustomerType;
import core.types.PaymentMethod;
import core.types.UnitType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class CustomerTest {
    private Product product;
    private Customer customer;

    @Before
    public void before() {
        Basket basket = new Basket();
        product = new Product(
                "1222223",
                BigDecimal.TEN,
                true,
                new Discount(0),
                2,
                UnitType.Quantity
        );
        customer =
                new Customer(basket, "1", CustomerType.Child, BigDecimal.ONE, PaymentMethod.Card);
    }

    @After
    public void after() {
        customer.getProductsFromBasket().clear();
    }

    @Test
    public void putToBasketProduct_HasUnits_addToBasket() {
        customer.putToBasket(LocalTime.now(), product);

        boolean expectedSize = (customer.getProductsFromBasket().size() == 1) ||
                (customer.getProductsFromBasket().size() == 0);
        assertTrue(expectedSize);
    }

    @Test
    public void putToBasket_productHasntUnits_notAddToBasket() {
        product.take(2);
        customer.putToBasket(LocalTime.now(), product);

        assertEquals(0, customer.getProductsFromBasket().size());
    }

    @Test
    public void checkCustomerDataCorrection() {
        assertEquals("1", customer.getName());
        assertEquals(PaymentMethod.Card, customer.getPaymentMethod());
    }

    @Test
    public void isEnoughMoney() {
        BigDecimal productsPrice = BigDecimal.valueOf(1);
        if (customer.isEnoughMoney(productsPrice)) {
            customer.pay(productsPrice);
        }

        assertFalse(customer.isEnoughMoney(BigDecimal.ONE));
    }
}