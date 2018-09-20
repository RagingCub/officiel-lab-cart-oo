package ca.ulaval.glo4002.cart.domain.shop;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({StandardShopItem.class, PrimeShopItem.class, FragileShopItem.class})
public abstract class ShopItem {
    public abstract String getName();

    public abstract boolean isAvailable();

    public abstract boolean hasSku(String sku);

    public abstract int getPrice();

    public abstract int getShipping();
}
