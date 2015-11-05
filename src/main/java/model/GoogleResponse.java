package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by radud on 05/11/2015.
 */
public class GoogleResponse {
    @SerializedName("results")
    private List<Results> results;

    @SerializedName("stasus")
    private String status;

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}