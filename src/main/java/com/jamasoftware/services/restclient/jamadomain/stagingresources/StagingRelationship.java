package com.jamasoftware.services.restclient.jamadomain.stagingresources;

import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItem;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaRelationship;

public class StagingRelationship extends JamaRelationship implements StagingResource {

    public StagingRelationship setToItem(JamaItem toItem) {
        this.toItem = toItem;
        return this;
    }

    public StagingRelationship setFromItem(JamaItem fromItem) {
        this.fromItem = fromItem;
        return this;
    }

    @Override
    public JamaItem getToItem() {
        return toItem;
    }

    @Override
    public JamaItem getFromItem() {
        return fromItem;
    }

    @Override
    protected String getEditUrl() throws RestClientException {
        return "relationships/" + getId();
    }

    @Override
    protected String getCreateUrl() throws RestClientException {
        return "relationships/";
    }
}
