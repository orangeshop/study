package study.case2.procedure;

public class Promotion {
    private Long cartId;
    private Long minPrice;
    private Long maxPrice;

    public Long getMinPrice() {
        return minPrice;
    }

    public Long getMaxPrice() {
        return maxPrice;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
}
