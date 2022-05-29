package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Suite;
import static org.junit.Assert.assertEquals;

@Suite.SuiteClasses({TwitterService.class})
public class TwitterServiceIntTest {

    private TwitterService service;
    private TwitterDao dao;

    @Before
    public void setup(){
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");

        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        this.dao = new TwitterDao(httpHelper);
        service = new TwitterService(dao);
    }

    @Test
    public void showTweet() throws Exception{
        String id = System.getenv("tweetId");
        String expectedText = null;

        String[] fields = {"id"};

        Tweet tweet = service.showTweet(id, fields);

        assertEquals(expectedText, tweet.getText());
    }

}
