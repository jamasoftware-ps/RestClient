package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.jamadomain.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.values.DateFieldValue;
import com.jamasoftware.services.restclient.util.DateUtil;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Date;

public class Release extends LazyResource{
    private String name;
    private String description;
    private JamaProject project;
    private Date releaseDate;
    private boolean active;
    private boolean archived;
    private Integer itemCount;

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

    public String getName() {
        fetch();
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        fetch();
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JamaProject getProject() {
        fetch();
        return project;
    }

    public void setProject(JamaProject project) {
        this.project = project;
    }

    public Date getReleaseDate() {
        fetch();
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isActive() {
        fetch();
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isArchived() {
        fetch();
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public Integer getItemCount() {
        fetch();
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
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
