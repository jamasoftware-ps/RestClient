package com.jamasoftware.services.restclient.JamaDomain.fields;

import com.jamasoftware.services.restclient.JamaDomain.values.TestCaseStepsFieldValue;

public class TestCaseStepsField extends Field {
    @Override
    public TestCaseStepsFieldValue getValue() {
        TestCaseStepsFieldValue testCaseStepsFieldValue = new TestCaseStepsFieldValue();
        populateFieldValue(testCaseStepsFieldValue);
        return testCaseStepsFieldValue;
    }
}
