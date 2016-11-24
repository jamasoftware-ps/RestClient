package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.JamaLocation;
import com.jamasoftware.services.restclient.jamadomain.LockStatus;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaProject;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaUser;
import com.jamasoftware.services.restclient.jamadomain.stagingresources.StagingItem;

import java.util.Date;


public class JsonStagingItem extends StagingItem{

    JsonStagingItem(){}

    public void setLocation(JamaLocation jamaLocation){
        this.location = jamaLocation;
    }

    @Override
    public void copyContentFrom(JamaDomainObject jamaDomainObject) {
        super.copyContentFrom(jamaDomainObject);
    }

    public void setLock(LockStatus lockStatus) {
        this.lockStatus = lockStatus;
    }

    @Override
    public void writeContentTo(JamaDomainObject jamaItem){
        super.writeContentTo(jamaItem);
    }

    public StagingItem setDocumentKey(String documentKey) {
        this.documentKey = documentKey;
        return this;
    }

    public StagingItem setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public StagingItem setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public StagingItem setLastActivityDate(Date lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
        return this;
    }
    public StagingItem setCreatedBy(JamaUser createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public StagingItem setProject(JamaProject project) {
        this.project = project;
        return this;
    }

}
