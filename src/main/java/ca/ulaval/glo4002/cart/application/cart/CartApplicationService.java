package ca.ulaval.glo4002.cart.application.cart;

import ca.ulaval.glo4002.cart.domain.cart.Cart;
import ca.ulaval.glo4002.cart.domain.cart.CartItem;
import ca.ulaval.glo4002.cart.domain.shop.ShopItem;

import java.util.List;

public class CartApplicationService {

    private CartRepository cartRepository;

    public CartApplicationService() {
        this.cartRepository = new CartRepository();
    }

    public Cart findOrCreateCartForClient(String email) {
        List<Cart> carts = this.cartRepository.retrieveCarts();

        Cart cart = this.getCartByOwner(email, carts);

        return cart;
    }

    public void addItemToCart(String email, ShopItem item) {
        List<Cart> carts = this.cartRepository.retrieveCarts();
        Cart cart = this.getCartByOwner(email, carts);

        cart.addItem(new CartItem(item.getName(), 1, this.getItemPriceWithShipping(item)));

        this.cartRepository.persistCarts(carts);
    }

    private int getItemPriceWithShipping(ShopItem item) {
        int totalPrice = item.getPrice();

        totalPrice += item.getShipping();

        return totalPrice;
    }

    private Cart getCartByOwner(String email, List<Cart> carts) {
        return carts.stream().filter(c -> c.ownerEmail.equals(email)).findFirst().orElseGet(() -> {
            Cart newCart = new Cart(email);
            carts.add(newCart);
            this.cartRepository.persistCarts(carts);
            return newCart;
        });
    }
}
