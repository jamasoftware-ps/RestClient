package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.core.LazyResource;

import java.util.List;

public class PickList extends LazyResource {
    protected OptionList options;
    protected String name;
    protected String description;

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

    public List<PickListOption> getOptions() throws RestClientException {
        if(options == null) {
            options = new OptionList(this);
        }
        return options.getOptions();
    }

    public String getName() {
        fetch();
        return name;
    }

    public String getDescription() {
        fetch();
        return description;
    }

    @Override
    public String toString() {
        return getName();
    }
}
