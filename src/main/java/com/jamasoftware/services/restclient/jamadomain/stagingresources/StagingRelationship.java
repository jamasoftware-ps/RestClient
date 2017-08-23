package com.jamasoftware.services.restclient.jamadomain.stagingresources;

import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.core.JamaInstance;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItem;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaRelationship;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaRelationshipType;

public class StagingRelationship extends JamaRelationship implements StagingResource {
    final private JamaRelationship originatingRelationship;
    private boolean invalid = false;


    @Override
    public JamaItem getToItem() {
        return toItem;
    }

    @Override
    public JamaItem getFromItem() {
        return fromItem;
    }

    @Override
    protected String getEditUrl() throws RestClientException {
        return "relationships/" + getId();
    }

    @Override
    protected String getCreateUrl() throws RestClientException {
        return "relationships/";
    }

    private void testValidity() throws RestClientException {
        if(invalid) {
            throw new RestClientException("Staging relationship may not be accessed after commit is called.");
        }
    }

    protected StagingRelationship(){
        originatingRelationship = null;
    }

    protected StagingRelationship(JamaRelationship relationship) throws RestClientException {
        super(relationship);
        if(relationship.getId() != null)
            super.associate(relationship.getId(), relationship.getJamaInstance());
        else
            super.associate(relationship.getJamaInstance());
        originatingRelationship = relationship;
    }

    @Override
    protected void copyContentFrom(JamaDomainObject jamaDomainObject) {
        super.copyContentFrom(jamaDomainObject);
    }


    public StagingRelationship setRelationshipType(JamaRelationshipType relationshipType) throws RestClientException {
        testValidity();
        this.relationshipType = relationshipType;
        return this;
    }

    public StagingRelationship setFromItem(JamaItem item) throws RestClientException {
        testValidity();
        this.fromItem = item;
        return this;
    }

    public StagingRelationship setToItem(JamaItem item) throws RestClientException {
        testValidity();
        this.toItem = item;
        return this;
    }

    public JamaRelationship commit() throws RestClientException {
        testValidity();
        invalid = true; // never to be unset...
        if (getId() == null) {
            return postCommit();
        } else {
            return putCommit();
        }
    }

    private JamaRelationship postCommit() throws RestClientException {
        return (JamaRelationship)post(JamaRelationship.class);
    }

    private JamaRelationship putCommit() throws RestClientException {
        put();
        invalidate(originatingRelationship);
        return originatingRelationship;
    }

    @Override
    public void associate(int id, JamaInstance jamaInstance) throws RestClientException {
        testValidity();
        throw new RestClientException("You cannot associate a relationship while it is in edit mode.");
    }

}
