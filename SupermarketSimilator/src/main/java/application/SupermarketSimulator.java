package application;

import application.interfaces.IGeneration;
import application.interfaces.ISupermarketSimulator;
import core.domainmodels.customer.Bill;
import core.domainmodels.customer.Customer;
import core.domainmodels.supermarket.CashDesk;
import core.domainmodels.supermarket.Report;
import core.domainmodels.supermarket.Supermarket;
import core.domainmodels.supermarket.product.Product;

import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class SupermarketSimulator implements ISupermarketSimulator {
    private List<Customer> _customers;
    private IGeneration<Customer> _customersGeneration;
    private IGeneration<Product> _productsGeneration;
    private Supermarket _supermarket;

    SupermarketSimulator(
            IGeneration<Customer> customersGeneration,
            IGeneration<Product> productsGeneration,
            Supermarket supermarket
    ) {
        _customers = customersGeneration.Generate();
        _customersGeneration = customersGeneration;
        _productsGeneration = productsGeneration;
        _supermarket = supermarket;;
    }

    public void SimulateWork() {
        final LocalTime start = LocalTime.of(8, 0,0,0);
        final LocalTime end = LocalTime.of(23, 0,0,0);
        LocalTime currentTime = LocalTime.of(start.getHour(), 0,0,0);
        _supermarket.addProductsFirstTime(start, _productsGeneration.Generate());

        Random random = new Random();
        int maxPeriod = 4;
        _supermarket.open(start);
        while (currentTime.getHour() < end.getHour()) {
            if (_supermarket.isAllCustomersServed() && _customers.size() == 0) {
                _customers.addAll(_customersGeneration.Generate());
            }
            if (_supermarket.isBoughtAllProducts()) {
                _supermarket.addProducts(_productsGeneration.Generate());
            }
            if (_customers.size() != 0) {
                int customerIndex = random.nextInt(_customers.size());
                Customer customer = _customers.get(customerIndex);
                _customers.remove(customerIndex);

                currentTime = currentTime.plusMinutes(random.nextInt(maxPeriod));
                if (random.nextBoolean()) {
                    _supermarket.acceptNewCustomer(customer, currentTime);
                }
            }

            currentTime = currentTime.plusMinutes(random.nextInt(maxPeriod));
            if (random.nextBoolean()) {
                _supermarket.someCustomerHasAddedProductToBasket(currentTime);
            }

            currentTime = currentTime.plusMinutes(random.nextInt(maxPeriod));
            if (random.nextBoolean()) {
                _supermarket.someCustomerGoToCashDesk(currentTime);
            }

            currentTime = currentTime.plusMinutes(random.nextInt(maxPeriod));
            if (random.nextBoolean()) {
                _supermarket.serveSomeCustomer(currentTime);
            }
        }
        _supermarket.close(currentTime);
    }
}
