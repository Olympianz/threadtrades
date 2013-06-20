package com.hm.service.generator;

import com.hm.model.Bid;
import com.hm.model.Broker;
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
public class BidGenerator implements Runnable{

    // TODO: Use dependency injection?
    TradeService tradeService = new TradeService();

    @Override
    public void run() {
        Random random = new Random();

        Broker buyer = TestTradeService.buyers[random.nextInt(TestTradeService.buyers.length)];
        Stock stock = TestTradeService.stocks[random.nextInt(TestTradeService.stocks.length)];
        int generatedPrice = random.nextInt(100);

        Bid bid = new Bid();

        bid.setBuyer(buyer);
        bid.setStock(stock);
        bid.setPrice(generatedPrice);

        tradeService.setBid(bid);
    }
}
