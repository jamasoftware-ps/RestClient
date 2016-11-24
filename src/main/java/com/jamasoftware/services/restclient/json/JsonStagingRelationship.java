package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaRelationshipType;
import com.jamasoftware.services.restclient.jamadomain.stagingresources.StagingRelationship;

public class JsonStagingRelationship extends StagingRelationship {

    JsonStagingRelationship(){}

    public JsonStagingRelationship setSuspect(boolean suspect){
        this.suspect = suspect;
        return this;
    }

    public JsonStagingRelationship setRelationshipType(JamaRelationshipType jamaRelationshipType){
        this.relationshipType = jamaRelationshipType;
        return this;
    }

    @Override
    public void writeContentTo(JamaDomainObject jamaDomainObject){
        super.writeContentTo(jamaDomainObject);
    }
}
