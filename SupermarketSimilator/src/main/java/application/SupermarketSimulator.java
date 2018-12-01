package application;

import application.interfaces.Generation;
import core.domainmodels.customer.Customer;
import core.domainmodels.supermarket.Supermarket;
import core.domainmodels.supermarket.product.Product;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;

public class SupermarketSimulator implements application.interfaces.SupermarketSimulator {
    private final List<Customer> customers;
    private final Generation<Customer> customersGeneration;
    private final Generation<Product> productsGeneration;
    private final Supermarket supermarket;

    SupermarketSimulator(
            Generation<Customer> customersGeneration,
            Generation<Product> productsGeneration,
            Supermarket supermarket
    ) {
        customers = customersGeneration.Generate();
        this.customersGeneration = customersGeneration;
        this.productsGeneration = productsGeneration;
        this.supermarket = supermarket;
    }

    public void SimulateWork() {
        final LocalTime start = LocalTime.of(8, 0,0,0);
        final LocalTime end = LocalTime.of(23, 0,0,0);
        LocalTime currentTime = LocalTime.of(start.getHour(), 0,0,0);
        supermarket.addProductsFirstTime(start, productsGeneration.Generate());

        Random random = new Random();
        int maxPeriod = 4;
        supermarket.open(start);
        while (currentTime.getHour() < end.getHour()) {
            if (supermarket.isAllCustomersServed() && customers.size() == 0) {
                customers.addAll(customersGeneration.Generate());
            }
            if (supermarket.isBoughtAllProducts()) {
                supermarket.addProducts(productsGeneration.Generate());
            }
            if (customers.size() != 0) {
                int customerIndex = random.nextInt(customers.size());
                Customer customer = customers.get(customerIndex);
                customers.remove(customerIndex);

                currentTime = currentTime.plusMinutes(random.nextInt(maxPeriod));
                if (random.nextBoolean()) {
                    supermarket.acceptNewCustomer(customer, currentTime);
                }
            }

            currentTime = currentTime.plusMinutes(random.nextInt(maxPeriod));
            if (random.nextBoolean()) {
                supermarket.someCustomerHasAddedProductToBasket(currentTime);
            }

            currentTime = currentTime.plusMinutes(random.nextInt(maxPeriod));
            if (random.nextBoolean()) {
                supermarket.someCustomerGoToCashDesk(currentTime);
            }

            currentTime = currentTime.plusMinutes(random.nextInt(maxPeriod));
            if (random.nextBoolean()) {
                supermarket.serveSomeCustomer(currentTime);
            }
        }
        supermarket.close(currentTime);
    }
}
