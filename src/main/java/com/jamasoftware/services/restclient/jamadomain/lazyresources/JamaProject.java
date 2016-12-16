package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.JamaParent;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.core.LazyResource;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class JamaProject extends LazyResource implements JamaParent {
    protected String projectKey;
    protected String name;
    protected String description;
    protected boolean isFolder;
    protected Date createdDate;
    protected Date modifiedDate;
    protected JamaUser createdBy;
    protected JamaUser modifiedBy;
    private ChildrenList children;

    @Override
    public List<JamaItem> getChildren() throws RestClientException {
        if(children == null) {
            children = new ChildrenList(this);
        }
        return Collections.unmodifiableList(children.getChildren());
    }

//    @Override
//    public void addChild(JamaItem jamaItem) {
//        throw new NotImplementedException();
//
//    }

    @Override
    public boolean isProject() {
        return true;
    }

    @Override
    public void makeChildOf(JamaParent jamaParent) throws RestClientException {
        throw new RestClientException("You cannot set the parent of a project.");
    }

    @Override
    protected String getResourceUrl() {
        return "projects/" + getId();
    }

    @Override
    public void copyContentFrom(JamaDomainObject jamaDomainObject) {
        checkType(JamaProject.class, jamaDomainObject);

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

    @Override
    protected void writeContentTo(JamaDomainObject jamaDomainObject) {
        checkType(JamaProject.class, jamaDomainObject);
        ((JamaProject)jamaDomainObject).copyContentFrom(this);
    }

    public String getProjectKey() {
        fetch();
        return projectKey;
    }

    public String getName() {
        fetch();
        return name;
    }

    public String getDescription() {
        fetch();
        return description;
    }

    public boolean isFolder() {
        fetch();
        return isFolder;
    }

    public JamaUser getCreatedBy() {
        fetch();
        return createdBy;
    }

    public Date getCreatedDate() {
        fetch();
        return createdDate;
    }

    public Date getModifiedDate() {
        fetch();
        return modifiedDate;
    }

    public JamaUser getModifiedBy() {
        fetch();
        return modifiedBy;
    }

    public List<JamaItem> getItems() throws RestClientException {
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

    public List<JamaRelationship> getRelationships() throws RestClientException {
        List<JamaDomainObject> objects = getJamaInstance().getAll("relationships?project=" + getId());
        List<JamaRelationship> relationships = new ArrayList<>();
        for(JamaDomainObject o : objects) {
            relationships.add((JamaRelationship)o);
        }
        return relationships;
    }

}
