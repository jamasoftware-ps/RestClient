package com.jamasoftware.services.restclient.jamadomain.values;

import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaProject;
import com.jamasoftware.services.restclient.exception.RestClientException;

public class ProjectFieldValue extends JamaFieldValue {
    private JamaProject value;

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        if(value == null) {
            this.value = null;
            return;
        }
        int projectId = Integer.valueOf(value);
        this.value = new JamaProject();
        this.value.associate(projectId, getJamaInstance());
    }
}
