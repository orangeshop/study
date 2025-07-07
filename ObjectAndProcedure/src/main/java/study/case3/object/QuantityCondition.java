package study.case3.object;

public class QuantityCondition implements DiscountCondition {
    private int baseQuantity;

    @Override
    public boolean isApplicableTo(Cart cart) {
        return cart.getTotalQuantity() >= baseQuantity;
    }
}
