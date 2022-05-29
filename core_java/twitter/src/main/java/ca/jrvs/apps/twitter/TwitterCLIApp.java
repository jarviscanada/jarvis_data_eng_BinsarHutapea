package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;

public class TwitterCLIApp {

    public static final String USAGE = "USAGE: TwitterCLIApp show [options]";

    private Controller controller;

    @Autowired
    public TwitterCLIApp(Controller controller){
        this.controller = controller;
    }

    public static void main(String[] args) throws JsonProcessingException {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");

        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        TwitterDao dao = new TwitterDao(httpHelper);
        TwitterService service = new TwitterService(dao);
        TwitterController controller = new TwitterController(service);
        TwitterCLIApp app = new TwitterCLIApp(controller);

        app.run(args);
    }

    public void run(String[] args) throws JsonProcessingException {
        if (args.length < 2){
            throw new IllegalArgumentException(USAGE);
        }

        if (!args[0].equalsIgnoreCase("show")){
            throw new IllegalArgumentException(USAGE);
        }

        Tweet tweet = this.controller.showTweet(args);
        System.out.println(JsonUtil.toJson(tweet, true, false));

    }

}
