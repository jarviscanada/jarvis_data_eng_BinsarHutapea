package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TwitterController implements Controller{

    private static final String COORD_SEP = ":";
    private static final String COMMA = ",";

    private Service service;

    @Autowired
    public TwitterController(Service service){
        this.service = service;
    }

    @Override
    public Tweet postTweet(String[] args) {
        return null;
    }

    @Override
    public Tweet showTweet(String[] args) {
        if (args.length < 2)
            throw new IllegalArgumentException("Usage: TwitterCLIApp get \"tweet id\" [OPTIONAL] \"field 1\" \"field 2\"..\"field n\"");

        String id = args[1];

        String[] fields = new String[args.length-1];

        if (args.length>2){
            for (int i = 2; i < args.length; i++){
                fields[i-2] = args[i];
            }
        }

        return service.showTweet(id, fields);
    }

    @Override
    public List<Tweet> deleteTweet(String[] args) {
        return null;
    }
}
