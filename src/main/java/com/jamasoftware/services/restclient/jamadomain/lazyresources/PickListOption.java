package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.jamadomain.JamaDomainObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PickListOption extends LazyResource {
    private String name;
    private String description;
    private boolean active;
    private String color;
    private boolean defaultValue;

    @Override
    protected String getResourceUrl() {
        return "picklistoptions/" + getId();
    }

    @Override
    protected void copyContentFrom(JamaDomainObject jamaDomainObject) {
        checkType(getClass(), jamaDomainObject);

        PickListOption option = (PickListOption)jamaDomainObject;
        name = option.name;
        description = option.description;
        active = option.active;
        color = option.color;
        defaultValue = option.defaultValue;
    }

    public String getName() {
        fetch();
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        fetch();
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        fetch();
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        fetch();
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isDefaultValue() {
        fetch();
        return defaultValue;
    }

    public void setDefaultValue(boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public String toString() {
        return getName();
    }
}
