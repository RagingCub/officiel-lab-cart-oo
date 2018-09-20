package ca.ulaval.glo4002.cart.interfaces.rest;

import ca.ulaval.glo4002.cart.application.shop.ShopApplicationService;
import ca.ulaval.glo4002.cart.domain.shop.ShopItem;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/shop")
public class ShopResource {

    private ShopApplicationService shopService;

    public ShopResource() {
        this.shopService = new ShopApplicationService(PersistenceProvider.getShopRepository());
    }

    @GET
    @Path("/available-items")
    public List<ShopItem> listItems() {
        return this.shopService.listAvailableItems();
    }
}
