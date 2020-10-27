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
        // this has an id of -1 because it doesn't have an Id associated with it in Jama
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
        /*
         * Problems have been reported that itemTypeList is null at this stage. The reasons are not fully clear, an
         * exception should have been thrown in that case. Anyway, fix is to return an empty list instead.
         */
        return Collections.unmodifiableList(itemTypeList != null ? itemTypeList : Collections.emptyList());
    }
}
