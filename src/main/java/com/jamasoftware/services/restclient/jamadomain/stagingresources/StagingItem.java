package com.jamasoftware.services.restclient.jamadomain.stagingresources;

import com.jamasoftware.services.restclient.jamadomain.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.*;
import com.jamasoftware.services.restclient.jamadomain.values.JamaFieldValue;
import com.jamasoftware.services.restclient.jamadomain.values.TextFieldValue;

import java.util.Date;
import java.util.List;

public class StagingItem extends JamaItem implements StagingResource {
    // todo no fetch
    // only getters and setters for edits

    public StagingItem(){}

    public StagingItem(JamaItem item) {
        super(item);
    }

    @Override
    protected void copyContentFrom(JamaDomainObject jamaDomainObject) {
        super.copyContentFrom(jamaDomainObject);
    }

    public StagingItem setName(TextFieldValue name) {
        this.name = name;
        return this;
    }

    public StagingItem setGlobalId(String globalId) {
        //TODO need to change the query parameters
        this.globalId = globalId;
        return this;
    }

    public StagingItem setItemType(JamaItemType itemType) {
        this.itemType = itemType;
        return this;
    }

    public StagingItem setChildItemType(JamaItemType childItemType) {
        this.childItemType = childItemType;
        return this;
    }

    public StagingItem setModifiedBy(JamaUser modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public StagingItem addFieldValue(JamaFieldValue fieldValue) {
        fieldValues.add(fieldValue);
        return this;
    }
}
