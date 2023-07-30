package com.patient.patient.model;

public class FailureResponse implements Response {
    private String message;
    private Boolean isSuccessful = false;

    public FailureResponse(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Boolean getIsSuccessful() {
        return isSuccessful;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
