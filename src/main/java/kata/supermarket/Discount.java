package kata.supermarket;

import java.math.BigDecimal;

/**
 * Discount interface. Represent discount type such as "two for one", "get two and take one free" etc.
 *
 */
public interface Discount {

    /**
     * Unique identifier of the discount type. Usually two or more discounts of the same type
     * cannot be applied to the same basket.
     * @return Unique discount identifier
     */
    int getId();

    /**
     * Initializes the discount aggregator.
     */
    void init();

    /**
     * Applies an item to the discount
     * (passes the item through the calculation procedure).
     * The discount may ignore the item or take it into account.
     * @param item An item to apply the discount on.
     */
    void apply(Item item);

    /**
     * Returns the calculated discount
     * @return The calculated discount
     */
    BigDecimal getDiscount();

}
