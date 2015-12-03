package com.springapp.hardware_store.model;

/**
 * Created by radud on 03/12/2015.
 */
public class Result {

    private boolean hasErrors;

    private String message;

    public Result() {
    }

    public boolean hasErrors() {
        return hasErrors;
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
}
