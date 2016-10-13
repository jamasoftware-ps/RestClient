package com.jamasoftware.services.restclient.JamaDomain.values;

import com.jamasoftware.services.restclient.JamaDomain.JamaInstance;
import com.jamasoftware.services.restclient.exception.RestClientException;

public abstract class FieldValue {
    private JamaInstance jamaInstance;
    private String name;
    private String label;

    public abstract Object getValue();

    public abstract void setValue(String value) throws RestClientException;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public JamaInstance getJamaInstance() {
        return jamaInstance;
    }

    public void setJamaInstance(JamaInstance jamaInstance) {
        this.jamaInstance = jamaInstance;
    }
}
