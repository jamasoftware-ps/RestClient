package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OptionList extends LazyCollection {
    private final PickList pickList;
    private List<PickListOption> options = new ArrayList<>();

    public OptionList(PickList pickList) throws RestClientException {
        this.pickList = pickList;
        associate(pickList.getId(), pickList.getJamaInstance());
    }

    @Override
    public String getCollectionUrl() {
        return "picklists/" + getId() + "/options";
    }

    @Override
    public void copyContentFrom(List<JamaDomainObject> objectList) {
        List<PickListOption> options = new ArrayList<>();
        for(JamaDomainObject o : objectList) {
            options.add((PickListOption)o);
        }
        this.options = options;
    }

    public PickList getPickList() {
        fetch();
        return pickList;
    }

    public List<PickListOption> getOptions() {
        fetch();
        return Collections.unmodifiableList(options);
    }
}
