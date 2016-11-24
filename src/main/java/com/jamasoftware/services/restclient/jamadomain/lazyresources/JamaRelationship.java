package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.core.LazyResource;

public class JamaRelationship extends LazyResource {
    protected JamaItem toItem;
    protected JamaItem fromItem;
    protected JamaRelationshipType relationshipType;
    protected boolean suspect;

    @Override
    protected String getResourceUrl() {
        return "relationships/" + getId();
    }

    @Override
    public void copyContentFrom(JamaDomainObject jamaDomainObject) {
        checkType(JamaRelationship.class, jamaDomainObject);

        JamaRelationship jamaRelationship = (JamaRelationship)jamaDomainObject;
        toItem = jamaRelationship.toItem;
        fromItem = jamaRelationship.fromItem;
        relationshipType = jamaRelationship.relationshipType;
        suspect = jamaRelationship.suspect;
    }

    @Override
    protected void writeContentTo(JamaDomainObject jamaDomainObject) {
        checkType(JamaRelationship.class, jamaDomainObject);
        ((JamaRelationship)jamaDomainObject).copyContentFrom(this);
    }

    public JamaItem getToItem() {
        fetch();
        return toItem;
    }

    public JamaItem getFromItem() {
        fetch();
        return fromItem;
    }

    public JamaRelationshipType getRelationshipType() {
        fetch();
        return relationshipType;
    }

    public boolean isSuspect() {
        fetch();
        return suspect;
    }

}
