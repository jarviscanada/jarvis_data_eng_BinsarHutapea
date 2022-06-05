package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Test;
import org.junit.Before;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MarketDataDaoIntTest {

    private MarketDataDao dao;

    @Before
    public void init(){
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(50);
        connectionManager.setDefaultMaxPerRoute(50);
        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setHost("https://cloud.iexapis.com/v1");
        marketDataConfig.setToken(System.getenv("IEX_PUB_TOKEN"));

        dao = new MarketDataDao(connectionManager, marketDataConfig);
    }

    @Test
    public void findIexQuotesByTickers() throws Exception{

        String tickerOne = System.getenv("tickerOne");
        String tickerTwo = System.getenv("tickerTwo");

        List<IexQuote> quotes = dao.findAllById(Arrays.asList(tickerOne, tickerTwo));
        assertEquals(2, quotes.size());
        assertEquals(tickerOne, quotes.get(0).getSymbol());
    }

    @Test
    public void findByTicker(){
        String ticker = System.getenv("tickerOne");
        IexQuote iexQuote = dao.findById(ticker).get();
        assertEquals(ticker, iexQuote.getSymbol());
    }

}
