package core.domainmodels.supermarket;

import core.domainmodels.customer.Bill;
import core.interfaces.Report;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.*;

public class SupermarketTest {
    private Supermarket supermarket;

    @Before
    public void before() {
        Report report = new core.domainmodels.supermarket.Report();
        supermarket = new Supermarket(new CashDesk(new Bill()), report);
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
}