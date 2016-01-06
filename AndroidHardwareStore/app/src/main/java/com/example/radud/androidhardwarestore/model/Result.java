package com.example.radud.androidhardwarestore.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by radud on 03/12/2015.
 */
public class Result<T> {

    @SerializedName("hasErrors")
    private boolean hasErrors;

    @SerializedName("message")
    private String message;

    @SerializedName("response")
    private T response;

    public Result() {
    }

    public Result(boolean hasErrors, String message, T response) {
        this.hasErrors = hasErrors;
        this.message = message;
        this.response = response;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getHasErrors() {
        return hasErrors;
    }
}
