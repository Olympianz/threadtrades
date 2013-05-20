package service;

import com.hm.model.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: Hamed
 * Date: 5/19/13
 * Time: 7:13 PM
 */

//TODO: This is for bulk bids and offers. Another case is bids and offers check for available trade partners at arrival time.
public class TradeService {


    //TODO Data should come from Client.

    //TODO: HardCoded data should move to the test class

    private Stock[] stocks = new Stock[2];
    private Broker[] buyers = new Broker[2];
    private Broker[] sellers = new Broker[2];

    private int buyersLength;
    private int sellersLength;
    private int stocksLength;


    //TODO: Number of comparison iterations is huge compared to insertions. So CopyOnWriteArraySet is used
    private CopyOnWriteArraySet<Trade> matchedTrades = new CopyOnWriteArraySet<Trade>();

    List<Bid> bids = new LinkedList<Bid>();
    List<Offer> offers = new LinkedList<Offer>();

    Lock bidsLock = new ReentrantLock();
    Lock offersLock = new ReentrantLock();

    //TODO: Data should come from the client
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

        buyersLength = buyers.length;
        sellersLength = sellers.length;
        stocksLength = stocks.length;

        tradeMatcher();
    }

    private void tradeMatcher() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long begin = System.currentTimeMillis();
                while ((System.currentTimeMillis() - begin) < 1000){
                    generateBid();
                    generateOffer();
                    try{
                        offersLock.lock();
                        bidsLock.lock();

                        for (int i=0; i<offers.size(); i++){
                            Offer offer = offers.get(i);
                            for(int j=0; j<bids.size();j++){
                                Bid bid = bids.get(j);
                                if(matches(bid, offer)){
                                    Trade trade = createTrade(bid, offer);
                                    deleteBidAndOffer(bid, offer);
                                    // TODO: Should be removed and matchedTrades be used
                                    System.out.println("Seller " + trade.getSeller().getName() + " Buyer " + trade.getBuyer().getName() + " Stock " +
                                            trade.getStock().getCode() + " Price " + trade.getPrice() + " Buyer Price " + bid.getPrice() + " at " + trade.getTradeDate());
                                }
                            }
                        }
                    } finally {
                        offersLock.unlock();
                        bidsLock.unlock();
                    }
                }

            }
        });
        thread.start();

        try {
            thread.join();
            System.out.println("Offers size " + offers.size()) ;
            System.out.println("Bids size " + bids.size());

            //TODO: Replace by a logic to keep data in DB
            for(Offer offer: offers){
                System.out.println("Offer is Seller " + offer.getSeller().getName() + " Price " + offer.getPrice() + " Stock " + offer.getStock().getCode());
            }

            for(Bid bid: bids){
                System.out.println("Bid is Buyer " + bid.getBuyer().getName() + " Price " + bid.getPrice() + " Stock " + bid.getStock().getCode());
            }

            for(Bid bid: bids){

                System.out.println("Bid is Buyer " + bid.getBuyer().getName() + " Price " + bid.getPrice() + " Stock " + bid.getStock().getCode());
            }


        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }


    public void deleteBidAndOffer(Bid bid, Offer offer){
        try{
            offersLock.lock();
            bidsLock.lock();
            offers.remove(offer);
            bids.remove(bid);
        } finally {
            offersLock.unlock();
            bidsLock.unlock();
        }
    }

    public void generateOffer(){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();

                Broker seller = sellers[random.nextInt(sellersLength)];
                Stock stock = stocks[random.nextInt(stocksLength)];
                int generatedPrice = random.nextInt(100);

                Offer offer = new Offer();

                offer.setSeller(seller);
                offer.setStock(stock);
                offer.setPrice(generatedPrice);

                try{
                    offersLock.lock();
                    offers.add(offer);
                } finally {
                    offersLock.unlock();
                }

            }
        });
        thread.start();
    }

    //TODO: Each Buyer should have its own Thread for, otherwise system does not reflect high or low demand and changes in Prices
    public void generateBid(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();

                Broker buyer = buyers[random.nextInt(buyersLength)];
                Stock stock = stocks[random.nextInt(stocksLength)];
                int generatedPrice = random.nextInt(100);

                Bid bid = new Bid();

                bid.setBuyer(buyer);
                bid.setStock(stock);
                bid.setPrice(generatedPrice);

                try{
                    bidsLock.lock();
                    bids.add(bid);
                } finally {
                    bidsLock.unlock();
                }
            }
        });
        thread.start();
    }

    private Trade createTrade(Bid bid, Offer offer) {
        Trade trade = new Trade();

        trade.setBuyer(bid.getBuyer());
        trade.setSeller(offer.getSeller());
        trade.setPrice(offer.getPrice());
        trade.setStock(bid.getStock());
        trade.setTradeDate(new Date());

        //TODO: This should be processed instead of printing out the result
        matchedTrades.add(trade);
        return trade;
    }

    private boolean matches(Bid bid, Offer offer) {

        if (bid.getStock() == offer.getStock() && offer.getPrice() >= bid.getPrice()){
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        TradeService tradeService = new TradeService();
        tradeService.setUp();
    }

}
