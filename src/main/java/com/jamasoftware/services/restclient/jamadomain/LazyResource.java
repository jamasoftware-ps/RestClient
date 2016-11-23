package com.jamasoftware.services.restclient.jamadomain;

import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.LazyBase;

public abstract class LazyResource extends LazyBase implements JamaDomainObject {
    public void forceFetch() throws RestClientException {
        markFetch();
        copyContentFrom(jamaInstance.getResource(getResourceUrl()));
    }

    protected abstract String getResourceUrl();

    protected abstract void copyContentFrom(JamaDomainObject jamaDomainObject);

    protected abstract void writeContentTo(JamaDomainObject jamaDomainObject);

    public boolean isAssociated() {
        return getId() != null && jamaInstance != null;
    }
    protected void put() throws RestClientException {
        jamaInstance.put(this);
    }

    protected String getEditUrl() throws RestClientException {
        throw new RestClientException("Unable to edit " + this.getClass() + " for item " + this);
    }
}
