package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Entities {
    private ArrayList<Hashtag> hashtags;
    private ArrayList<UserMention> userMentions;

    public ArrayList<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(ArrayList<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    public ArrayList<UserMention> getUserMentions() {
        return userMentions;
    }

    public void setUserMentions(ArrayList<UserMention> userMentions) {
        this.userMentions = userMentions;
    }
}
