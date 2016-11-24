package com.jamasoftware.services.restclient.jamadomain.values;

import com.jamasoftware.services.restclient.exception.JamaTypeMismatchException;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.PickListOption;
import com.jamasoftware.services.restclient.exception.RestClientException;

import java.util.ArrayList;
import java.util.List;

public class MultiSelectFieldValue extends JamaFieldValue {
    private List<PickListOption> value = new ArrayList<>();

    @Override
    public List<PickListOption> getValue() {
        return value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        if(value == null || value.equals("[]")) {
            return;
        }
        value = value.substring(1, value.length() - 1);

        String[] values = value.split(",");
        for(String optionIdString : values) {
            optionIdString = optionIdString.trim();
            int optionId = Integer.valueOf(optionIdString);
            setValueFromPoolOrNew(PickListOption.class, optionId);
        }
    }

    public void setValue(Object value) throws JamaTypeMismatchException {
        Class[] allowedTypes = {
                List.class,
                PickListOption.class
        };
        checkTypes(allowedTypes, value);
        if(value instanceof PickListOption) {
            this.value.add((PickListOption)value);
            return;
        }
        List<?> list = (List)value;
        List<PickListOption> options = new ArrayList<>();
        for(Object o : list) {
            checkType(PickListOption.class, o);
            options.add((PickListOption)o);
        }
        this.value = options;
    }

    public void setValue(List<PickListOption> value) {
        this.value = value;
    }
}
