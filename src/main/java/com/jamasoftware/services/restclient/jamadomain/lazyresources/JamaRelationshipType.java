package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.jamadomain.JamaDomainObject;

public class JamaRelationshipType extends LazyResource {
    private String name;
    private boolean isDefault;

    @Override
    protected String getResourceUrl() {
        return "relationshiptypes/" + getId();
    }

    @Override
    public void copyContentFrom(JamaDomainObject jamaDomainObject) {
        checkType(this.getClass(), jamaDomainObject);

        JamaRelationshipType relationshipType = (JamaRelationshipType)jamaDomainObject;
        this.name = relationshipType.name;
        this.isDefault = relationshipType.isDefault;
    }

    public String getName() {
        fetch();
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDefault() {
        fetch();
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    @Override
    public String toString() {
        return getName();
    }
}
