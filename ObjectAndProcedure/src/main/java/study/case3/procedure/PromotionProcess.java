package study.case3.procedure;

public class PromotionProcess {
    public void apply(Promotion promotion, Cart cart) {
        if (isApplicableTo(promotion, cart)){
            promotion.setCartId(cart.getId());
        }
    }

    private boolean isApplicableTo(Promotion promotion, Cart cart) {
        switch (promotion.getConditionType()){
            case PRICE:
                return cart.getTotalPrice() >= promotion.getBasePrice();
            case QUANTITY:
                return cart.getTotalQuantity() >= promotion.getBaseQuantity();
        }

        return false;
    }
}
