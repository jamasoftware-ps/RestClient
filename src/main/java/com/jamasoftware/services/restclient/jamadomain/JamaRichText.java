package com.jamasoftware.services.restclient.jamadomain;

public class JamaRichText {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
