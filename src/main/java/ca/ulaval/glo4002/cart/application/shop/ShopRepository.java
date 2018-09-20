package ca.ulaval.glo4002.cart.application.shop;

import ca.ulaval.glo4002.cart.domain.shop.ShopItem;

import java.util.List;

public interface ShopRepository {
    List<ShopItem> readShopFromFile();

    void persistShop(List<ShopItem> items);
}
