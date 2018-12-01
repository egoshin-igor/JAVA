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
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.*;

public class CashDeskTest {
    private Product product;
    private Customer customer;
    private CashDesk cashDesk;
    private IReport report;

    @Before
    public void before() {
        report = new Report();
        cashDesk = new CashDesk(new Bill());
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
        cashDesk.serve(LocalTime.now(), report);
    }


    @Test
    public void add() {
        cashDesk.add(LocalTime.now(), customer);

        assertFalse(cashDesk.isAllCustomersServed());
    }

    @Test
    public void serve() {
        cashDesk.add(LocalTime.now(), customer);

        cashDesk.serve(LocalTime.now(), report);

        assertTrue(cashDesk.isAllCustomersServed());
    }

    @Test
    public void returnProductsToShop() {
        List<Product> products = cashDesk.returnProductsToShop();

        assertEquals(0, products.size());
    }
}