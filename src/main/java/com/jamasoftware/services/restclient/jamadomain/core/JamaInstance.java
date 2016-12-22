package com.jamasoftware.services.restclient.jamadomain.core;

import com.jamasoftware.services.restclient.JamaConfig;
import com.jamasoftware.services.restclient.JamaParent;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.*;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamaclient.JamaClient;
import com.jamasoftware.services.restclient.jamadomain.stagingresources.StagingItem;
import com.jamasoftware.services.restclient.util.CompareUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JamaInstance implements JamaDomainObject {
    private JamaClient jamaClient;
    private JamaConfig jamaConfig;
    private Integer resourceTimeOut;
    private JamaUser currentUser;
    private ItemTypeList itemTypeList;
    private RelationshipTypeList relationshipTypeList;

    private Map<String, WeakReference<JamaDomainObject>> resourcePool = new HashMap<>();

    public JamaInstance(JamaConfig jamaConfig) {
        this.jamaConfig = jamaConfig;
        this.resourceTimeOut = jamaConfig.getResourceTimeOut();
        this.jamaClient = new JamaClient(
                jamaConfig.getHttpClient(),
                jamaConfig.getJson(),
                jamaConfig.getBaseUrl(),
                jamaConfig.getUsername(),
                jamaConfig.getPassword());
    }

    private JamaDomainObject getPoolOrNull(String key) {
        WeakReference<JamaDomainObject> wr = resourcePool.get(key);
        return wr == null ? null : wr.get();
    }

    public JamaDomainObject checkPool(Class clazz, int id) {
        return getPoolOrNull(clazz.getName() + id);
    }

    public void addToPool(Class clazz, int id, JamaDomainObject jamaDomainObject) {
        resourcePool.put(clazz.getName() + id, new WeakReference<>(jamaDomainObject));
    }

    private JamaDomainObject checkPool(JamaDomainObject fresh) {
        if(fresh instanceof LazyResource) {
            String key = fresh.getClass().getName() + ((LazyResource) fresh).getId();
            LazyResource existingResource = (LazyResource) getPoolOrNull(key);
            if(existingResource != null) {
                existingResource.copyContentFrom(fresh);
                return existingResource;
            }
            resourcePool.put(key, new WeakReference<>(fresh));
        }
        return fresh;
    }

    public JamaDomainObject getResource(String resource) throws RestClientException {
        JamaDomainObject retrieved = jamaClient.getResource(resource, this);
        return checkPool(retrieved);
    }

    public List<JamaDomainObject> getResourceCollection(String resource) throws RestClientException {
        return getAll(resource);
    }

    public List<JamaDomainObject> getAll(String resource) throws RestClientException {
        List<JamaDomainObject> objects = jamaClient.getAll(jamaConfig.getBaseUrl() + resource, this);
        for(int i = 0; i < objects.size(); ++i) {
            objects.set(i, checkPool(objects.get(i)));
        }
        return objects;
    }


    public JamaProject getProject(int id) throws RestClientException{
        String key = JamaProject.class.getName() + id;
        JamaProject project = (JamaProject) getPoolOrNull(key);
        if(project != null) {
            project.fetch();
        } else {
            project = new JamaProject();
            project.associate(id, this);
            resourcePool.put(key, new WeakReference<>((JamaDomainObject)project));
        }
        return project;
    }

    public List<JamaProject> getProjects() throws RestClientException {
        List<JamaProject> projects = new ArrayList<>();
        List<JamaDomainObject> jamaDomainObjects = getAll("projects");
        for(JamaDomainObject jamaDomainObject : jamaDomainObjects) {
            JamaProject project = (JamaProject) jamaDomainObject;
            projects.add(project);
        }
        return projects;
    }

    public List<JamaItemType> getItemTypes() throws RestClientException {
        if(itemTypeList == null) {
            itemTypeList = new ItemTypeList(this);
        }
        return itemTypeList.getItemTypes();
    }

    public JamaItemType getItemType(int id) throws RestClientException{
        JamaItemType itemType = new JamaItemType();
        itemType.associate(id, this);
        return itemType;
    }

    public JamaItemType getItemType(String name) throws RestClientException {
        List<JamaItemType> itemTypes = getItemTypes();
        JamaItemType found = null;
        for(JamaItemType itemType : itemTypes) {
            if(CompareUtil.closeEnough(name, itemType.getDisplay())) {
                if(found != null) {
                    throw new RestClientException("Multiple ItemTypes with the display: " + name);
                }
                found = itemType;
            }
        }
        return found;
    }

    public JamaItem getItem(int id) throws RestClientException {
        JamaItem item = new JamaItem();
        item.associate(id, this);
        return item;
    }

    public void ping() throws RestClientException {
        jamaClient.ping();
    }

    public Integer getResourceTimeOut() {
        return resourceTimeOut;
    }

    public JamaUser getCurrentUser() throws RestClientException{
        if(currentUser == null) {
            currentUser = (JamaUser) getResource("users/current");
        }
        return currentUser;
    }

    public void putRawData(String resource, String payload) throws RestClientException {
        jamaClient.putRaw(jamaConfig.getBaseUrl() + resource, payload);
    }

    public void postRawData(String resource, String payload) throws RestClientException {
        jamaClient.postRaw(jamaConfig.getBaseUrl() + resource, payload);
    }

    public byte[] retrieveItemTypeImage(String url) throws RestClientException {
        return jamaClient.getItemTypeImage(url);
    }

    public String getOpenUrl(JamaItem item) {
        return this.jamaConfig.getOpenUrlBase() + item.getId() + "?project=" + item.getProject().getId();
    }

    public void setBaseOpenUrl(String baseOpenUrl) {
        this.jamaConfig.setOpenUrlBase(baseOpenUrl);
    }

    public void setResourceTimeOut(Integer resourceTimeOut) {
        this.resourceTimeOut = resourceTimeOut;
    }

    public StagingItem createItem(String name, JamaParent parent, JamaItemType itemType) throws RestClientException{
        return (new StagingDispenser()).createStagingItem(this, name, parent, itemType);
    }

    protected void put(LazyResource lazyResource) throws RestClientException {
        jamaClient.put(lazyResource.getEditUrl(), lazyResource);
    }

    protected LazyResource post(LazyResource lazyResource, Class clazz) throws RestClientException {
        Integer resourceId = jamaClient.post(lazyResource.getCreateUrl(), lazyResource);
        if(resourceId == null) {
            return null;
        }
        LazyResource createdResource;
        try {
            createdResource = (LazyResource)clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RestClientException(e);
        }
        createdResource.associate(resourceId, this);
        return createdResource;
    }

    public StagingItem editItem(JamaItem jamaItem) throws RestClientException {
        jamaItem.fetch();
        return (new StagingDispenser()).createStagingItem(jamaItem);
    }

    public List<JamaRelationshipType> getRelationshipTypes() throws RestClientException {
        if(relationshipTypeList == null) {
            relationshipTypeList = new RelationshipTypeList(this);
        }
        return relationshipTypeList.getRelationshipTypes();
    }
}
