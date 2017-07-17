package com.irit.main;

import java.util.HashMap;

/**
 * Created by mkostiuk on 17/07/2017.
 */
public class Liker {

    public HashMap<String,String> likes;

    public Liker() {
        likes = new HashMap<>();
    }

    public void addLike(String page) {
        String likePage = likes.get(page);

        if (likePage == null) {
            likePage = "1";
            likes.put(page, likePage);
        }
        else {
            Integer newLike = Integer.parseInt(likePage) + 1;
            likes.put(page, newLike.toString());
        }
    }

    public HashMap<String, String> getLikes() {
        return likes;
    }
}


