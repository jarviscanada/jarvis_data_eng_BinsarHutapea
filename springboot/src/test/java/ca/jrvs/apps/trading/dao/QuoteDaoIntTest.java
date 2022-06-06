package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteDaoIntTest {

    @Autowired
    private QuoteDao quoteDao;

    private Quote savedQuote;

    @Before
    public void insertOne(){

        savedQuote = new Quote();

        savedQuote.setAskPrice(10d);
        savedQuote.setAskSize(10);
        savedQuote.setBidPrice(10.2d);
        savedQuote.setBidSize(10);
        savedQuote.setId("aapl");
        savedQuote.setLastPrice(10.1d);
        quoteDao.save(savedQuote);
    }

    @Test
    public void saveAll(){

        List<Quote> quotes = new ArrayList<>();

        Quote firstQuote = new Quote();
        firstQuote.setId("one");
        firstQuote.setAskPrice(10d);
        firstQuote.setAskSize(10);
        firstQuote.setBidPrice(10.2d);
        firstQuote.setBidSize(10);
        firstQuote.setLastPrice(10.1d);
        quotes.add(firstQuote);

        Quote secondQuote = new Quote();
        secondQuote.setId("two");
        secondQuote.setAskPrice(10d);
        secondQuote.setAskSize(10);
        secondQuote.setBidPrice(10.2d);
        secondQuote.setBidSize(10);
        secondQuote.setLastPrice(10.1d);
        quotes.add(secondQuote);

        List<Quote> expectedQuotes = (List<Quote>) quoteDao.saveAll(quotes);
        assertEquals(expectedQuotes, quotes);
    }

    @Test
    public void existsById(){
        assertTrue(quoteDao.existsById(savedQuote.getId()));
    }

    @After
    public void deleteOne(){
        quoteDao.deleteById(savedQuote.getId());
    }
}
