package kata.supermarket;

import java.util.HashSet;
import java.util.Set;

/**
 * Two for the price of one discount.
 * Can be applied multiple times for the same product. For example,
 * if the basket contains 4 items of a product, the discount is applied twice.
 */
public class TwoForOne extends AbstractDiscount {

    private static final int TWO_FOR_ONE_ID = 1;

    private final Set<String> waitingForSecond = new HashSet<>();

    TwoForOne() {
        super(TWO_FOR_ONE_ID);
    }

    @Override
    public void apply(Item item) {

        // check if the item is 'by unit'. If not, the discount has no effect on this item.
        if (!(item instanceof ItemByUnit)) {
            return;
        }

        // get the product name from the class name
        // TODO: implement methods on Item and Product to identify
        // the product, so that the discount could match products correctly.
        String productName = item.getClass().getSimpleName();

        // if we already have a product in the basket (this is the second)
        // apply the discount and remove the product
        // otherwise add the product name to the waiting list
        if (waitingForSecond.contains(productName)) {
            addDiscount(item.price());
            waitingForSecond.remove(productName);
        } else {
            waitingForSecond.add(productName);
        }

    }
}
