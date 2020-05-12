package kata.supermarket;

import java.math.BigDecimal;

public interface Discount {

    int getId();

    void init();

    void apply(Item item);

    BigDecimal getDiscount();

}
