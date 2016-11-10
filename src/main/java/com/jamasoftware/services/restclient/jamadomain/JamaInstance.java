package com.jamasoftware.services.restclient.jamadomain;

import com.jamasoftware.services.restclient.JamaConfig;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItem;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItemType;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaProject;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamaclient.JamaClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JamaInstance implements JamaDomainObject {
    private JamaClient jamaClient;
    private JamaConfig jamaConfig;

    // todo encapsulate this
    private HashMap<Integer, JamaItemType> itemTypeMap = new HashMap<>();

    public JamaInstance(JamaConfig jamaConfig) {
        this.jamaConfig = jamaConfig;
        this.jamaClient = new JamaClient(
                jamaConfig.getHttpClient(),
                jamaConfig.getJson(),
                jamaConfig.getBaseUrl(),
                jamaConfig.getUsername(),
                jamaConfig.getPassword());
    }

    public JamaDomainObject getResource(String resource) throws RestClientException {
        return jamaClient.getResource(resource, this);
    }

    public List<JamaDomainObject> getAll(String resource) {
        try {
            return jamaClient.getAll(jamaConfig.getBaseUrl() + resource, this);
        } catch (RestClientException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JamaProject getProject(int id) {
        JamaProject project = new JamaProject();
        project.associate(id, this);
        return project;
    }

    public List<JamaProject> getProjects() {
        List<JamaProject> projects = new ArrayList<>();
        try {
            List<JamaDomainObject> jamaDomainObjects = jamaClient.getAll(jamaConfig.getBaseUrl() + "projects", this);
            for(JamaDomainObject jamaDomainObject : jamaDomainObjects) {
                JamaProject project = (JamaProject) jamaDomainObject;
                projects.add(project);
            }
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return projects;
    }

    private void addItemType(JamaItemType itemType) {
        itemTypeMap.put(itemType.getId(), itemType);
    }

    public List<JamaItemType> getItemTypes() {
        List<JamaItemType> itemTypes = new ArrayList<>();
        try {
            List<JamaDomainObject> jamaDomainObjects = jamaClient.getAll(jamaConfig.getBaseUrl() + "itemtypes", this);
            for(JamaDomainObject jamaDomainObject : jamaDomainObjects) {
                JamaItemType itemType = (JamaItemType)jamaDomainObject;
                itemTypes.add(itemType);
                itemTypeMap.put(itemType.getId(), itemType);
            }
        } catch (RestClientException e) {
            e.printStackTrace();
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

}
