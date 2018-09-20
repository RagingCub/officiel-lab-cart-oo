package ca.ulaval.glo4002.cart.interfaces.rest;

import ca.ulaval.glo4002.cart.application.cart.CartApplicationService;
import ca.ulaval.glo4002.cart.application.shop.ShopApplicationService;
import ca.ulaval.glo4002.cart.domain.cart.Cart;
import ca.ulaval.glo4002.cart.domain.shop.ShopItem;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/clients/{" + CartResource.EMAIL_PARAMETER + "}/cart")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartResource {
    public static final String EMAIL_PARAMETER = "email";
    public static final String SKU_PARAMETER = "sku";

    private CartApplicationService cartService;
    private ShopApplicationService shopService;

    public CartResource() {
        this.cartService = new CartApplicationService(PersistenceProvider.getCartRepository());
        this.shopService = new ShopApplicationService(PersistenceProvider.getShopRepository());
    }

    @GET
    public Cart getCart(@PathParam(EMAIL_PARAMETER) String email) {
        return this.cartService.findOrCreateCartForClient(email);
    }

    @PUT
    @Path("/{" + SKU_PARAMETER + "}")
    public Response addItemToCart(@PathParam(EMAIL_PARAMETER) String email, @PathParam(SKU_PARAMETER) String sku) {
        // TODO this resource does too much
        ShopItem shopItem = this.shopService.findItemBySku(sku);
        this.cartService.addItemToCart(email, shopItem);
        return Response.ok().build();
    }
}