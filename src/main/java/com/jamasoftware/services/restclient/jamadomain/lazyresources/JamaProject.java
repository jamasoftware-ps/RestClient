package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.JamaParent;
import com.jamasoftware.services.restclient.jamadomain.JamaDomainObject;
import com.jamasoftware.services.restclient.exception.UnexpectedJamaResponseException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JamaProject extends LazyResource implements JamaParent {
    private String projectKey;
    private String name;
    private String description;
    private boolean isFolder;
    private Date createdDate;
    private Date modifiedDate;
    private JamaUser createdBy;
    private JamaUser modifiedBy;

    @Override
    public boolean addChild(JamaItem jamaItem) {
        throw new NotImplementedException();
    }

    @Override
    public List<JamaItem> getChildren() {
        throw new NotImplementedException();
    }

    @Override
    protected String getResourceUrl() {
        return "projects/" + getId();
    }

    @Override
    protected void copyContentFrom(JamaDomainObject jamaDomainObject) {
        checkType(this.getClass(), jamaDomainObject);

        JamaProject project = (JamaProject) jamaDomainObject;
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

    public JamaUser getCreatedBy() {
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

    public void setCreatedBy(JamaUser createdBy) {
        this.createdBy = createdBy;
    }

    public JamaUser getModifiedBy() {
        fetch();
        return modifiedBy;
    }

    public void setModifiedBy(JamaUser modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public List<JamaItem> getItems() {
        List<JamaDomainObject> objects = getJamaInstance().getAll("items?project=" + getId());
        List<JamaItem> items = new ArrayList<>();
        for(JamaDomainObject object : objects) {
            items.add((JamaItem)object);
        }
        return items;
    }

    @Override
    public String toString() {
        return getName();
    }
}
