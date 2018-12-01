package core.domainmodels.supermarket.product;

import core.types.CustomerType;
import core.types.UnitType;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
    private final String name;
    private final BigDecimal price;
    private final boolean isHaveAgeRestriction;
    private final Discount discount;
    private int units;
    private final UnitType unitType;

    public Product(
            String name,
            BigDecimal price,
            boolean isHaveAgeRestriction,
            Discount discount,
            int units,
            UnitType unitType
    ) {
        this.name = name;
        this.price = price;
        this.isHaveAgeRestriction = isHaveAgeRestriction;
        this.discount = discount;
        this.units = units;
        this.unitType = unitType;
    }

    public String getName() {
        return name;
    }

    public int getUnits() {
        return units;
    }

    public String getTookInfo() {
        return units + " units of " + name + " type = " + unitType.toString() ;
    }

    public String getSoldInfo() {
        return  name + ", price: " + price.setScale(0, RoundingMode.HALF_UP)
                + ", units: " + units + ", type = " + unitType.toString();
    }

    public Product take(int units) {
        if (units > this.units) {
            units = this.units;
        }

        this.units -= units;
        return new Product(name, price, isHaveAgeRestriction, discount, units, unitType);
    }

    public void put(int units) {
        if (units < 0)
            throw new IllegalAccessError("Impossible put negative count of products");

        this.units += units;
    }

    public BigDecimal getPrice(CustomerType customerType) {
        final double discountForRetiredKf = 0.05;

        BigDecimal price = discount.getPriceWithDiscount(this.price);
        if (customerType == CustomerType.Retired) {
            price = price.subtract(price.multiply(BigDecimal.valueOf(discountForRetiredKf)));
        }

        return price;
    }

    public BigDecimal getPrice() {
        return getPrice(CustomerType.Adult);
    }

    public boolean isHaveAgeRestriction() {
        return isHaveAgeRestriction;
    }
}
