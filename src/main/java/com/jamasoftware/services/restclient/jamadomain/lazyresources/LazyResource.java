package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.exception.UnexpectedJamaResponseException;
import com.jamasoftware.services.restclient.jamadomain.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.JamaInstance;
import com.jamasoftware.services.restclient.exception.RestClientException;

public abstract class LazyResource implements JamaDomainObject {
    private JamaInstance jamaInstance;
    private Integer id;
    private boolean shouldFetch = true;
    private Long lastFetch = null;

    public void fetch() {
        checkFetched();
        try {
            if (shouldFetch && id != null) {
                shouldFetch = false;
                lastFetch = System.currentTimeMillis();
                copyContentFrom(jamaInstance.getResource(getResourceUrl()));
            }
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }

    protected abstract String getResourceUrl();

    public abstract void copyContentFrom(JamaDomainObject jamaDomainObject);

    public void associate(int id, JamaInstance jamaInstance) {
        this.id = id;
        this.jamaInstance = jamaInstance;
    }

    public Integer getId() {
        return id;
    }

    public JamaInstance getJamaInstance() {
        return jamaInstance;
    }

    public boolean isAssociated() {
        return id != null && jamaInstance != null;
    }

    public void checkType(Class clazz, JamaDomainObject jamaDomainObject) {
        if(!clazz.isInstance(jamaDomainObject)) {
            throw new UnexpectedJamaResponseException("Expecting a " + clazz.getName() + " from the Jama server. Instead, got: " + jamaDomainObject.getClass().getName());
        }
    }

    private void checkFetched() {
        if(jamaInstance == null || lastFetch == null) {
            return;
        }
        if(!shouldFetch && System.currentTimeMillis() - lastFetch > jamaInstance.getResourceTimeOut() * 1000) {
            shouldFetch = true;
        }
    }
}
