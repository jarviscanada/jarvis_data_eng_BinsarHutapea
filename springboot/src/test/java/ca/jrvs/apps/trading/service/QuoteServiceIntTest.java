package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteServiceIntTest {

    @Autowired
    private QuoteService quoteService;

    @Autowired
    private QuoteDao quoteDao;

    private Quote testQuote;
    @Before
    public void setup(){
        quoteDao.deleteAll();
        testQuote = new Quote();
        testQuote.setId("AAPL");
        testQuote.setAskPrice(10d);
        testQuote.setAskSize(10);
        testQuote.setBidPrice(10.2d);
        testQuote.setBidSize(10);
        testQuote.setLastPrice(10.1d);
        quoteService.saveQuote(testQuote);
    }

    @Test
    public void findIexQuotesByTicker(){
        String expectedSymbol = "AAPL";
        String actualSymbol = quoteService.findIexQuoteByTicker("AAPL").getSymbol();
        assertEquals(expectedSymbol, actualSymbol);
    }

    @After
    public void deleteAll(){
        quoteDao.deleteAll();
    }
}
