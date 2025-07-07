package study.case3.procedure;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Long id;
    private List<CartLineItem> items = new ArrayList<>();

    public Long getTotalPrice() {
        return items.stream().mapToLong(CartLineItem::getPrice).sum();
    }

    public int getTotalQuantity() {
        return items.stream().mapToInt(CartLineItem::getQuantity).sum();
    }

    public Long getId() {
        return id;
    }

    public class CartLineItem {
        private Long price;
        private Integer quantity;

        public Long getPrice() {
            return price;
        }

        public Integer getQuantity() {
            return quantity;
        }
    }
}
