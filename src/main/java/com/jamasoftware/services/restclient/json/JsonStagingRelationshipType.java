package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.stagingresources.StagingRelationshipType;
import com.jamasoftware.services.restclient.jamadomain.stagingresources.StagingResource;

public class JsonStagingRelationshipType extends StagingRelationshipType implements StagingResource{

    JsonStagingRelationshipType(){}

    @Override
    public void writeContentTo(JamaDomainObject jamaDomainObject) {
        super.writeContentTo(jamaDomainObject);
    }

    public JsonStagingRelationshipType setName(String name) {
        this.name = name;
        return this;
    }

    public JsonStagingRelationshipType setDefault(boolean aDefault) {
        this.isDefault = aDefault;
        return this;
    }
}
