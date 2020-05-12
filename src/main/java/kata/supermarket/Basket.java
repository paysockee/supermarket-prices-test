package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Consumer;

public class Basket {
    private final List<Item> items;
    private final Set<Discount> discounts;

    public Basket() {
        this.items = new ArrayList<>();
        this.discounts = new HashSet<>();
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    public void apply(final Discount discount) {
        this.discounts.add(discount);
    }

    List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal total() {
        return new TotalCalculator().calculate();
    }

    private class TotalCalculator {
        private final List<Item> items;

        TotalCalculator() {
            this.items = items();
        }

        private BigDecimal subtotal() {
            return items.stream().map(Item::price)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        /**
         * TODO: This could be a good place to apply the results of
         *  the discount calculations.
         *  It is not likely to be the best place to do those calculations.
         *  Think about how Basket could interact with something
         *  which provides that functionality.
         */
        private BigDecimal discounts() {
            discounts.forEach(Discount::init);

            discounts.stream().<Consumer<? super Item>>map(d -> d::apply).forEach(items::forEach);

            return discounts.stream().map(Discount::getDiscount).reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        private BigDecimal calculate() {
            return subtotal().subtract(discounts());
        }
    }
}
