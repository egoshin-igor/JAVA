package core.domainmodels.supermarket;

import core.domainmodels.customer.Customer;
import core.domainmodels.supermarket.product.Product;
import core.interfaces.Report;

import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Supermarket {
    private final Random random;
    private final CashDesk cashDesk;
    private final List<Product> products;
    private final List<Customer> customers;
    private final Report report;
    private boolean isOpened;

    public Supermarket(
            CashDesk cashDesk,
            Report report
    ) {
        this.cashDesk = cashDesk;
        this.random = new Random();
        this.report = report;
        products = new LinkedList<>();
        customers = new LinkedList<>();
    }

    public void addProductsFirstTime(LocalTime localTime, List<Product> products) {
        this.products.addAll(products);
        System.out.println(localTime + " Supermarket products have been formed: ");
        logProductsCreating();
    }

    public void open(LocalTime localTime) {
        isOpened = true;
        System.out.println(localTime + " Supermarket is opened");
    }

    public void close(LocalTime localTime) {
        isOpened = false;
        customers.clear();
        System.out.println(localTime + " Supermarket is closed");
        products.addAll(cashDesk.returnProductsToShop());
        report.printReport();
    }

    public void acceptNewCustomer(Customer customer, LocalTime localTime) {
        if (!isOpened)
            return;

        customers.add(customer);
        System.out.println(localTime + " Customer " + customer.getName() + " came to supermarket");
    }

    public void someCustomerHasAddedProductToBasket(LocalTime localTime) {
        if (!isOpened)
            return;

        if (customers.size() == 0)
            return;

        Customer customer = this.customers.get(this.random.nextInt(this.customers.size()));
        Product product = this.products.get(this.random.nextInt(this.products.size()));
        customer.putToBasket(localTime, product);
        if (product.getUnits() == 0) {
            this.products.remove(product);
        }
    }

    public void someCustomerGoToCashDesk(LocalTime localTime) {
        if (!isOpened)
            return;

        if (customers.size() == 0)
            return;

        int customerIndex = random.nextInt(customers.size());
        Customer customer = customers.get(customerIndex);
        if (customer.getProductsFromBasket().size() == 0) {
            return;
        }

        cashDesk.add(localTime, customer);
        customers.remove(customerIndex);
    }

    public void serveSomeCustomer(LocalTime localTime) {
        if (!isOpened)
            return;

        cashDesk.serve(localTime, report);
    }

    public boolean isAllCustomersServed(){
        return cashDesk.isAllCustomersServed();
    }

    public void addProducts(List<Product> products) {
        this.products.addAll(products);
    }

    public boolean isBoughtAllProducts() {
        return products.size() == 0;
    }

    public boolean isOpened() { return isOpened; }

    private void logProductsCreating() {
        for (Product product : products) {
            System.out.println(product.getName() +
                    ", price: " + product.getPrice().setScale(0, RoundingMode.HALF_UP) +
                    ", count: " + product.getUnits() +
                    ", isAcceptAge: " + product.isHaveAgeRestriction()
            );
        }
    }
}
