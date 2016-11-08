package com.jamasoftware.services.restclient.jamadomain.values;

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
            PickListOption pickListOption = new PickListOption();
            pickListOption.associate(optionId, getJamaInstance());
        }
//        //todo needs to be a list of.  probably just string cat the numbers even though it's bad
//        throw new NotImplementedException();
    }

    public void setValue(List<PickListOption> value) {
        this.value = value;
    }
}
