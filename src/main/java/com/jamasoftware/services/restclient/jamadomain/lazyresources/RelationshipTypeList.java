package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.core.JamaInstance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RelationshipTypeList extends LazyCollection {
    private List<JamaRelationshipType> relationshipTypeList;

    public RelationshipTypeList(JamaInstance jamaInstance) throws RestClientException {
        // this has an id of -1 because it doesn't have an Id associated with it in Jama
        associate(-1, jamaInstance);
    }

    @Override
    public String getCollectionUrl() {
        return "relationshiptypes";
    }

    @Override
    public void copyContentFrom(List<JamaDomainObject> objectList) {
        List<JamaRelationshipType> relationshipTypeList = new ArrayList<>();
        for(JamaDomainObject relationship : objectList) {
            relationshipTypeList.add((JamaRelationshipType) relationship);
        }
        this.relationshipTypeList = relationshipTypeList;
    }

    public List<JamaRelationshipType> getRelationshipTypes() {
        fetch();
        return Collections.unmodifiableList(relationshipTypeList);
    }
}
