package core.domainmodels.supermarket;

import core.domainmodels.customer.Customer;
import core.domainmodels.supermarket.product.Product;
import core.interfaces.IReport;

import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Supermarket {
    Random _random;
    private CashDesk _cashDesk;
    private List<Product> _products;
    private List<Customer> _customers;
    private IReport _report;
    private boolean _isOpened;

    public Supermarket(
            CashDesk cashDesk,
            IReport report
    ) {
        _cashDesk = cashDesk;
        _random = new Random();
        _report = report;
        _products = new LinkedList<>();
        _customers = new LinkedList<>();
    }

    public void addProductsFirstTime(LocalTime localTime, List<Product> products) {
        _products.addAll(products);
        System.out.println(localTime + " Supermarket products have been formed: ");
        logProductsCreating();
    }

    public void open(LocalTime localTime) {
        _isOpened = true;
        System.out.println(localTime + " Supermarket is opened");
    }

    public void close(LocalTime localTime) {
        _isOpened = false;
        _customers.clear();
        System.out.println(localTime + " Supermarket is closed");
        _report.printReport();
    }

    public void acceptNewCustomer(Customer customer, LocalTime localTime) {
        if (!_isOpened)
            return;

        _customers.add(customer);
        System.out.println(localTime + " Customer " + customer.getName() + " came to supermarket");
    }

    public void someCustomerHasAddedProductToBasket(LocalTime localTime) {
        if (!_isOpened)
            return;

        if (_customers.size() == 0)
            return;

        Customer customer = _customers.get(_random.nextInt(_customers.size()));
        Product product = _products.get(_random.nextInt(_products.size()));
        customer.putToBasket(product);
        if (product.getUnits() == 0) {
            _products.remove(product);
        }
        System.out.println(localTime + " Customer " + customer.getName() + " picked up " + product.getTookInfo());
    }

    public void someCustomerGoToCashDesk(LocalTime localTime) {
        if (!_isOpened)
            return;

        if (_customers.size() == 0)
            return;

        int customerIndex = _random.nextInt(_customers.size());
        Customer customer = _customers.get(customerIndex);
        if (customer.getProductsFromBasket().size() == 0) {
            return;
        }

        _cashDesk.add(customer, localTime);
        _customers.remove(customerIndex);
    }

    public void serveSomeCustomer(LocalTime localTime) {
        if (!_isOpened)
            return;

        _cashDesk.serve(localTime, _report);
    }

    public boolean isAllCustomersServed(){
        return _cashDesk.isAllCustomersServed();
    }

    public void addProducts(List<Product> products) {
        _products.addAll(products);
    }

    public boolean isBoughtAllProducts() {
        return _products.size() == 0;
    }

    private void logProductsCreating() {
        for (Product product : _products) {
            System.out.println(product.getName() +
                    ", price: " + product.getPrice().setScale(0, RoundingMode.HALF_UP) +
                    ", count: " + product.getUnits() +
                    ", isAcceptAge: " + product.isHaveAgeRestriction()
            );
        }
    }
}
