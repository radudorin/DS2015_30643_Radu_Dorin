package com.springapp.hardware_store.requests;


import com.springapp.hardware_store.model.Rating;

import java.io.Serializable;

/**
 * Created by radud on 07/01/2016.
 */
public class AddRatingHolder implements Serializable {

    private Rating rating;
    private int memberId;

    public AddRatingHolder() {
    }

    public AddRatingHolder(Rating rating, int memberId) {
        this.rating = rating;
        this.memberId = memberId;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
}
