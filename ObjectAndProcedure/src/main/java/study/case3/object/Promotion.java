package study.case3.object;

public class Promotion {
    private Cart cart;
    private DiscountCondition discountCondition;

    public void apply(Cart cart) {
        if(discountCondition.isApplicableTo(cart)) {
            this.cart = cart;
        }
    }
}
