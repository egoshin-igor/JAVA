package core.domainmodels.supermarket.product;

import java.math.BigDecimal;

public class Discount {
    private static final double MAX_DISCOUNT = 90;

    private double discountKf;

    public Discount(double discount) {

        if (discount > MAX_DISCOUNT)
            throw new IllegalArgumentException("Discount can not be more then max discount value");

        discountKf = discount / 100;
    }

    public BigDecimal getPriceWithDiscount(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Price must be more then zero");

        return price.subtract(price.multiply(BigDecimal.valueOf(discountKf)));
    }
}
