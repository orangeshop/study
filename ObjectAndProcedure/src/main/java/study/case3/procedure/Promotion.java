package study.case3.procedure;

public class Promotion {

    public enum ConditionType {
        PRICE, QUANTITY
    }

    private Long cartId;
    private Long basePrice;
    private int baseQuantity;

    private ConditionType conditionType;

    public ConditionType getConditionType() {
        return conditionType;
    }

    public int getBaseQuantity() {
        return baseQuantity;
    }

    public Long getBasePrice() {
        return basePrice;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
}
