package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaUser;
import com.jamasoftware.services.restclient.jamadomain.stagingresources.StagingProject;

import java.util.Date;

public class JsonStagingProject extends StagingProject {

    JsonStagingProject(){}

    @Override
    public void writeContentTo(JamaDomainObject jamaDomainObject) {
        super.writeContentTo(jamaDomainObject);
    }

    public JsonStagingProject setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public JsonStagingProject setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public JsonStagingProject setCreatedBy(JamaUser createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public JsonStagingProject setModifiedBy(JamaUser modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }


}
