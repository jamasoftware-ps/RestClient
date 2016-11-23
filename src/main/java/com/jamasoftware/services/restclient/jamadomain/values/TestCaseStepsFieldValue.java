package com.jamasoftware.services.restclient.jamadomain.values;

import com.jamasoftware.services.restclient.exception.JamaTypeMismatchException;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.TestCaseStep;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.PickListOption;

import java.util.ArrayList;
import java.util.List;

public class TestCaseStepsFieldValue extends JamaFieldValue {
    List<TestCaseStep> steps = new ArrayList<>();
    @Override
    public List<TestCaseStep> getValue() {
        return steps;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        throw new RestClientException("testCaseSteps must call setValue(List<TestCaseStep> steps)");
    }
    public void setValue(Object value) throws JamaTypeMismatchException {
        checkType(List.class, value);
        List<?> list = (List)value;
        List<TestCaseStep> testCaseSteps = new ArrayList<>();
        for(Object o : list) {
            checkType(TestCaseStep.class, o);
            testCaseSteps.add((TestCaseStep) o);
        }
        this.steps = testCaseSteps;
    }
}
