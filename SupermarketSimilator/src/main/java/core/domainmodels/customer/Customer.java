package core.domainmodels.customer;

import core.domainmodels.supermarket.product.Product;
import core.types.CustomerType;
import core.types.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

public class Customer {
    private final Random random = new Random();
    private final Basket basket;
    private final String name;
    private final CustomerType type;
    private BigDecimal moneyQuantity;
    private final PaymentMethod paymentMethod;

    public Customer(
            Basket basket,
            String name,
            CustomerType customerType,
            BigDecimal moneyQuantity,
            PaymentMethod paymentMethod
            )
    {
        this.basket = basket;
        this.name = name;
        this.type = customerType;
        this.moneyQuantity = moneyQuantity;
        this.paymentMethod = paymentMethod;
    }

    public void putToBasket(LocalTime localTime, Product product) {
        int randomInt = product.getUnits() + 1;
        randomInt = randomInt < 0 ? 0 : randomInt;
        Product takenProduct = product.take(random.nextInt(randomInt));
        if (takenProduct.getUnits() != 0) {
            basket.addProduct(takenProduct);
            System.out.println(localTime + " Customer " + name + " picked up " + takenProduct.getTookInfo());
        }
    }

    public String getName() {
        return name;
    }

    public boolean isEnoughMoney(BigDecimal price) {
        return moneyQuantity.compareTo(price) >= 0;
    }

    public void pay(BigDecimal price) {
        moneyQuantity = moneyQuantity.subtract(price);
    }

    public List<Product> getProductsFromBasket() {
        return basket.getProducts();
    }

    public CustomerType getCustomerType() {
        return type;
    }

    public PaymentMethod getPaymentMethod() { return paymentMethod; }
}
