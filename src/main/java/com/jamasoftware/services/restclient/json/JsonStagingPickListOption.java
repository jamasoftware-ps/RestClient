package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.stagingresources.StagingPickListOption;

public class JsonStagingPickListOption extends StagingPickListOption {

    JsonStagingPickListOption(){}

    @Override
    protected void writeContentTo(JamaDomainObject jamaDomainObject) {
        super.writeContentTo(jamaDomainObject);
    }

    public JsonStagingPickListOption setName(String name) {
        this.name = name;
        return this;
    }

    public JsonStagingPickListOption setActive(boolean active) {
        this.active = active;
        return this;
    }

    public JsonStagingPickListOption setDescription(String description) {
        this.description = description;
        return this;
    }

    public JsonStagingPickListOption setColor(String color) {
        this.color = color;
        return this;
    }

    public JsonStagingPickListOption setDefaultValue(boolean defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }
}
