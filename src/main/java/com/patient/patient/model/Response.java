package com.patient.patient.model;

public interface Response {
    String getMessage();

    Boolean getIsSuccessful();

    void setMessage(String message);
}
