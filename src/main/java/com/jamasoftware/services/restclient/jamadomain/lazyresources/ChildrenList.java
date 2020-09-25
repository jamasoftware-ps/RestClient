package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.JamaParent;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChildrenList extends LazyCollection {
    private final JamaParent parent;
    private List<JamaItem> children;

    public ChildrenList(JamaParent parent) throws RestClientException {
        this.parent = parent;
        associate(parent.getId(), parent.getJamaInstance());
    }

    @Override
    public String getCollectionUrl() {
        if(parent.isProject()) {
            return "items?project=" + getId() + "&rootOnly=true";
        }
        return "items/" + getId() + "/children";
    }

    @Override
    public void copyContentFrom(List<JamaDomainObject> objects) {
        List<JamaItem> children = new ArrayList<>();
        for(JamaDomainObject object : objects) {
            children.add((JamaItem) object);
        }
        this.children = children;
    }

    public List<JamaItem> getChildren() {
        fetch();
        /*
         * Problems have been reported that children is null at this stage. The reasons are not fully clear, an
         * exception should have been thrown in that case. Anyway, fix is to return an empty list instead.
         */
        return Collections.unmodifiableList(children != null ? children : Collections.emptyList());
    }

    public JamaParent getParent() {
        fetch();
        return parent;
    }
}
