package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.lazyresources.PickList;
import com.jamasoftware.services.restclient.jamadomain.values.MultiSelectFieldValue;

public class MultiSelectField extends JamaField {
    private PickList pickList;

    public PickList getPickList() {
        return pickList;
    }

    public void setPickList(PickList pickList) {
        this.pickList = pickList;
    }

    @Override
    public MultiSelectFieldValue getValue() {
        MultiSelectFieldValue multiSelectFieldValue = new MultiSelectFieldValue();
        populateFieldValue(multiSelectFieldValue);
        return multiSelectFieldValue;
    }

    public MultiSelectField(String type) {
        super(type);
    }

    public MultiSelectField() {
        super();
    }

}
