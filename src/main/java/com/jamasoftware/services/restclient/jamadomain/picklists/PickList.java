package com.jamasoftware.services.restclient.jamadomain.picklists;

import com.jamasoftware.services.restclient.jamadomain.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.LazyResource;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.PickListOption;

import java.util.ArrayList;
import java.util.List;

public class PickList extends LazyResource {
    private List<PickListOption> options = new ArrayList<>();
    private String name;
    private String description;

    @Override
    protected String getResourceUrl() {
        return "picklists/" + getId();
    }

    @Override
    protected void copyContentFrom(JamaDomainObject jamaDomainObject) {
        checkType(PickList.class, jamaDomainObject);
        PickList pickList = (PickList)jamaDomainObject;
        name = pickList.name;
        description = pickList.description;
        options = pickList.options;
    }

    @Override
    protected void writeContentTo(JamaDomainObject jamaDomainObject) {
        checkType(PickList.class, jamaDomainObject);
        ((PickList)jamaDomainObject).copyContentFrom(this);
    }

    public List<PickListOption> getOptions() {
        fetch();
        return options;
    }

    public String getName() {
        fetch();
        return name;
    }

    public String getDescription() {
        fetch();
        return description;
    }
}
