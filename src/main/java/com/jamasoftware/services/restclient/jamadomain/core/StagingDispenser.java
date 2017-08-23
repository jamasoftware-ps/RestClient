package com.jamasoftware.services.restclient.jamadomain.core;

import com.jamasoftware.services.restclient.JamaParent;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItem;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItemType;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaProject;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaRelationship;
import com.jamasoftware.services.restclient.jamadomain.stagingresources.StagingFactory;
import com.jamasoftware.services.restclient.jamadomain.stagingresources.StagingItem;
import com.jamasoftware.services.restclient.jamadomain.stagingresources.StagingRelationship;

public class StagingDispenser extends StagingFactory {

    StagingDispenser() {}

    @Override
    protected StagingItem createStagingItem() {
        return super.createStagingItem();
    }

    @Override
    protected StagingItem createStagingItem(JamaItem jamaItem) throws RestClientException {
        return super.createStagingItem(jamaItem);
    }

    @Override
    protected StagingItem createStagingItem(JamaInstance jamaInstance, String name, JamaParent parent, JamaItemType itemType) throws RestClientException {
        return super.createStagingItem(jamaInstance, name, parent, itemType);
    }


    @Override
    protected StagingRelationship createStagingRelationship() {
        return super.createStagingRelationship();
    }

    @Override
    protected StagingRelationship createStagingRelationship(JamaRelationship jamaRelationship) throws RestClientException {
        return super.createStagingRelationship(jamaRelationship);
    }
}
