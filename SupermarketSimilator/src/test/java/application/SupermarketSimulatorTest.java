package application;

import application.interfaces.Generation;
import application.interfaces.SupermarketSimulator;
import core.domainmodels.customer.Bill;
import core.domainmodels.customer.Customer;
import core.domainmodels.supermarket.CashDesk;
import core.domainmodels.supermarket.Supermarket;
import core.domainmodels.supermarket.product.Product;
import core.interfaces.Report;
import infrastructure.Generators.CustomerGeneration;
import infrastructure.Generators.ProductGeneration;
import org.junit.Test;

import static org.junit.Assert.*;

public class SupermarketSimulatorTest {


    @Test
    public void simulateWork() {
        Report report = new core.domainmodels.supermarket.Report();
        Supermarket supermarket = new Supermarket(new CashDesk(new Bill()), report);
        Generation<Customer> customerGeneration = new CustomerGeneration();
        Generation<Product> productGeneration = new ProductGeneration();

        SupermarketSimulator supermarketSimulator = new application.SupermarketSimulator(
                customerGeneration, productGeneration, supermarket);

        assertFalse(supermarket.isOpened());
        supermarketSimulator.SimulateWork();
        assertFalse(supermarket.isOpened());
    }
}