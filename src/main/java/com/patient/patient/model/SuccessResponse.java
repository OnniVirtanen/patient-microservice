package com.patient.patient.model;

public class SuccessResponse implements Response {

    private String message;
    private Boolean isSuccessful = true;

    public SuccessResponse(String message) {
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
