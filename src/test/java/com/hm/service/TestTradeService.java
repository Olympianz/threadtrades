package com.hm.service;

import com.hm.model.Broker;
import com.hm.model.Stock;
import org.junit.Before;
import service.TradeService;

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
    private void setUp(){
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

//    private Bid generateBid(){
//
//        Random random = new Random();
//
//        Broker buyer = buyers[random.nextInt(buyersLength)];
//        Stock stock = stocks[random.nextInt(stocksLength)];
//        int generatedPrice = random.nextInt(100);
//
//        Bid bid = new Bid();
//
//        bid.setBuyer(buyer);
//        bid.setStock(stock);
//        bid.setPrice(generatedPrice);
//
//        return bid;
//    }
//
//    private Offer generateOffer(){
//        Random random = new Random();
//
//        Broker seller = buyers[random.nextInt(sellersLength)];
//        Stock stock = stocks[random.nextInt(stocksLength)];
//        int generatedPrice = random.nextInt(100);
//
//        Offer offer = new Offer();
//
//        offer.setSeller(seller);
//        offer.setStock(stock);
//        offer.setPrice(generatedPrice);
//
//        return offer;
//
//    }
}
