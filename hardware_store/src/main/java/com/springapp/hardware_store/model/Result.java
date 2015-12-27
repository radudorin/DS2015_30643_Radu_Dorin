package com.springapp.hardware_store.model;

/**
 * Created by radud on 03/12/2015.
 */
public class Result {

    private boolean hasErrors;

    private String message;

    private BaseResponse response;

    public Result(boolean hasErrors, String message, BaseResponse response) {
        this.hasErrors = hasErrors;
        this.message = message;
        this.response = response;
    }

    public BaseResponse getResponse() {
        return response;
    }

    public void setResponse(BaseResponse response) {
        this.response = response;
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
