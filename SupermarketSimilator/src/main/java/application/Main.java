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
import infrastructure.Generators.CustomertGeneration;
import infrastructure.Generators.ProductGeneration;

public class Main {
    public static void main(String[] args) {
        IReport report = new Report();
        Supermarket supermarket = new Supermarket(new CashDesk(new Bill()), report);
        IGeneration<Product> productGeneration = new ProductGeneration();
        IGeneration<Customer> customerGeneration = new CustomertGeneration();

        ISupermarketSimulator supermarketSimulator = new SupermarketSimulator(
                customerGeneration, productGeneration, supermarket);

        supermarketSimulator.SimulateWork();
    }
}
