package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

public class TwitterDao implements CrdDao<Tweet, String>{

    //URI constants
    private static final String API_BASE_URI = "https://api.twitter.com";
    private static final String SHOW_PATH = "/2/tweets/";

    //URI symbols
    private static final String QUERY_SYM = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUAL = "=";

    //Response code
    private static final int HTTP_OK = 200;

    private HttpHelper httpHelper;

    public TwitterDao(HttpHelper httpHelper){
        this.httpHelper = httpHelper;
    }

    @Override
    public Tweet create(Tweet entity) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException {

        URI uri = URI.create(API_BASE_URI+SHOW_PATH);
        Tweet tweet = null;

        HttpResponse response = httpHelper.httpPost(uri);

        int status = response.getStatusLine().getStatusCode();

        if (status != HTTP_OK){
            throw new RuntimeException("HTTP status: "+status);
        }

        String jsonString = EntityUtils.toString(response.getEntity());


        try{
            tweet = JsonUtil.toObjectFromJson(jsonString, Tweet.class);
        } catch(IOException e){
            throw new RuntimeException(e);
        }

        return tweet;
    }

    @Override
    public Tweet findById(String s) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException {

        URI uri = URI.create(API_BASE_URI+SHOW_PATH+s);
        Tweet tweet;

        HttpResponse response = httpHelper.httpGet(uri);

        int status = response.getStatusLine().getStatusCode();

        if (status != HTTP_OK){
            throw new RuntimeException("HTTP status: "+status);
        }

        String jsonString = EntityUtils.toString(response.getEntity());


        try{
            tweet = JsonUtil.toObjectFromJson(jsonString.substring(8, jsonString.length()), Tweet.class);
        } catch(IOException e){
            throw new RuntimeException(e);
        }

        return tweet;
    }

    @Override
    public Tweet deleteById(String s) {
        return null;
    }

}
