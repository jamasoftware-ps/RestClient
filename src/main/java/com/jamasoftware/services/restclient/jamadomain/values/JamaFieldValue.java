package com.jamasoftware.services.restclient.jamadomain.values;

import com.jamasoftware.services.restclient.exception.JamaTypeMismatchException;
import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.core.JamaInstance;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.core.LazyResource;
import com.jamasoftware.services.restclient.jamadomain.fields.JamaField;

public abstract class JamaFieldValue {
    private JamaInstance jamaInstance;
    private String name;
    private String label;
    protected JamaField field;

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

    public abstract void setValue(Object value) throws JamaTypeMismatchException, RestClientException;

    @Override
    public String toString() {
        if(getValue() == null) {
            return "";
        }
        return getValue().toString();
    }

    protected void checkType(Class clazz, Object value) throws JamaTypeMismatchException {
        if(!clazz.isInstance(value)){
            throw new JamaTypeMismatchException("Expected type " + clazz.getName() + ", received " + value.getClass() + " instead. " +
                    "In field: " + getName());
        }
    }

    protected void checkTypes(Class[] classes, Object value) throws JamaTypeMismatchException {
        for(Class clazz : classes) {
            if(clazz.isInstance(value)) {
                return;
            }
        }
        String message = "Expected one type of ";
        for(int i = 0; i < classes.length; ++i) {
            message += classes[i].getName() + (i == classes.length - 1 ? "" : ", ");
        }
        throw new JamaTypeMismatchException(message + ". Received " + value.getClass() + " instead. In field: " + getName());
    }

    public void setField(JamaField field) {
        this.field = field;
    }

    public boolean readOnly() {
        return field.isReadOnly();
    }

    protected void setValueFromPoolOrNew(Class clazz, int resourceId) throws RestClientException {
        JamaDomainObject jamaDomainObject = getJamaInstance().checkPool(clazz, resourceId);
        try {
            if(jamaDomainObject == null) {
                jamaDomainObject = (JamaDomainObject)clazz.newInstance();
                jamaInstance.addToPool(clazz, resourceId, jamaDomainObject);
                if(jamaDomainObject instanceof LazyResource) {
                    ((LazyResource)jamaDomainObject).associate(resourceId, jamaInstance);
                }
            }
            setValue(jamaDomainObject);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RestClientException(e);
        }
    }

}
