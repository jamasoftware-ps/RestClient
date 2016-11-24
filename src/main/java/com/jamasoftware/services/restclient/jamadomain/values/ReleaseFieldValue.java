package com.jamasoftware.services.restclient.jamadomain.values;

import com.jamasoftware.services.restclient.exception.JamaTypeMismatchException;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.Release;
import com.jamasoftware.services.restclient.exception.RestClientException;

public class ReleaseFieldValue extends JamaFieldValue {
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
        setValueFromPoolOrNew(Release.class, releaseId);
    }

    public void setValue(Object value) throws JamaTypeMismatchException {
        checkType(Release.class, value);
        this.value = (Release) value;
    }
}
