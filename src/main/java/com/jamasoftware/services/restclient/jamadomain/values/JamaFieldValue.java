package com.jamasoftware.services.restclient.jamadomain.values;

import com.jamasoftware.services.restclient.exception.JamaTypeMismatchException;
import com.jamasoftware.services.restclient.jamadomain.JamaInstance;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.fields.JamaField;

public abstract class JamaFieldValue {
    private JamaInstance jamaInstance;
    private String name;
    private String label;
    private JamaField field;

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

    public abstract void setValue(Object value) throws JamaTypeMismatchException;

    @Override
    public String toString() {
        if(getValue() == null) {
            return getName() + ": ";
        }
        return getName() + ": " + getValue().toString();
    }

    protected void checkType(Class clazz, Object value) throws JamaTypeMismatchException {
        if(!clazz.isInstance(value)){
            throw new JamaTypeMismatchException("Expected type " + clazz.getName() + ", received " + value.getClass() + " instead. " +
                    "In field: " + getName());
        }
    }

    public void setField(JamaField field) {
        this.field = field;
    }

    public boolean readOnly() {
        return field.isReadOnly();
    }
}
