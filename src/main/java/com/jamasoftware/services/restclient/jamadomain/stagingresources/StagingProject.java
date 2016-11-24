package com.jamasoftware.services.restclient.jamadomain.stagingresources;

import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaProject;

public class StagingProject extends JamaProject implements StagingResource {
    public StagingProject setProjectKey(String projectKey) {
        this.projectKey = projectKey;
        return this;
    }

    public StagingProject setName(String name) {
        this.name = name;
        return this;
    }

    public StagingProject setDescription(String description) {
        this.description = description;
        return this;
    }

    public StagingProject setFolder(boolean folder) {
        this.isFolder = folder;
        return this;
    }

    @Override
    protected String getEditUrl() throws RestClientException {
        return "projects/" + getId();
    }

    @Override
    protected String getCreateUrl() throws RestClientException {
        return "items/";
    }
}
