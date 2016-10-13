package com.jamasoftware.services.restclient.JamaDomain.lazyresources;

import com.jamasoftware.services.restclient.JamaDomain.JamaDomainObject;
import com.jamasoftware.services.restclient.JamaDomain.values.FieldValue;
import com.jamasoftware.services.restclient.exception.UnexpectedJamaResponseException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project extends LazyResource {
    private String projectKey;
    private String name;
    private String description;
    private boolean isFolder;
    private Date createdDate;
    private Date modifiedDate;
    private User createdBy;
    private User modifiedBy;

    @Override
    protected String getResourceUrl() {
        return "projects/" + getId();
    }

    @Override
    protected void copyContentFrom(JamaDomainObject jamaDomainObject) {
        if (!(jamaDomainObject instanceof Project)) {
            throw new UnexpectedJamaResponseException("Expecting a Project from the Jama server. Instead, got: " + jamaDomainObject);
        }
        Project project = (Project) jamaDomainObject;

        projectKey = project.projectKey;
        name = project.name;
        description = project.description;
        isFolder = project.isFolder;
        createdDate = project.createdDate;
        modifiedDate = project.modifiedDate;
        createdBy = project.createdBy;
        modifiedBy = project.modifiedBy;
    }

    public String getProjectKey() {
        fetch();
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
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

    public boolean isFolder() {
        fetch();
        return isFolder;
    }

    public void setFolder(boolean folder) {
        isFolder = folder;
    }

    public User getCreatedBy() {
        fetch();
        return createdBy;
    }

    public Date getCreatedDate() {
        fetch();
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        fetch();
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getModifiedBy() {
        fetch();
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public List<Item> getItems() {
        List<JamaDomainObject> objects = getJamaInstance().getAll("items?project=" + getId());
        List<Item> items = new ArrayList<>();
        for(JamaDomainObject object : objects) {
            items.add((Item)object);
        }
        return items;
    }
}
