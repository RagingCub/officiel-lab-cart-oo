package ca.ulaval.glo4002.cart.application.cart;

import ca.ulaval.glo4002.cart.domain.cart.Cart;

import java.util.List;

public interface CartRepository {
    List<Cart> retrieveCarts();

    void persistCarts(List<Cart> carts);
}
