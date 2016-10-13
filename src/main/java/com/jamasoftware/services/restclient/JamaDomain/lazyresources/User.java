package com.jamasoftware.services.restclient.JamaDomain.lazyresources;

import com.jamasoftware.services.restclient.JamaDomain.JamaDomainObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class User extends LazyResource {
    @Override
    protected String getResourceUrl() {
        throw new NotImplementedException();
    }

    @Override
    protected void copyContentFrom(JamaDomainObject jamaDomainObject) {
        throw new NotImplementedException();
    }
}
