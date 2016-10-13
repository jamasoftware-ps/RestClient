package com.jamasoftware.services.restclient.JamaDomain.fields;

import com.jamasoftware.services.restclient.JamaDomain.values.TestCaseStatusFieldValue;

public class TestCaseStatusField extends Field {
    @Override
    public TestCaseStatusFieldValue getValue() {
        TestCaseStatusFieldValue testCaseStatusFieldValue = new TestCaseStatusFieldValue();
        populateFieldValue(testCaseStatusFieldValue);
        return testCaseStatusFieldValue;
    }
}
