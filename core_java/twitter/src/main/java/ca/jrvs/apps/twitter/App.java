package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class App {

    public static void main(String[] args) throws Exception{

        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");

        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);

        HttpResponse response = httpHelper.httpGet(new URI("https://api.twitter.com/2/tweets/search/recent?query=Aurora%20Borealis"));

        System.out.println(EntityUtils.toString(response.getEntity()));

    }

}
