package core.domainmodels.supermarket;

import core.domainmodels.customer.Bill;
import core.domainmodels.customer.Customer;
import core.domainmodels.supermarket.product.Product;
import core.interfaces.Report;
import core.types.CustomerType;
import core.types.PaymentMethod;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CashDesk {
    private final Queue<Customer> customers;
    private final Bill bill;
    private final List<Product> retrievedProducts;

    public CashDesk(Bill bill) {
        customers = new ArrayDeque<>();
        this.bill = bill;
        this.retrievedProducts = new LinkedList<>();
    }

    void add(LocalTime addTime, Customer customer) {
        customers.add(customer);
        toLogAddInfo(addTime, bill.getBill(customer), customer.getName());
    }

    void serve(LocalTime serveTime, Report report) {
        Customer customer = customers.poll();

        if (customer == null)
            return;

        if (customer.getCustomerType() == CustomerType.Child) {
            System.out.println(serveTime + " Customer " + customer.getName() + " is a child. Checking products");
            checkForRestrictedProducts(customer.getProductsFromBasket(), serveTime);
        }

        BigDecimal price = bill.getBill(customer);
        if (price.compareTo(BigDecimal.ZERO) == 0) {
            System.out.println(customer.getName() + " nothing to pay");
            return;
        }

        if (customer.isEnoughMoney(price)) {
            customer.pay(price);
            report.add(customer.getProductsFromBasket());
            toLogServeInfo(serveTime, price, customer.getName(), customer.getPaymentMethod());
        } else {
            System.out.println("Customer " + customer.getName() + " has left. Not enough money");
        }


    }

    boolean isAllCustomersServed() {
        return customers.size() == 0;
    }

    private void checkForRestrictedProducts(List<Product> products, LocalTime localTime) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.isHaveAgeRestriction()) {
                System.out.println(localTime + " Product " + product.getName() + " not allowed for customer");
                products.remove(product);
                retrievedProducts.add(product);
            }
        }
    }

    public List<Product> returnProductsToShop() {
        return retrievedProducts;
    }

    private void toLogServeInfo(LocalTime serveTime, BigDecimal price, String name, PaymentMethod paymentMethod) {
        System.out.println(serveTime + " Customer - " + name + " paid "
                + price.setScale(0, RoundingMode.HALF_UP) + " by " + paymentMethod.toString());
        System.out.println(serveTime + " Customer leave from supermarket");
    }

    private void toLogAddInfo(LocalTime addTime, BigDecimal price, String name) {
        System.out.println(addTime + " Customer - " + name + " at the cash desk. Quantity to pay - "
                + price.setScale(0, RoundingMode.HALF_UP));
    }
}
