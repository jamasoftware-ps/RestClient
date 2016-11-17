package com.jamasoftware.services.restclient.jamadomain;

import com.jamasoftware.services.restclient.JamaConfig;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItem;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItemType;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaProject;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamaclient.JamaClient;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.LazyResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JamaInstance implements JamaDomainObject {
    private JamaClient jamaClient;
    private JamaConfig jamaConfig;
    private Integer resourceTimeOut;

    // todo remove itemType map
    private HashMap<Integer, JamaItemType> itemTypeMap = new HashMap<>();
    private Map<String, JamaDomainObject> resourcePool = new HashMap<>();


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

    public JamaDomainObject checkPool(Class clazz, int id) {
        return resourcePool.get(clazz.getName() + id);
    }

    public void addToPool(Class clazz, int id, JamaDomainObject jamaDomainObject) {
        resourcePool.put(clazz.getName() + id, jamaDomainObject);
    }

    private JamaDomainObject checkPool(JamaDomainObject fresh) {
        if(fresh instanceof LazyResource) {
            String key = fresh.getClass().getName() + ((LazyResource) fresh).getId();
            LazyResource existingResource = (LazyResource) resourcePool.get(key);
            if(existingResource != null) {
                existingResource.copyContentFrom(fresh);
                return existingResource;
            }
            resourcePool.put(key, fresh);
        }
        return fresh;
    }

    public JamaDomainObject getResource(String resource) throws RestClientException {
        JamaDomainObject retrieved = jamaClient.getResource(resource, this);
        return checkPool(retrieved);
    }

    public List<JamaDomainObject> getAll(String resource) throws RestClientException {
        List<JamaDomainObject> objects = jamaClient.getAll(jamaConfig.getBaseUrl() + resource, this);
        for(int i = 0; i < objects.size(); ++i) {
            objects.set(i, checkPool(objects.get(i)));
        }
        return objects;
    }

    public JamaProject getProject(int id) {
        String key = JamaProject.class.getName() + id;
        JamaProject project = (JamaProject)resourcePool.get(key);
        if(project != null) {
            project.fetch();
        } else {
            project = new JamaProject();
            project.associate(id, this);
            resourcePool.put(key, project);
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

    private void addItemType(JamaItemType itemType) {
        itemTypeMap.put(itemType.getId(), itemType);
    }

    public List<JamaItemType> getItemTypes() throws RestClientException {
        List<JamaItemType> itemTypes = new ArrayList<>();
        List<JamaDomainObject> jamaDomainObjects = getAll("itemtypes");
        for(JamaDomainObject jamaDomainObject : jamaDomainObjects) {
            JamaItemType itemType = (JamaItemType) jamaDomainObject;
            itemTypes.add(itemType);
            itemTypeMap.put(itemType.getId(), itemType);
        }
        return itemTypes;
    }

    public JamaItemType getItemType(int id) {
        JamaItemType itemType = new JamaItemType();
        itemType.associate(id, this);
        return itemType;
    }

    public JamaItemType getItemType(String name) throws RestClientException {
        getItemTypes();
        JamaItemType found = null;
        for(JamaItemType itemType : itemTypeMap.values()) {
            if(name.equals(itemType.getDisplay())) {
                if(found != null) {
                    throw new RestClientException("Multiple ItemTypes with the display: " + name);
                }
                found = itemType;
            }
        }
        return found;
    }

    public JamaItem getItem(int id) {
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

    public void setResourceTimeOut(Integer resourceTimeOut) {
        this.resourceTimeOut = resourceTimeOut;
    }
}
