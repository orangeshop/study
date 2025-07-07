package study.case1.procedure;

public class PromotionProcess {
    public void apply(Promotion promotion, Cart cart) {
        if (isApplicableTo(promotion, cart)){
            promotion.setCartId(cart.getId());
        }
    }

    private boolean isApplicableTo(Promotion promotion, Cart cart) {
        return cart.getTotalPrice() >= promotion.getBasePrice();
    }
}
