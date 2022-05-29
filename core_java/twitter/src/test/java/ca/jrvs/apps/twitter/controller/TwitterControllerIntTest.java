package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Suite;
import static org.junit.Assert.assertEquals;

@Suite.SuiteClasses({TwitterController.class})
public class TwitterControllerIntTest {

    private TwitterController controller;
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
        controller = new TwitterController(service);
    }

    @Test
    public void showTweet() throws Exception{
        String option = System.getenv("option");
        String id = System.getenv("tweetId");
        String field = System.getenv("field");
        String[] command = {option, id, field};
        String expectedText = System.getenv("expectedText");

        if (expectedText == null || expectedText.length()==2){
            expectedText = "";
        }
        else
            expectedText = expectedText.substring(1, expectedText.length()-1);


        Tweet tweet = controller.showTweet(command);

        assertEquals(expectedText, tweet.getText());
    }

}
