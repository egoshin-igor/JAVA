package application;

import application.interfaces.IGeneration;
import application.interfaces.ISupermarketSimulator;
import core.domainmodels.customer.Bill;
import core.domainmodels.customer.Customer;
import core.domainmodels.supermarket.CashDesk;
import core.domainmodels.supermarket.Report;
import core.domainmodels.supermarket.Supermarket;
import core.domainmodels.supermarket.product.Product;
import core.interfaces.IReport;
import infrastructure.Generators.CustomerGeneration;
import infrastructure.Generators.ProductGeneration;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class SupermarketSimulatorTest {


    @Test
    public void simulateWork() {
        IReport report = new Report();
        Supermarket supermarket = new Supermarket(new CashDesk(new Bill()), report);
        IGeneration<Customer> customerGeneration = new CustomerGeneration();
        IGeneration<Product> productGeneration = new ProductGeneration();

        ISupermarketSimulator supermarketSimulator = new SupermarketSimulator(
                customerGeneration, productGeneration, supermarket);

        assertFalse(supermarket.isOpened());
        supermarketSimulator.SimulateWork();
        assertFalse(supermarket.isOpened());
    }
}