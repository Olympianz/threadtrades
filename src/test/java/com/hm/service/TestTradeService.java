package com.hm.service;

import com.hm.model.Bid;
import com.hm.model.Broker;
import com.hm.model.Offer;
import com.hm.model.Stock;
import org.junit.Before;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Hamed
 * Date: 5/19/13
 * Time: 7:13 PM
 */
public class TestTradeService {

    private Stock[] stocks = new Stock[1];
    private Broker[] buyers = new Broker[1];
    private Broker[] sellers = new Broker[1];

    private int buyersLength;
    private int sellersLength;
    private int stocksLength;

    @Before
    private void setUp(){
        Stock googleStock = new Stock();
        googleStock.setCode("Goog");

        stocks[0] = googleStock;

        Broker buyer = new Broker();
        buyer.setName("J P Morgan");

        buyers[0] = buyer;

        Broker seller = new Broker();
        seller.setName("Goldman Sachs");

        sellers[0] = seller;

        buyersLength = buyers.length;
        sellersLength = sellers.length;
        stocksLength = stocks.length;
    }

    private Bid generateBid(){

        Random random = new Random();

        Broker buyer = buyers[random.nextInt(buyersLength)];
        Stock stock = stocks[random.nextInt(stocksLength)];
        int generatedPrice = random.nextInt(100);

        Bid bid = new Bid();

        bid.setBuyer(buyer);
        bid.setStock(stock);
        bid.setPrice(generatedPrice);

        return bid;
    }

    private Offer generateOffer(){
        Random random = new Random();

        Broker seller = buyers[random.nextInt(sellersLength)];
        Stock stock = stocks[random.nextInt(stocksLength)];
        int generatedPrice = random.nextInt(100);

        Offer offer = new Offer();

        offer.setSeller(seller);
        offer.setStock(stock);
        offer.setPrice(generatedPrice);

        return offer;

    }


}
