package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Suite;
import static org.junit.Assert.assertEquals;

@Suite.SuiteClasses({TwitterDao.class})
public class TwitterDaoIntegrationTest {

    private TwitterDao dao;

    @Before
    public void setup(){
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");

        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        this.dao = new TwitterDao(httpHelper);
    }

    @Test
    public void findById() throws Exception{
        String id = System.getenv("tweetId");
        String expectedText = System.getenv("expectedText");

        if (expectedText == null || expectedText.length()==2){
            expectedText = "";
        }
        else
            expectedText = expectedText.substring(1, expectedText.length()-1);

        Tweet tweet = dao.findById(id);

        assertEquals(expectedText, tweet.getText());
    }

}
