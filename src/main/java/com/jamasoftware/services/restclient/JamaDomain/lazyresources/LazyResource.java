package com.jamasoftware.services.restclient.JamaDomain.lazyresources;

import com.jamasoftware.services.restclient.JamaDomain.JamaDomainObject;
import com.jamasoftware.services.restclient.JamaDomain.JamaInstance;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.exception.UnexpectedJamaResponseException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class LazyResource implements JamaDomainObject {
    private JamaInstance jamaInstance;
    private Integer id;
    private boolean fetched = false;

    public void fetch() {
        try {
            if (!fetched && id != null) {
                copyContentFrom(jamaInstance.getResource(getResourceUrl()));
                fetched = true;
            }
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }

    protected abstract String getResourceUrl();

    protected abstract void copyContentFrom(JamaDomainObject jamaDomainObject);

    public void associate(int id, JamaInstance jamaInstance) {
        this.id = id;
        this.jamaInstance = jamaInstance;
    }

    public void create(JamaInstance jamaInstance) {
        // todo: post item and populate id
        throw new NotImplementedException();
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
}
