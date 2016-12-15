package com.jamasoftware.services.restclient.jamadomain.core;

import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.LazyBase;

import java.io.FileReader;
import java.io.FileWriter;

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

    protected LazyResource post(Class clazz, JamaInstance jamaInstance) throws RestClientException {
        return jamaInstance.post(this, clazz);
    }

    protected LazyResource post(Class clazz) throws RestClientException {
        return jamaInstance.post(this, clazz);
    }

    protected String getEditUrl() throws RestClientException {
        throw new RestClientException("Unable to edit " + this.getClass() + " for item " + this);
    }

    protected String getCreateUrl() throws RestClientException {
        throw new RestClientException("Unable to create a new " + this.getClass());
    }
}
