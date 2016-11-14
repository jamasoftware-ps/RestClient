package com.jamasoftware.services.restclient.jamadomain.values;

import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.TestCaseStep;

import java.util.ArrayList;
import java.util.List;

public class TestCaseStepsFieldValue extends JamaFieldValue {
    List<TestCaseStep> steps = new ArrayList<>();
    @Override
    public Object getValue() {
        return steps;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        throw new RestClientException("testCaseSteps must call setValue(List<TestCaseStep> steps)");
    }

    public void setValue(List<TestCaseStep> steps) {
        this.steps = steps;
    }
}
