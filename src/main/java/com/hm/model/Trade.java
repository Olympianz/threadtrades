package com.hm.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Hamed
 * Date: 5/19/13
 * Time: 7:02 PM
 */
public class Trade {

    private Stock stock;

//    private int shares;

    private long price;

    private Broker seller;

    private Broker buyer;

    private Date tradeDate;


    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

//    public int getShares() {
//        return shares;
//    }
//
//    public void setShares(int shares) {
//        this.shares = shares;
//    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Broker getSeller() {
        return seller;
    }

    public void setSeller(Broker seller) {
        this.seller = seller;
    }

    public Broker getBuyer() {
        return buyer;
    }

    public void setBuyer(Broker buyer) {
        this.buyer = buyer;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trade trade = (Trade) o;

        if (price != trade.price) return false;
        if (buyer != null ? !buyer.equals(trade.buyer) : trade.buyer != null) return false;
        if (seller != null ? !seller.equals(trade.seller) : trade.seller != null) return false;
        if (stock != null ? !stock.equals(trade.stock) : trade.stock != null) return false;
        if (tradeDate != null ? !tradeDate.equals(trade.tradeDate) : trade.tradeDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stock != null ? stock.hashCode() : 0;
        result = 31 * result + (int) (price ^ (price >>> 32));
        result = 31 * result + (seller != null ? seller.hashCode() : 0);
        result = 31 * result + (buyer != null ? buyer.hashCode() : 0);
        result = 31 * result + (tradeDate != null ? tradeDate.hashCode() : 0);
        return result;
    }
}
