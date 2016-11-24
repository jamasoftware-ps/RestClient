package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.stagingresources.StagingRelease;

public class JsonStagingRelease extends StagingRelease {

    JsonStagingRelease() {}

    @Override
    public void writeContentTo(JamaDomainObject jamaDomainObject) {
        super.writeContentTo(jamaDomainObject);
    }

    public JsonStagingRelease setItemCount(int itemCount){
        this.itemCount = itemCount;
        return this;
    }

}
