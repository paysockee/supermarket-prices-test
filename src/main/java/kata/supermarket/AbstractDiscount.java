package kata.supermarket;

import java.math.BigDecimal;

public abstract class AbstractDiscount implements Discount {

    private final int id;

    private BigDecimal discount = BigDecimal.ZERO;

    AbstractDiscount(int id) {
        this.id = id;
    }

    void addDiscount(BigDecimal discount) {
        this.discount = this.discount.add(discount);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public void init() {
        discount = BigDecimal.ZERO;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public BigDecimal getDiscount() {
        return discount;
    }
}
