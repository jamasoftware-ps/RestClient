package com.jamasoftware.services.restclient.jamadomain.stagingresources;

import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaProject;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.Release;

import java.util.Date;

public class StagingRelease extends Release implements StagingResource {

    public StagingRelease setName(String name) {
        this.name = name;
        return this;
    }

    public StagingRelease setDescription(String description) {
        this.description = description;
        return this;
    }

    public StagingRelease setProject(JamaProject project) {
        this.project = project;
        return this;
    }

    public StagingRelease setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public StagingRelease setActive(boolean active) {
        this.active = active;
        return this;
    }

    public StagingRelease setArchived(boolean archived) {
        this.archived = archived;
        return this;
    }
}
