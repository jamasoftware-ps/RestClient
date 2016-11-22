package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.jamadomain.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.LazyResource;

public class PickListOption extends LazyResource {
    protected String name;
    protected String description;
    protected boolean active;
    protected String color;
    protected boolean defaultValue;

    @Override
    protected String getResourceUrl() {
        return "picklistoptions/" + getId();
    }

    @Override
    public void copyContentFrom(JamaDomainObject jamaDomainObject) {
        checkType(getClass(), jamaDomainObject);

        PickListOption option = (PickListOption)jamaDomainObject;
        name = option.name;
        description = option.description;
        active = option.active;
        color = option.color;
        defaultValue = option.defaultValue;
    }

    @Override
    protected void writeContentTo(JamaDomainObject jamaDomainObject) {
        checkType(PickListOption.class, jamaDomainObject);
        ((PickListOption)jamaDomainObject).copyContentFrom(this);
    }

    public String getName() {
        fetch();
        return name;
    }

    public boolean isActive() {
        fetch();
        return active;
    }

    public String getDescription() {
        fetch();
        return description;
    }


    public String getColor() {
        fetch();
        return color;
    }

    public boolean isDefaultValue() {
        fetch();
        return defaultValue;
    }

    @Override
    public String toString() {
        return getName();
    }
}
