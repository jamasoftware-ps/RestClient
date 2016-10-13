package com.jamasoftware.services.restclient.JamaDomain;

import com.jamasoftware.services.restclient.JamaConfig;
import com.jamasoftware.services.restclient.JamaDomain.lazyresources.Item;
import com.jamasoftware.services.restclient.JamaDomain.lazyresources.ItemType;
import com.jamasoftware.services.restclient.JamaDomain.lazyresources.Project;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamaclient.JamaClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JamaInstance implements JamaDomainObject {
    private JamaClient jamaClient;
    private JamaConfig jamaConfig;

    // todo encapsulate this
    private HashMap<Integer, ItemType> itemTypeMap = new HashMap<>();

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

    public Project getProject(int id) {
        Project project = new Project();
        project.associate(id, this);
        return project;
    }

    public List<Project> getProjects() {
        List<Project> projects = new ArrayList<>();
        try {
            List<JamaDomainObject> jamaDomainObjects = jamaClient.getAll(jamaConfig.getBaseUrl() + "projects", this);
            for(JamaDomainObject jamaDomainObject : jamaDomainObjects) {
                Project project = (Project) jamaDomainObject;
                projects.add(project);
            }
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return projects;
    }

    private void addItemType(ItemType itemType) {
        itemTypeMap.put(itemType.getId(), itemType);
    }

    public List<ItemType> getItemTypes() {
        List<ItemType> itemTypes = new ArrayList<>();
        try {
            List<JamaDomainObject> jamaDomainObjects = jamaClient.getAll(jamaConfig.getBaseUrl() + "itemtypes", this);
            for(JamaDomainObject jamaDomainObject : jamaDomainObjects) {
                ItemType itemType = (ItemType)jamaDomainObject;
                itemTypes.add(itemType);
                itemTypeMap.put(itemType.getId(), itemType);
            }
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return itemTypes;
    }

    public ItemType getItemType(int id) {
        ItemType itemType = new ItemType();
        itemType.associate(id, this);
        return itemType;
    }

    public ItemType getItemType(String name) throws RestClientException {
        getItemTypes();
        ItemType found = null;
        for(ItemType itemType : itemTypeMap.values()) {
            if(name.equals(itemType.getDisplay())) {
                if(found != null) {
                    throw new RestClientException("Multiple ItemTypes with the display: " + name);
                }
                found = itemType;
            }
        }
        return found;
    }

    public Item getItem(int id) {
        Item item = new Item();
        item.associate(id, this);
        return item;
    }

}
