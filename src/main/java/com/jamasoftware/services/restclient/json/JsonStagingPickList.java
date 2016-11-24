package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.stagingresources.StagingPickList;

public class JsonStagingPickList extends StagingPickList {
    public JsonStagingPickList setName(String name) {
        this.name = name;
        return this;
    }

    public JsonStagingPickList setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    protected void writeContentTo(JamaDomainObject jamaDomainObject) {
        super.writeContentTo(jamaDomainObject);
    }
}
