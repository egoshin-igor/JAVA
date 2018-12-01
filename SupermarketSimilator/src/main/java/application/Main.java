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

public class Main {
    public static void main(String[] args) {
        Report report = new core.domainmodels.supermarket.Report();
        Supermarket supermarket = new Supermarket(new CashDesk(new Bill()), report);
        Generation<Product> productGeneration = new ProductGeneration();
        Generation<Customer> customerGeneration = new CustomerGeneration();

        SupermarketSimulator supermarketSimulator = new application.SupermarketSimulator(
                customerGeneration, productGeneration, supermarket);

        supermarketSimulator.SimulateWork();
    }
}
