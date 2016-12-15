package com.jamasoftware.services.restclient.jamadomain.stagingresources;

import com.jamasoftware.services.restclient.JamaParent;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.core.JamaInstance;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItem;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItemType;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaProject;

public class StagingFactory {

    protected StagingItem createStagingItem() { return new StagingItem(); }

    protected StagingItem createStagingItem(JamaItem jamaItem) throws RestClientException {
        return new StagingItem(jamaItem);
    }

    protected StagingItem createStagingItem(JamaInstance jamaInstance, String name, JamaParent parent, JamaItemType itemType) throws RestClientException {
        return new StagingItem(jamaInstance, name, parent, itemType);
    }
}
