package infrastructure.Generators;

import application.interfaces.IGeneration;
import core.domainmodels.supermarket.product.Discount;
import core.domainmodels.supermarket.product.Product;
import core.types.UnitType;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class ProductGeneration implements IGeneration<Product> {

    @Override
    public List<Product> Generate() {
        List<Product> products = new LinkedList<>();
        products.add(new Product("Lays", BigDecimal.valueOf(111),
                false, new Discount(20), 22, UnitType.Quantity));
        products.add(new Product("Lays", BigDecimal.valueOf(111),
                false, new Discount(20), 22, UnitType.Quantity));
        products.add(new Product("Milk", BigDecimal.valueOf(49),
                false, new Discount(1), 222, UnitType.Quantity));
        products.add(new Product("Bread", BigDecimal.valueOf(19),
                false, new Discount(0), 88, UnitType.Quantity));
        products.add(new Product("Potato", BigDecimal.valueOf(33),
                false, new Discount(11), 800, UnitType.Weight));
        products.add(new Product("Bear", BigDecimal.valueOf(150),
                true, new Discount(10), 220, UnitType.Quantity));
        products.add(new Product("Vodka", BigDecimal.valueOf(300),
                true, new Discount(0), 44, UnitType.Quantity));

        return products;
    }
}
