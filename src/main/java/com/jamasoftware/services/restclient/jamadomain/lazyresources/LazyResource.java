package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.jamadomain.JamaDomainObject;
import com.jamasoftware.services.restclient.exception.RestClientException;

public abstract class LazyResource extends LazyBase implements JamaDomainObject {
    public void forceFetch() throws RestClientException {
        markFetch();
        copyContentFrom(jamaInstance.getResource(getResourceUrl()));
    }

    protected abstract String getResourceUrl();

    public abstract void copyContentFrom(JamaDomainObject jamaDomainObject);

    public boolean isAssociated() {
        return getId() != null && jamaInstance != null;
    }

}
