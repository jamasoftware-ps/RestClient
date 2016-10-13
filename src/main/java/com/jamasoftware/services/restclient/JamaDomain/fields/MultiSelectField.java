package com.jamasoftware.services.restclient.JamaDomain.fields;

import com.jamasoftware.services.restclient.JamaDomain.picklists.PickList;
import com.jamasoftware.services.restclient.JamaDomain.values.FieldValue;
import com.jamasoftware.services.restclient.JamaDomain.values.MultiSelectFieldValue;

public class MultiSelectField extends Field {
    private PickList pickList;
    private int picklistId;

    public PickList getPickList() {
        return pickList;
    }

    public void setPickList(PickList pickList) {
        this.pickList = pickList;
    }

    public int getPicklistId() {
        return picklistId;
    }

    public void setPicklistId(int picklistId) {
        this.picklistId = picklistId;
    }

    @Override
    public FieldValue getValue() {
        MultiSelectFieldValue multiSelectFieldValue = new MultiSelectFieldValue();
        populateFieldValue(multiSelectFieldValue);
        return multiSelectFieldValue;
    }
}
