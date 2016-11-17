package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.JamaParent;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.JamaLocation;
import com.jamasoftware.services.restclient.jamadomain.values.JamaFieldValue;
import com.jamasoftware.services.restclient.jamadomain.values.RichTextFieldValue;
import com.jamasoftware.services.restclient.jamadomain.values.TextFieldValue;
import com.jamasoftware.services.restclient.exception.UnexpectedJamaResponseException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JamaItem extends LazyResource implements JamaParent {
    private TextFieldValue name;
    private RichTextFieldValue description;
    private String globalId;
    private String documentKey;
    private JamaProject project;
    private JamaItemType itemType;
    private JamaItemType childItemType;
    private Date createdDate;
    private Date modifiedDate;
    private Date lastActivityDate;
    private JamaUser createdBy;
    private JamaUser modifiedBy;
    private JamaLocation location;
    private List<JamaFieldValue> fieldValues;

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
        return "items/" + getId();
    }

    @Override
    public void copyContentFrom(JamaDomainObject jamaDomainObject) {
        checkType(this.getClass(), jamaDomainObject);

        JamaItem item = (JamaItem) jamaDomainObject;
        name = item.name;
        description = item.description;
        globalId = item.globalId;
        documentKey = item.documentKey;
        project = item.project;
        itemType = item.itemType;
        childItemType = item.childItemType;
        createdDate = item.createdDate;
        modifiedDate = item.modifiedDate;
        lastActivityDate = item.lastActivityDate;
        createdBy = item.createdBy;
        modifiedBy = item.modifiedBy;
        location = item.location;
        fieldValues = item.fieldValues;
    }

    public TextFieldValue getName() {
        fetch();
        return name;
    }

    public void setName(TextFieldValue name) {
        this.name = name;
    }

    public RichTextFieldValue getDescription() {
        fetch();
        return description;
    }

    public void setDescription(RichTextFieldValue description) {
        this.description = description;
    }

    public String getGlobalId() {
        fetch();
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    public JamaProject getProject() {
        fetch();
        return project;
    }

    public void setProject(JamaProject project) {
        this.project = project;
    }

    public JamaItemType getItemType() {
        fetch();
        return itemType;
    }

    public void setItemType(JamaItemType itemType) {
        this.itemType = itemType;
    }

    public JamaItemType getChildItemType() {
        fetch();
        return childItemType;
    }

    public void setChildItemType(JamaItemType childItemType) {
        this.childItemType = childItemType;
    }

    public JamaUser getCreatedBy() {
        fetch();
        return createdBy;
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

    public JamaLocation getLocation() {
        fetch();
        return location;
    }

    public void setLocation(JamaLocation location) {
        this.location = location;
    }

    public List<JamaFieldValue> getFieldValues() {
        fetch();
        return fieldValues;
    }

    public void setFieldValues(List<JamaFieldValue> fieldValues) {
        this.fieldValues = fieldValues;
    }

    public String getDocumentKey() {
        fetch();
        return documentKey;
    }

    public void setDocumentKey(String documentKey) {
        this.documentKey = documentKey;
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

    public Date getLastActivityDate() {
        fetch();
        return lastActivityDate;
    }

    public void setLastActivityDate(Date lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public List<JamaRelationship> getDownstreamRelationships() throws RestClientException {
        List<JamaDomainObject> objects = getJamaInstance().getAll("items/" + getId() + "/downstreamrelationships");
        List<JamaRelationship> relationships = new ArrayList<>();
        for(JamaDomainObject o : objects) {
            relationships.add((JamaRelationship)o);
        }
        return relationships;
    }

    public List<JamaRelationship> getUpstreamRelationships() throws RestClientException {
        List<JamaDomainObject> objects = getJamaInstance().getAll("items/" + getId() + "/upstreamrelationships");
        List<JamaRelationship> relationships = new ArrayList<>();
        for(JamaDomainObject object : objects){
            relationships.add((JamaRelationship) object);
        }
        return relationships;
    }

    public List<JamaItem> getDownstreamItems() throws RestClientException {
        List<JamaDomainObject> objects = getJamaInstance().getAll("items/" + getId() + "/downstreamrelated");
        List<JamaItem> items = new ArrayList<>();
        for(JamaDomainObject o : objects) {
            items.add((JamaItem)o);
        }
        return items;
    }

    public List<JamaItem> getUpstreamItems() throws RestClientException {
        List<JamaDomainObject> objects = getJamaInstance().getAll("items/" + getId() + "/upstreamrelated");
        List<JamaItem> items = new ArrayList<>();
        for(JamaDomainObject o : objects) {
            items.add((JamaItem)o);
        }
        return items;
    }

    @Override
    public String toString() {
        return getName().toString();
    }
}
