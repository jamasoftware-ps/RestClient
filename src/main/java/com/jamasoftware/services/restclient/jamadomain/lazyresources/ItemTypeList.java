package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.core.JamaInstance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemTypeList extends LazyCollection {
    private List<JamaItemType> itemTypeList;

    public ItemTypeList(JamaInstance jamaInstance) throws RestClientException {
        associate(-1, jamaInstance);
    }

    @Override
    public String getCollectionUrl() {
        return "itemtypes";
    }

    @Override
    public void copyContentFrom(List<JamaDomainObject> objectList) {
        List<JamaItemType> itemTypes = new ArrayList<>();
        for(JamaDomainObject o : objectList) {
            itemTypes.add((JamaItemType) o);
        }

        this.itemTypeList = itemTypes;
    }

    public List<JamaItemType> getItemTypes() {
        fetch();
        return Collections.unmodifiableList(itemTypeList);
    }
}
