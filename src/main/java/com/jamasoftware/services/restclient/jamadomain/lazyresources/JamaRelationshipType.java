package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.jamadomain.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.LazyResource;

public class JamaRelationshipType extends LazyResource {
    protected String name;
    protected boolean isDefault;

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

    @Override
    protected void writeContentTo(JamaDomainObject jamaDomainObject) {
        checkType(JamaRelationshipType.class, jamaDomainObject);
        ((JamaRelationshipType)jamaDomainObject).copyContentFrom(this);
    }


    public String getName() {
        fetch();
        return name;
    }

    public boolean isDefault() {
        fetch();
        return isDefault;
    }

    @Override
    public String toString() {
        return getName();
    }
}
