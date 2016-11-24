package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.exception.UnexpectedJamaResponseException;
import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.core.JamaInstance;
import com.jamasoftware.services.restclient.jamadomain.stagingresources.StagingResource;

public abstract class LazyBase {
    protected JamaInstance jamaInstance;
    protected boolean shouldFetch = true;
    protected Long lastFetch = null;
    private Integer id;

    public void fetch() {
        if(this instanceof StagingResource) return;
        checkFetched();
        try {
            if (shouldFetch && id != null) {
                forceFetch();
            }
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }

    public void checkType(Class clazz, JamaDomainObject jamaDomainObject) {
        if(this == jamaDomainObject) return;
        if(!clazz.isInstance(jamaDomainObject)) {
            throw new UnexpectedJamaResponseException("Expecting a " + clazz.getName() + " from the Jama server. Instead, got: " + jamaDomainObject.getClass().getName());
        }
    }

    protected void markFetch() {
        shouldFetch = false;
        lastFetch = System.currentTimeMillis();
    }

    public JamaInstance getJamaInstance() {
        return jamaInstance;
    }

    public Integer getId() {
        return id;
    }

    public void associate(int id, JamaInstance jamaInstance) throws RestClientException {
        this.id = id;
        this.jamaInstance = jamaInstance;
    }

    protected void checkFetched() {
        if(jamaInstance == null || lastFetch == null) {
            return;
        }
        if(!shouldFetch && System.currentTimeMillis() - lastFetch > jamaInstance.getResourceTimeOut() * 1000) {
            shouldFetch = true;
        }
    }

    public abstract void forceFetch() throws RestClientException;

    protected void invalidate() {
        shouldFetch = true;
        lastFetch = null;
    }

    protected void invalidate(LazyBase lazyBase) {
        lazyBase.invalidate();
    }
}
