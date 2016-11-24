package com.jamasoftware.services.restclient.jamadomain.values;

import com.jamasoftware.services.restclient.jamadomain.fields.PickListField;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.PickListOption;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.util.CompareUtil;

import java.util.List;

public class PickListFieldValue extends JamaFieldValue {
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
        setValueFromPoolOrNew(PickListOption.class, optionId);
    }

    public void setValue(Object value) throws RestClientException {
        Class[] allowedTypes = {
                PickListOption.class,
                String.class
        };
        checkTypes(allowedTypes, value);
        if(value instanceof String) {
            value = getOption((String)value);
        }
        this.value = (PickListOption) value;
    }

    public void setValue(PickListOption value) {
        this.value = value;
    }

    public List<PickListOption> getOptions() throws RestClientException {
        return ((PickListField)field).getPickList().getOptions();
    }

    public PickListOption getOption(String optionName) throws RestClientException {
        PickListOption found = null;
        for(PickListOption option : getOptions()) {
            if(CompareUtil.closeEnough(option.getName(), optionName)) {
                if(found != null) {
                    throw new RestClientException("More than one pickListOption closely matches the string: \"" + optionName + "\" in pickList \"" + ((PickListField)field).getPickList() + "\"");
                }
                found = option;
            }
        }
        if(found == null) {
            throw new RestClientException("No pickListOptions matched the string: \"" + optionName + "\" in pickList \"" + ((PickListField)field).getPickList() + "\"");
        }
        return found;
    }
}
