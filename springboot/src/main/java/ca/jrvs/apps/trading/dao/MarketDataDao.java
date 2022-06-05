package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {

    private static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
    private final String IEX_BATCH_URL;

    private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
    private HttpClientConnectionManager httpClientConnectionManager;

    @Autowired
    public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager, MarketDataConfig marketDataConfig ) {

        this.httpClientConnectionManager = httpClientConnectionManager;
        IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
    }

    @Override
    public <S extends IexQuote> S save(S s) {
        return null;
    }

    @Override
    public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    /**
     *
     * @param ticker
     * @return
     */
    @Override
    public Optional<IexQuote> findById(String ticker) {

        Optional<IexQuote> iexQuote;
        List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));

        if (quotes.size() == 0){
            return Optional.empty();
        }
        else if (quotes.size() == 1){
            iexQuote = Optional.of(quotes.get(0));
        }
        else{
            throw new DataRetrievalFailureException("Unexpected number of quotes");
        }

        return iexQuote;
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<IexQuote> findAll() {
        return null;
    }

    @Override
    public List<IexQuote> findAllById(Iterable<String> iterable) {

        List<IexQuote> quotes = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();

        for (String s : iterable){
            String uri = String.format(IEX_BATCH_URL, s);

            //HTTP response
            String response = executeHttpGet(uri)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid ticker"));

            //Array of JSON documents
            JSONObject iexQuotesJson = new JSONObject(response);

            //Get number of documents
            if (iexQuotesJson.length() == 0) {
                throw new IllegalArgumentException("Invalid ticker");
            }

            String iexQuotesString = iexQuotesJson.toString();
            IexQuote quote = null;
            try {
                quote = JsonUtil.toObjectFromJson(iexQuotesString.substring(13+s.length(), iexQuotesString.length()), IexQuote.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            quotes.add(quote);

        }

        return quotes;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void delete(IexQuote iexQuote) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends IexQuote> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     *
     * @param uri
     * @return
     */
    private Optional<String> executeHttpGet(String uri){
        Optional<String> responseString = null;
        CloseableHttpClient client = getHttpClient();
        HttpGet request = new HttpGet(uri);

        try{
            HttpResponse response = client.execute(request);
            responseString = Optional.of(EntityUtils.toString(response.getEntity()));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseString;
    }

    private CloseableHttpClient getHttpClient(){
        return HttpClients.custom().setConnectionManager(httpClientConnectionManager).setConnectionManagerShared(true).build();
    }
}
