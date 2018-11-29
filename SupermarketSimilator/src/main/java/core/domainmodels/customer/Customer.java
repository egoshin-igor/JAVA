package core.domainmodels.customer;

import core.domainmodels.supermarket.product.Product;
import core.types.CustomerType;
import core.types.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public class Customer {
    private final Random _random = new Random();
    private Basket _basket;
    private String _name;
    private CustomerType _type;
    private BigDecimal _moneyQuantity;
    private PaymentMethod _paymentMethod;

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

    public void putToBasket(Product product) {
        Product takenProduct = product.take(_random.nextInt(product.getUnits()) + 1);
        if (takenProduct.getUnits() != 0) {
            _basket.addProduct(takenProduct);
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
