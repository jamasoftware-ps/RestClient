package com.jamasoftware.services.restclient.JamaDomain.lazyresources;

import com.jamasoftware.services.restclient.JamaDomain.JamaDomainObject;
import com.jamasoftware.services.restclient.JamaDomain.JamaInstance;
import com.jamasoftware.services.restclient.JamaDomain.Location;
import com.jamasoftware.services.restclient.JamaDomain.values.FieldValue;
import com.jamasoftware.services.restclient.JamaDomain.values.RichTextFieldValue;
import com.jamasoftware.services.restclient.JamaDomain.values.TextFieldValue;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.exception.UnexpectedJamaResponseException;

import java.util.Date;
import java.util.List;

public class Item extends LazyResource {
    private TextFieldValue name;
    private RichTextFieldValue description;
    private String globalId;
    private String documentKey;
    private Project project;
    private ItemType itemType;
    private ItemType childItemType;
    private Date createdDate;
    private Date modifiedDate;
    private Date lastActivityDate;
    private User createdBy;
    private User modifiedBy;
    private Location location;
    private List<FieldValue> fieldValues;

    @Override
    protected String getResourceUrl() {
        return "items/" + getId();
    }

    @Override
    protected void copyContentFrom(JamaDomainObject jamaDomainObject) {
        if (!(jamaDomainObject instanceof Item)) {
            throw new UnexpectedJamaResponseException("Expecting an Item from the Jama server. Instead, got: " + jamaDomainObject);
        }
        Item item = (Item) jamaDomainObject;

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

    public Project getProject() {
        fetch();
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ItemType getItemType() {
        fetch();
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public ItemType getChildItemType() {
        fetch();
        return childItemType;
    }

    public void setChildItemType(ItemType childItemType) {
        this.childItemType = childItemType;
    }

    public User getCreatedBy() {
        fetch();
        return createdBy;
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

    public Location getLocation() {
        fetch();
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<FieldValue> getFieldValues() {
        fetch();
        return fieldValues;
    }

    public void setFieldValues(List<FieldValue> fieldValues) {
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
}
