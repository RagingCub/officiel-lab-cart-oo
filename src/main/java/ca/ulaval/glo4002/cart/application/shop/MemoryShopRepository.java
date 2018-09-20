package ca.ulaval.glo4002.cart.application.shop;

import ca.ulaval.glo4002.cart.domain.shop.ShopItem;

import java.util.ArrayList;
import java.util.List;

public class MemoryShopRepository implements ShopRepository {

    private List<ShopItem> items;

    public MemoryShopRepository() {
        this.items = new ArrayList<>();
    }

    @Override
    public List<ShopItem> readShopFromFile() {
        return items;
    }

    @Override
    public void persistShop(List<ShopItem> items) {
        this.items = items;
    }
}
