package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.model.Tweet;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class TwitterService implements Service{

    private TwitterDao dao;

    @Autowired
    public TwitterService(TwitterDao dao){
        this.dao = dao;
    }

    @Override
    public Tweet postTweet(Tweet tweet) {
        return null;
    }

    @Override
    public Tweet showTweet(String id, String[] fields) {

        HashSet<String> fieldSet = new HashSet<>();

        for (String field : fields)
            fieldSet.add(field);

        Tweet tweet = null;
        try {
            tweet = dao.findById(id);
        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();
        }

        if (!fieldSet.contains("date"))
            tweet.setDate(null);
        if (!fieldSet.contains("idStr"))
            tweet.setIdStr(null);
        if (!fieldSet.contains("text"))
            tweet.setText(null);
        if (!fieldSet.contains("entities"))
            tweet.setEntities(null);
        if (!fieldSet.contains("coordinates"))
            tweet.setCoordinates(null);

        return tweet;
    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) {
        return null;
    }
}
