package core.domainmodels.customer;

import core.domainmodels.supermarket.product.Product;
import core.types.CustomerType;
import core.types.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

public class Customer {
    private final Random _random = new Random();
    private final Basket _basket;
    private final String _name;
    private final CustomerType _type;
    private BigDecimal _moneyQuantity;
    private final PaymentMethod _paymentMethod;

    public Customer(
            Basket basket,
            String name,
            CustomerType customerType,
            BigDecimal moneyQuantity,
            PaymentMethod paymentMethod
            )
    {
        _basket =basket;
        _name = name;
        _type = customerType;
        _moneyQuantity = moneyQuantity;
        _paymentMethod = paymentMethod;
    }

    public void putToBasket(LocalTime localTime, Product product) {
        int randomInt = product.getUnits() + 1;
        randomInt = randomInt < 0 ? 0 : randomInt;
        Product takenProduct = product.take(_random.nextInt(randomInt));
        if (takenProduct.getUnits() != 0) {
            _basket.addProduct(takenProduct);
            System.out.println(localTime + " Customer " + _name + " picked up " + takenProduct.getTookInfo());
        }
    }

    public String getName() {
        return _name;
    }

    public boolean isEnoughMoney(BigDecimal price) {
        return _moneyQuantity.compareTo(price) >= 0;
    }

    public void pay(BigDecimal price) {
        _moneyQuantity = _moneyQuantity.subtract(price);
    }

    public List<Product> getProductsFromBasket() {
        return _basket.getProducts();
    }

    public CustomerType getCustomerType() {
        return _type;
    }

    public PaymentMethod getPaymentMethod() { return _paymentMethod; }
}
