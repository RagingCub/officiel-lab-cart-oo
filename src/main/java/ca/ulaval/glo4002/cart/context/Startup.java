package ca.ulaval.glo4002.cart.context;

import ca.ulaval.glo4002.cart.application.cart.CartApplicationService;
import ca.ulaval.glo4002.cart.application.shop.ShopApplicationService;
import ca.ulaval.glo4002.cart.application.shop.ShopRepository;
import ca.ulaval.glo4002.cart.domain.shop.PrimeShopItem;
import ca.ulaval.glo4002.cart.domain.shop.ShopItem;
import ca.ulaval.glo4002.cart.domain.shop.StandardShopItem;
import ca.ulaval.glo4002.cart.interfaces.rest.CartResource;
import ca.ulaval.glo4002.cart.interfaces.rest.PersistenceProvider;
import ca.ulaval.glo4002.cart.interfaces.rest.ShopResource;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Startup {
    public static Object[] getInstances() {
        List<Object> instances = new ArrayList<>();
        instances.add(getCartResourceInstance());
        instances.add(getShopResourceInstance());
        return instances.toArray();
    }

    private static CartResource getCartResourceInstance() {
        CartApplicationService cartService = new CartApplicationService(PersistenceProvider.getCartRepository());
        ShopApplicationService shopService = new ShopApplicationService(PersistenceProvider.getShopRepository());
        return new CartResource(cartService, shopService);
    }

    private static ShopResource getShopResourceInstance() {
        ShopRepository shopRepository = PersistenceProvider.getShopRepository();

        if (System.getProperty("mode") != null && System.getProperty("mode").equalsIgnoreCase("demo")) {
            Logger.getGlobal().info("Prefilling data in the shop for the demo");
            List<ShopItem> items = shopRepository.readShopFromFile();
            items.add(new StandardShopItem("1251521", "Peanuts", 5, 1, 1.20, true));
            items.add(new PrimeShopItem("236637", "Clean Code", 35, 2, 0.50, false));
            items.add(new StandardShopItem("235265", "Détendeur Mares Abyss Navy 22", 999, 5, 0.15, true));
            items.add(new StandardShopItem("276101", "Imprimante 3D", 2341, 31, 0.60, true));
            items.add(new PrimeShopItem("818113", "GoPro", 650, 1, 4.60, true));
            items.add(new StandardShopItem("51-153", "Peinture à numéro", 1, 2, 1.40, true));
            shopRepository.persistShop(items);
        }

        return new ShopResource(new ShopApplicationService(shopRepository));
    }
}
