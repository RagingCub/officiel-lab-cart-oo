package ca.ulaval.glo4002.cart.application.shop;

import ca.ulaval.glo4002.cart.domain.shop.ShopItem;

import java.util.List;
import java.util.stream.Collectors;

public class ShopApplicationService {
    private final ShopRepository shopRepository;

    public ShopApplicationService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public List<ShopItem> listAvailableItems() {
        List<ShopItem> items = this.shopRepository.readShopFromFile();

        return items.stream().filter(ShopItem::isAvailable).collect(Collectors.toList());
    }

    public ShopItem findItemBySku(String sku) {
        return this.listAvailableItems().stream()
                .filter(x -> x.hasSku(sku))
                .findFirst()
                .orElseThrow(ItemNotFoundException::new);
    }

    private void addItem(ShopItem item) {
        List<ShopItem> items = this.shopRepository.readShopFromFile();
        items.add(item);

        this.shopRepository.persistShop(items);
    }
}
