package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class QuoteService {

    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

    private QuoteDao quoteDao;
    private MarketDataDao marketDataDao;

    @Autowired
    public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao){
        this.quoteDao = quoteDao;
        this.marketDataDao = marketDataDao;
    }

    public void updateMarketData(){
        List<Quote> quotes = (List<Quote>) quoteDao.findAll();
        List<IexQuote> iexQuotes = new ArrayList<>();
        List<Quote> updatedQuotes = new ArrayList<>();

        for (Quote quote : quotes){
            iexQuotes.add(findIexQuoteByTicker(quote.getId()));
        }

        for (IexQuote iexQuote : iexQuotes){
            updatedQuotes.add(buildQuoteFromIexQuote(iexQuote));
        }
    }

    protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote){
        Quote newQuote = new Quote();
        newQuote.setTicker(iexQuote.getSymbol());
        newQuote.setBidSize(iexQuote.getIexBidSize());
        newQuote.setBidPrice(iexQuote.getIexBidPrice());
        newQuote.setAskSize(iexQuote.getIexAskSize());
        newQuote.setAskPrice(iexQuote.getIexAskPrice());
        return newQuote;
    }

    public List<Quote> saveQuotes(List<String> tickers){

        List<IexQuote> iexQuotes = new ArrayList<>();
        List<Quote> quotes = new ArrayList<>();

        for (String ticker : tickers){
            iexQuotes.add(findIexQuoteByTicker(ticker));
        }

        for (IexQuote iexQuote : iexQuotes){
            quotes.add(buildQuoteFromIexQuote(iexQuote));
        }

        for (Quote quote : quotes){
            saveQuote(quote.getId());
        }

        return quotes;
    }

    public Quote saveQuote(String ticker){

        IexQuote iexQuote = findIexQuoteByTicker(ticker);
        Quote quote = buildQuoteFromIexQuote(iexQuote);
        Quote savedQuote = quoteDao.save(quote);

        return savedQuote;

    }

    public List<Quote> findIexQuoteByTicker(){
        List<Quote> quotes = (List<Quote>) quoteDao.findAll();

        return quotes;
    }

    public IexQuote findIexQuoteByTicker(String ticker){
        return marketDataDao.findById(ticker).orElseThrow(() -> new IllegalArgumentException(ticker +" is invalid"));
    }

    public Quote saveQuote(Quote quote){
        return quoteDao.save(quote);
    }

    public List<Quote> findAllQuotes(){
        return (List<Quote>) quoteDao.findAll();
    }
}
