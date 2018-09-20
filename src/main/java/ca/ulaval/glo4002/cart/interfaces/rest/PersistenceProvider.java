package ca.ulaval.glo4002.cart.interfaces.rest;

import ca.ulaval.glo4002.cart.application.cart.CartRepository;
import ca.ulaval.glo4002.cart.application.cart.MemoryCartRepository;
import ca.ulaval.glo4002.cart.application.cart.XmlCartRepository;
import ca.ulaval.glo4002.cart.application.shop.MemoryShopRepository;
import ca.ulaval.glo4002.cart.application.shop.ShopRepository;
import ca.ulaval.glo4002.cart.application.shop.XmlShopRepository;

public class PersistenceProvider {
    public static CartRepository getCartRepository() {
        if (System.getProperty("store").equalsIgnoreCase("xml")) {
            return new XmlCartRepository();
        } else {
            return new MemoryCartRepository();
        }
    }

    public static ShopRepository getShopRepository() {
        if (System.getProperty("store").equalsIgnoreCase("xml")) {
            return new XmlShopRepository();
        } else {
            return new MemoryShopRepository();
        }
    }
}
