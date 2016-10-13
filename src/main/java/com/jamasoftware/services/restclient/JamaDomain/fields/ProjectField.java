package com.jamasoftware.services.restclient.JamaDomain.fields;
import com.jamasoftware.services.restclient.JamaDomain.values.ProjectFieldValue;

public class ProjectField extends Field {
    @Override
    public ProjectFieldValue getValue() {
        ProjectFieldValue projectFieldValue = new ProjectFieldValue();
        populateFieldValue(projectFieldValue);
        return projectFieldValue;
    }
}
