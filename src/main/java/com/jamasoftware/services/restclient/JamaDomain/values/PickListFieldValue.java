package com.jamasoftware.services.restclient.JamaDomain.values;

import com.jamasoftware.services.restclient.JamaDomain.lazyresources.PickListOption;
import com.jamasoftware.services.restclient.exception.RestClientException;

public class PickListFieldValue extends FieldValue {
    private PickListOption value;

    @Override
    public PickListOption getValue() {
        return value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        if(value == null) {
            this.value = null;
            return;
        }
        int optionId = Integer.valueOf(value);
        PickListOption pickListOption = new PickListOption();
        pickListOption.associate(optionId, getJamaInstance());
    }

    public void setValue(PickListOption value) {
        this.value = value;
    }
}
