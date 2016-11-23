package com.jamasoftware.services.restclient.jamadomain.values;

import com.jamasoftware.services.restclient.exception.JamaTypeMismatchException;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaProject;
import com.jamasoftware.services.restclient.exception.RestClientException;

public class ProjectFieldValue extends JamaFieldValue {
    private JamaProject value;

    @Override
    public JamaProject getValue() {
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
    public void setValue(Object value) throws JamaTypeMismatchException {
        checkType(JamaProject.class, value);
        this.value = (JamaProject)value;
    }
}
