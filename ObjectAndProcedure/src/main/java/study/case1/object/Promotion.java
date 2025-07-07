package study.case1.object;

public class Promotion {
    private Cart cart;
    private Long basePrice;

    public void apply(Cart cart) {
        if(cart.getTotalPrice() >= this.basePrice){
            this.cart = cart;
        }
    }
}
