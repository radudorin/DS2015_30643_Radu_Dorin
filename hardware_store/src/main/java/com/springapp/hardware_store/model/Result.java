package com.springapp.hardware_store.model;

import java.io.Serializable;

/**
 * Created by radud on 03/12/2015.
 */
public class Result<T> implements Serializable {

    private boolean hasErrors;

    private String message;

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

    public boolean getHasErrors() {
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
