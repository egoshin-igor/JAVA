package infrastructure.Generators;

import application.interfaces.IGeneration;
import core.domainmodels.customer.Basket;
import core.domainmodels.customer.Customer;
import core.types.CustomerType;
import core.types.PaymentMethod;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class CustomerGeneration implements IGeneration<Customer> {
    @Override
    public List<Customer> Generate() {
        List<Customer> customers = new LinkedList<>();
        customers.add(new Customer(new Basket(), "Ivan",
                CustomerType.Child, BigDecimal.valueOf(20000), PaymentMethod.Card));
        customers.add(new Customer(new Basket(), "Egor",
                CustomerType.Retired, BigDecimal.valueOf(30000), PaymentMethod.HardCash));
        customers.add(new Customer(new Basket(), "Boris",
                CustomerType.Adult, BigDecimal.valueOf(10000), PaymentMethod.Card));
        customers.add(new Customer(new Basket(), "Nadia",
                CustomerType.Retired, BigDecimal.valueOf(5000), PaymentMethod.HardCash));
        customers.add(new Customer(new Basket(), "Katya",
                CustomerType.Adult, BigDecimal.valueOf(1000), PaymentMethod.Bonus));
        customers.add(new Customer(new Basket(), "Olga",
                CustomerType.Adult, BigDecimal.valueOf(900), PaymentMethod.Bonus));

        return customers;
    }
}
