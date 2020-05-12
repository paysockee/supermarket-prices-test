package kata.supermarket;

import java.util.HashSet;
import java.util.Set;

public class TwoForOne extends AbstractDiscount {

    private static final int TWO_FOR_ONE_ID = 1;

    private final Set<String> waitingForSecond = new HashSet<>();

    TwoForOne() {
        super(TWO_FOR_ONE_ID);
    }

    @Override
    public void apply(Item item) {

        if (!(item instanceof ItemByUnit)) {
            return;
        }

        String productName = item.getClass().getSimpleName();

        if (waitingForSecond.contains(productName)) {
            addDiscount(item.price());
            waitingForSecond.remove(productName);
        } else {
            waitingForSecond.add(productName);
        }

    }
}
