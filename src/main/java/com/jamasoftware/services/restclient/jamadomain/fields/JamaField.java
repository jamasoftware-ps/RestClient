package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.core.JamaInstance;
import com.jamasoftware.services.restclient.jamadomain.SerializableJamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.values.JamaFieldValue;

import java.lang.reflect.Field;

public abstract class JamaField implements SerializableJamaDomainObject {
    private JamaInstance jamaInstance;

    private int id;
    private String name;
    private String label;
    private boolean readOnly;
    private boolean required;
    private boolean triggerSuspect;
    private boolean synchronize;
    public final String type;

    public abstract JamaFieldValue getValue();

//    public abstract String getType();


    public JamaInstance getJamaInstance() {
        return jamaInstance;
    }

    public void setJamaInstance(JamaInstance jamaInstance) {
        this.jamaInstance = jamaInstance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.contains("$")) {
            name = name.substring(0, name.indexOf("$"));
        }
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isTriggerSuspect() {
        return triggerSuspect;
    }

    public void setTriggerSuspect(boolean triggerSuspect) {
        this.triggerSuspect = triggerSuspect;
    }

    public boolean isSynchronize() {
        return synchronize;
    }

    public void setSynchronize(boolean synchronize) {
        this.synchronize = synchronize;
    }

    protected void populateFieldValue(JamaFieldValue fieldValue) {
        fieldValue.setJamaInstance(jamaInstance);
        fieldValue.setName(name);
        fieldValue.setLabel(label);
        fieldValue.setField(this);
    }

    @Override
    public String toString() {
        return getName();
    }

    public JamaField(String type) {
        this.type = type;
    }

    public JamaField() {this.type = null;}
}
