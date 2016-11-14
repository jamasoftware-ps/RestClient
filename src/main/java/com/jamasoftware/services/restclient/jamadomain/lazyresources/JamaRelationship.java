package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.jamadomain.JamaDomainObject;

public class JamaRelationship extends LazyResource {
    private JamaItem toItem;
    private JamaItem fromItem;
    private JamaRelationshipType relationshipType;
    private boolean suspect;

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

    public JamaItem getToItem() {
        fetch();
        return toItem;
    }

    public void setToItem(JamaItem toItem) {
        this.toItem = toItem;
    }

    public JamaItem getFromItem() {
        fetch();
        return fromItem;
    }

    public void setFromItem(JamaItem fromItem) {
        this.fromItem = fromItem;
    }

    public JamaRelationshipType getRelationshipType() {
        fetch();
        return relationshipType;
    }

    public void setRelationshipType(JamaRelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }

    public boolean isSuspect() {
        fetch();
        return suspect;
    }

    public void setSuspect(boolean suspect) {
        this.suspect = suspect;
    }
}
