package ca.ulaval.glo4002.cart.application.shop;

import ca.ulaval.glo4002.cart.domain.shop.PrimeShopItem;
import ca.ulaval.glo4002.cart.domain.shop.ShopItem;
import ca.ulaval.glo4002.cart.domain.shop.StandardShopItem;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ShopApplicationService {
    private final ShopRepository shopRepository;

    public ShopApplicationService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public List<ShopItem> listAvailableItems() {
        List<ShopItem> items = this.shopRepository.readShopFromFile();
        if (items.isEmpty()) {
            Logger.getGlobal().info("Prefilling data in the shop for the demo");
            this.prefillDemoData();
            items = this.shopRepository.readShopFromFile();
        }

        return items.stream().filter(ShopItem::isAvailable).collect(Collectors.toList());
    }

    public ShopItem findItemBySku(String sku) {
        return this.listAvailableItems().stream()
                .filter(x -> x.hasSku(sku))
                .findFirst()
                .orElseThrow(ItemNotFoundException::new);
    }

    private void prefillDemoData() {
        this.addItem(new StandardShopItem("1251521", "Peanuts", 5, 1, 1.20, true));
        this.addItem(new PrimeShopItem("236637", "Clean Code", 35, 2, 0.50, false));
        this.addItem(new StandardShopItem("235265", "Détendeur Mares Abyss Navy 22", 999, 5, 0.15, true));
        this.addItem(new StandardShopItem("276101", "Imprimante 3D", 2341, 31, 0.60, true));
        this.addItem(new PrimeShopItem("818113", "GoPro", 650, 1, 4.60, true));
        this.addItem(new StandardShopItem("51-153", "Peinture à numéro", 1, 2, 1.40, true));
    }

    private void addItem(ShopItem item) {
        List<ShopItem> items = this.shopRepository.readShopFromFile();
        items.add(item);

        this.shopRepository.persistShop(items);
    }
}
