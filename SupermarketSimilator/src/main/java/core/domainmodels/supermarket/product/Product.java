package core.domainmodels.supermarket.product;

import core.types.CustomerType;
import core.types.UnitType;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
    private String _name;
    private BigDecimal _price;
    private boolean _isHaveAgeRestriction;
    private Discount _discount;
    private int _units;
    private UnitType _unitType;

    public Product(
            String name,
            BigDecimal price,
            boolean isHaveAgeRestriction,
            Discount discount,
            int units,
            UnitType unitType
    ) {
        _name = name;
        _price = price;
        _isHaveAgeRestriction = isHaveAgeRestriction;
        _discount = discount;
        _units = units;
        _unitType = unitType;
    }

    public String getName() {
        return _name;
    }

    public int getUnits() {
        return _units;
    }

    public String getTookInfo() {
        return _units + " units of " + _name + " type = " + _unitType.toString() ;
    }

    public String getSoldInfo() {
        return  _name + ", price: " + _price.setScale(0, RoundingMode.HALF_UP)
                + ", units: " + _units + ", type = " + _unitType.toString();
    }

    public Product take(int units) {
        if (units > _units) {
            units = _units;
        }

        _units -= units;
        return new Product(_name, _price, _isHaveAgeRestriction, _discount, units, _unitType);
    }

    public void put(int units) {
        if (units < 0)
            throw new IllegalAccessError("Impossible put negative count of products");

        _units += units;
    }

    public BigDecimal getPrice(CustomerType customerType) {
        final double discountForRetiredKf = 0.05;

        BigDecimal price = _discount.getPriceWithDiscount(_price);
        if (customerType == CustomerType.Retired) {
            price = price.subtract(price.multiply(BigDecimal.valueOf(discountForRetiredKf)));
        }

        return price;
    }

    public BigDecimal getPrice() {
        return getPrice(CustomerType.Adult);
    }

    public boolean isHaveAgeRestriction() {
        return _isHaveAgeRestriction;
    }
}
