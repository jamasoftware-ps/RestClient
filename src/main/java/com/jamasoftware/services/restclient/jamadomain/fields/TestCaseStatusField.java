package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.TestCaseStatusFieldValue;

public class TestCaseStatusField extends JamaField {
    @Override
    public TestCaseStatusFieldValue getValue() {
        TestCaseStatusFieldValue testCaseStatusFieldValue = new TestCaseStatusFieldValue();
        populateFieldValue(testCaseStatusFieldValue);
        return testCaseStatusFieldValue;
    }

    public TestCaseStatusField(String type) {
        super(type);
    }

    public TestCaseStatusField() {
        super();
    }
}
