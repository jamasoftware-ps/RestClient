package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;

import java.util.List;

public abstract class LazyCollection extends LazyBase {

    @Override
    public void forceFetch() throws RestClientException {
        markFetch();
        copyContentFrom(jamaInstance.getResourceCollection(getCollectionUrl()));
    }

    public abstract String getCollectionUrl();
    public abstract void copyContentFrom(List<JamaDomainObject> objectList);
}
