package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.TestCaseStepsFieldValue;

public class TestCaseStepsField extends JamaField {
    @Override
    public TestCaseStepsFieldValue getValue() {
        TestCaseStepsFieldValue testCaseStepsFieldValue = new TestCaseStepsFieldValue();
        populateFieldValue(testCaseStepsFieldValue);
        return testCaseStepsFieldValue;
    }

    @Override
    public String getType() {
        return "TestCaseSteps Field";
    }
}
