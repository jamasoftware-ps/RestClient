package com.jamasoftware.services.restclient.JamaDomain.fields;

import com.jamasoftware.services.restclient.JamaDomain.JamaInstance;
import com.jamasoftware.services.restclient.JamaDomain.SerializableJamaDomainObject;
import com.jamasoftware.services.restclient.JamaDomain.values.FieldValue;

public abstract class Field implements SerializableJamaDomainObject {
    private JamaInstance jamaInstance;

    private int id;
    private String name;
    private String label;
    private boolean readOnly;
    private boolean required;
    private boolean triggerSuspect;
    private boolean synchronize;

    public abstract FieldValue getValue();


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
        // todo need to strip $1234132
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

    protected void populateFieldValue(FieldValue fieldValue) {
        fieldValue.setJamaInstance(jamaInstance);
        fieldValue.setName(name);
        fieldValue.setLabel(label);
    }
}
