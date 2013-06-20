package com.hm.service.generator;

import com.hm.model.Broker;
import com.hm.model.Offer;
import com.hm.model.Stock;
import com.hm.service.TestTradeService;
import service.TradeService;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Hamed
 * Date: 6/19/13
 * Time: 2:39 AM
 */
public class OfferGenerator implements Runnable {

    // TODO: Use dependency injection?
    TradeService tradeService = new TradeService();

    @Override
    public void run() {

        Random random = new Random();

        Broker seller = TestTradeService.sellers[random.nextInt(TestTradeService.sellers.length)];
        Stock stock = TestTradeService.stocks[random.nextInt(TestTradeService.stocks.length)];
        int generatedPrice = random.nextInt(100);

        Offer offer = new Offer();

        offer.setSeller(seller);
        offer.setStock(stock);
        offer.setPrice(generatedPrice);

        tradeService.setOffer(offer);
    }
}
