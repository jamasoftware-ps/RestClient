package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.stagingresources.StagingUser;

public class JsonStagingUser extends StagingUser {
    JsonStagingUser(){}

    public void writeContentTo(JamaDomainObject jamaDomainObject) {
        super.writeContentTo(jamaDomainObject);
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
