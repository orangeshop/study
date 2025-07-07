package study.case3.object;

public class PriceCondition implements DiscountCondition {
    private Long basePrice;

    @Override
    public boolean isApplicableTo(Cart cart) {
        return cart.getTotalPrice() >= basePrice;
    }
}
