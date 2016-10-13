package com.jamasoftware.services.restclient.JamaDomain.values;

import com.jamasoftware.services.restclient.JamaDomain.lazyresources.Release;
import com.jamasoftware.services.restclient.exception.RestClientException;

public class ReleaseFieldValue extends FieldValue {
    private Release value;

    @Override
    public Release getValue() {
        return value;
    }

    public void setValue(Release value) {
        this.value = value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        if(value == null) {
            this.value = null;
            return;
        }
        int releaseId = Integer.valueOf(value);
        this.value = new Release();
        this.value.associate(releaseId, getJamaInstance());
    }
}
