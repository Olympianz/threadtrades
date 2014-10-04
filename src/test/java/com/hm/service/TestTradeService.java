package com.hm.service;

import com.hm.model.Broker;
import com.hm.model.Stock;
import org.junit.Before;
import org.junit.Test;
import service.TradeService;
import com.hm.model.*;

import static org.junit.Assert.assertTrue;


/**
 * Created with IntelliJ IDEA.
 * User: Hamed
 * Date: 5/19/13
 * Time: 7:13 PM
 */
public class TestTradeService {

    public static Stock[] stocks = new Stock[2];
    public static Broker[] buyers = new Broker[2];
    public static Broker[] sellers = new Broker[2];

    TradeService service = new TradeService();

    @Before
    public void setUp(){
        Stock googleStock = new Stock();
        googleStock.setCode("Goog");

        Stock amazonStock = new Stock();
        amazonStock.setCode("AMZ");

        stocks[0] = googleStock;
        stocks[1] = amazonStock;

        Broker buyer = new Broker();
        buyer.setName("J P Morgan");

        Broker buyerTwo = new Broker();
        buyerTwo.setName("Morgan Stanley");

        buyers[0] = buyer;
        buyers[1] = buyerTwo;

        Broker seller = new Broker();
        seller.setName("Goldman Sachs");

        Broker sellerTwo = new Broker();
        sellerTwo.setName("Barclays");

        sellers[0] = seller;
        sellers[1] = sellerTwo;

        service.setStocks(stocks);
        service.setSellers(sellers);
        service.setBuyers(buyers);

    }

    //TODO: Bids and Offers should get generated

    @Test
    public void testMatches() {

        Offer offer = new Offer();
        offer.setSeller(sellers[0]);
        offer.setPrice(100);
        offer.setStock(stocks[0]);

        Bid bid = new Bid();
        bid.setBuyer(buyers[0]);
        bid.setPrice(100);
        bid.setStock(stocks[0]);

        assertTrue(service.matches(bid, offer));

    }
}
