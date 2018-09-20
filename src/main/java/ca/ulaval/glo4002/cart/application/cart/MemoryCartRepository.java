package ca.ulaval.glo4002.cart.application.cart;

import ca.ulaval.glo4002.cart.domain.cart.Cart;

import java.util.ArrayList;
import java.util.List;

public class MemoryCartRepository implements CartRepository {

    private List<Cart> carts;

    public MemoryCartRepository() {
        this.carts = new ArrayList<>();
    }

    @Override
    public List<Cart> retrieveCarts() {
        return this.carts;
    }

    @Override
    public void persistCarts(List<Cart> carts) {
        this.carts = carts;
    }
}
