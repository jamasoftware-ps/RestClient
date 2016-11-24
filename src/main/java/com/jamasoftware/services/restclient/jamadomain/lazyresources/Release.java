package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.core.LazyResource;
import com.jamasoftware.services.restclient.util.DateUtil;

import java.util.Date;

public class Release extends LazyResource {
    protected String name;
    protected String description;
    protected JamaProject project;
    protected Date releaseDate;
    protected boolean active;
    protected boolean archived;
    protected Integer itemCount;

    @Override
    protected String getResourceUrl() {
        return "releases/" + getId();
    }

    @Override
    public void copyContentFrom(JamaDomainObject jamaDomainObject) {
        checkType(getClass(), jamaDomainObject);

        Release release = (Release)jamaDomainObject;
        name = release.name;
        description = release.description;
        project = release.project;
        releaseDate = release.releaseDate;
        active = release.active;
        archived = release.archived;
        itemCount = release.itemCount;
    }

    @Override
    protected void writeContentTo(JamaDomainObject jamaDomainObject) {
        checkType(Release.class, jamaDomainObject);
        ((Release)jamaDomainObject).copyContentFrom(this);
    }

    public String getName() {
        fetch();
        return name;
    }

    public String getDescription() {
        fetch();
        return description;
    }


    public JamaProject getProject() {
        fetch();
        return project;
    }

    public Date getReleaseDate() {
        fetch();
        return releaseDate;
    }


    public boolean isActive() {
        fetch();
        return active;
    }

    public boolean isArchived() {
        fetch();
        return archived;
    }

    public Integer getItemCount() {
        fetch();
        return itemCount;
    }

    @Override
    public String toString() {
        String s = getName();
        if(getReleaseDate() != null) {
            s += " [" + DateUtil.monthDayYear(getReleaseDate()) + "]";
        }
        return s;
    }

}
