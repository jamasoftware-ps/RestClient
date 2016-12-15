package com.jamasoftware.services.restclient;

import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.core.JamaInstance;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItem;

import java.util.List;

// Decorator
public interface JamaParent {
    List<JamaItem> getChildren() throws RestClientException;
//    void addChild(JamaItem jamaItem) throws RestClientException;
    boolean isProject();
    void makeChildOf(JamaParent jamaParent) throws RestClientException;
    Integer getId();
    JamaInstance getJamaInstance();
}
