package com.jamasoftware.services.restclient.exception;

public class JamaApiException extends RestClientException {
    private int statusCode;
    private String responseMessage;

    public JamaApiException(int statusCode, String responseMessage) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
    }

    @Override
    public String getMessage() {
        return "Error response from Jama API: \n" + responseMessage;
    }

    @Override
    public String toString() { return getStatusCode() + ": " + getMessage(); }

    private int getStatusCode() {
        return statusCode;
    }
}
