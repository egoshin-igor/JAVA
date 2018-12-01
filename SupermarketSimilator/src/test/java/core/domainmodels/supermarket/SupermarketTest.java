package core.domainmodels.supermarket;

import core.domainmodels.customer.Basket;
import core.domainmodels.customer.Bill;
import core.domainmodels.customer.Customer;
import core.domainmodels.supermarket.product.Discount;
import core.domainmodels.supermarket.product.Product;
import core.interfaces.IReport;
import core.types.CustomerType;
import core.types.PaymentMethod;
import core.types.UnitType;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class SupermarketTest {
    Supermarket supermarket;

    @Before
    public void before() {
        IReport report = new Report();
        supermarket = new Supermarket(new CashDesk(new Bill()), report);
    }

    @Test
    public void addProductsFirstTime() {
    }

    @Test
    public void open() {
        supermarket.open(LocalTime.MIN);

        assertTrue(supermarket.isOpened());
    }

    @Test
    public void close() {
        supermarket.close(LocalTime.MIN);

        assertTrue(supermarket.isAllCustomersServed());
        assertFalse(supermarket.isOpened());
    }

    @Test
    public void acceptNewCustomer() {
        Basket basket = new Basket();

        Customer customer =
                new Customer(basket, "1", CustomerType.Child, BigDecimal.ONE, PaymentMethod.Card);

        supermarket.acceptNewCustomer(customer, LocalTime.MIN);
    }

    @Test
    public void someCustomerHasAddedProductToBasket() {
    }

    @Test
    public void someCustomerGoToCashDesk() {
    }

    @Test
    public void serveSomeCustomer() {
    }

    @Test
    public void isAllCustomersServed() {
    }

    @Test
    public void addProducts() {
    }

    @Test
    public void isBoughtAllProducts() {
    }
}