package com.jamasoftware.services.restclient;

import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItem;

import java.util.List;

// Decorator
public interface JamaParent {
    boolean addChild(JamaItem jamaItem);
    List<JamaItem> getChildren();
}
