package study.case2.object;

public class Promotion {
    private Cart cart;
    private Long minPrice;
    private Long maxPrice;

    public void apply(Cart cart) {
        if(cart.getTotalPrice() >= this.minPrice &&
            cart.getTotalPrice() <= this.maxPrice) {
            this.cart = cart;
        }
    }
}
