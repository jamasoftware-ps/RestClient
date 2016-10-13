package com.jamasoftware.services.restclient.JamaDomain.values;

import com.jamasoftware.services.restclient.JamaDomain.lazyresources.Project;
import com.jamasoftware.services.restclient.exception.RestClientException;

public class ProjectFieldValue extends FieldValue {
    private Project value;

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
        this.value = new Project();
        this.value.associate(projectId, getJamaInstance());
    }
}
