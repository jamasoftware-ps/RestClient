package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.JamaParent;
import com.jamasoftware.services.restclient.jamadomain.JamaDomainObject;

import java.util.ArrayList;
import java.util.List;

public class ChildrenList extends LazyCollection {
    private JamaParent parent;
    private List<JamaItem> children;

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
        return children;
    }

    public JamaParent getParent() {
        fetch();
        return parent;
    }

    public void setParent(JamaParent parent) {
        this.parent = parent;
    }
}
