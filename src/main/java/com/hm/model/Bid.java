package com.hm.model;

/**
 * Created with IntelliJ IDEA.
 * User: Hamed
 * Date: 5/19/13
 * Time: 7:27 PM
 */
public class Bid {

    private Broker buyer;

    private Stock stock;
    private long price;

    public Broker getBuyer() {
        return buyer;
    }

    public void setBuyer(Broker buyer) {
        this.buyer = buyer;
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
