package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.JamaParent;
import com.jamasoftware.services.restclient.exception.ItemEditException;
import com.jamasoftware.services.restclient.exception.JsonException;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.JamaLocation;
import com.jamasoftware.services.restclient.jamadomain.core.LazyResource;
import com.jamasoftware.services.restclient.jamadomain.LockStatus;
import com.jamasoftware.services.restclient.jamadomain.stagingresources.StagingItem;
import com.jamasoftware.services.restclient.jamadomain.values.JamaFieldValue;
import com.jamasoftware.services.restclient.jamadomain.values.TextFieldValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class JamaItem extends LazyResource implements JamaParent{
    protected TextFieldValue name;
    protected String globalId;
    protected String documentKey;
    protected JamaProject project;
    protected JamaItemType itemType;
    protected JamaItemType childItemType;
    protected Date createdDate;
    protected Date modifiedDate;
    protected Date lastActivityDate;
    protected JamaUser createdBy;
    protected JamaUser modifiedBy;
    protected JamaLocation location;
    protected List<JamaFieldValue> fieldValues = new ArrayList<>();
    protected LockStatus lockStatus;
    private ChildrenList children;

    public JamaItem(){}

    public JamaItem(JamaItem item) {
        copyContentFrom(item);
    }

    public JamaParent getParent() {
        fetch();
        return location.getParent();
    }

    public String getSequence() {
        fetch();
        return location.getSequence();
    }

    @Override
    public List<JamaItem> getChildren() throws RestClientException {
        if(children == null) {
            children = new ChildrenList(this);
        }
        return Collections.unmodifiableList(children.getChildren());
    }

//    @Override
//    public void addChild(JamaItem jamaItem) throws RestClientException {
//        throw new ItemEditException("You are attempting to update an item's location. This can only be done using the " +
//                "\'setParent\' method on an edited item.");
//    }

    @Override
    public boolean isProject() {
        return false;
    }

    @Override
    public void makeChildOf(JamaParent jamaParent) throws RestClientException {
        this.location.setParent(jamaParent);
    }

    @Override
    protected String getResourceUrl() {
        return "items/" + getId();
    }
    

    @Override
    protected void copyContentFrom(JamaDomainObject jamaDomainObject) {
        checkType(JamaItem.class, jamaDomainObject);

        JamaItem item = (JamaItem) jamaDomainObject;
        name = item.name;
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
        lockStatus = item.lockStatus;
    }

    @Override
    protected void writeContentTo(JamaDomainObject jamaItem){
        checkType(JamaItem.class, jamaItem);
        ((JamaItem)jamaItem).copyContentFrom(this);
    }

    public TextFieldValue getName() {
        fetch();
        return name;
    }

    public String getGlobalId() {
        fetch();
        return globalId;
    }

    public JamaProject getProject() {
        fetch();
        return project;
    }

    public JamaItemType getItemType() {
        fetch();
        return itemType;
    }


    public JamaItemType getChildItemType() {
        fetch();
        return childItemType;
    }

    public JamaUser getCreatedBy() {
        fetch();
        return createdBy;
    }

    public JamaUser getModifiedBy() {
        fetch();
        return modifiedBy;
    }

    protected JamaLocation getLocation() {
        fetch();
        return location;
    }

    public List<JamaFieldValue> getFieldValues() {
        fetch();
        return fieldValues;
    }

    public JamaFieldValue getFieldValueByName(String fieldName) {
        if(fieldName.equals("name")) {
            return getName();
        }
        for(JamaFieldValue fieldValue : getFieldValues()) {
            if(fieldValue.getName().equals(fieldName))
                return fieldValue;
        }
        return null;
    }

    public String getDocumentKey() {
        fetch();
        return documentKey;
    }

    public Date getCreatedDate() {
        fetch();
        return createdDate;
    }

    public Date getModifiedDate() {
        fetch();
        return modifiedDate;
    }

    public Date getLastActivityDate() {
        fetch();
        return lastActivityDate;
    }

    public boolean isLocked() {
        fetch();
        return lockStatus.isLocked();
    }

    public boolean isLockedByCurrentUser() throws RestClientException{
        // fetch is called in isLocked so we removed it here
        return isLocked() && lockStatus.getLockedBy().getId().equals(getJamaInstance().getCurrentUser().getId());
    }

    public Date lastLockedDate(){
        fetch();
        return lockStatus.getLastLocked();
    }

    public JamaUser lockedBy() {
        return lockStatus.getLockedBy();
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

    public void forceLockItem() throws RestClientException {
        sendLockRequest(false);
        sendLockRequest(true);
        forceFetch();
    }

    public boolean acquireLock() throws RestClientException {
        try {
            sendLockRequest(true);
            forceFetch();
            return getJamaInstance().getCurrentUser().equals(lockStatus.getLockedBy());
        } catch (RestClientException e) {
            return false;
        }
    }

    public void lock() throws RestClientException {
        sendLockRequest(true);
        forceFetch();
    }

    private void sendLockRequest(boolean lockState) throws RestClientException {
        getJamaInstance().putRawData("items/" + getId() + "/lock", "{\"locked\":" + lockState + "}" );
    }

    // returns status of releasing the lock, for non org admin users to verify whether or not their request was successful
    public boolean releaseLock() throws RestClientException {
        try {
            sendLockRequest(false);
            forceFetch();
            if(lockStatus.getLockedBy() == null) {
                return true;
            } else {
                return false;
            }
        } catch (RestClientException e) {
            return false;
        }
    }

    // if user knows they are an org admin user and can override the lock status of an item locked by another user
    public void unlock() throws RestClientException {
        sendLockRequest(false);
        forceFetch();
    }

    @Override
    public String toString() {
        return getName().toString();
    }

    public byte[] getItemTypeImage() throws JsonException {
        return getItemType().getImage();
    }

    public StagingItem edit() throws RestClientException{
        return jamaInstance.editItem(this);
    }

}
