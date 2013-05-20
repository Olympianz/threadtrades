package com.hm.model;

/**
 * Created with IntelliJ IDEA.
 * User: Hamed
 * Date: 5/19/13
 * Time: 7:28 PM
 */
public class Offer {

    private Broker seller;
    private Stock stock;
    private long price;

    public Broker getSeller() {
        return seller;
    }

    public void setSeller(Broker seller) {
        this.seller = seller;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
