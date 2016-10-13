package com.jamasoftware.services.restclient.JamaDomain.fields;

import com.jamasoftware.services.restclient.JamaDomain.picklists.PickList;
import com.jamasoftware.services.restclient.JamaDomain.values.FieldValue;
import com.jamasoftware.services.restclient.JamaDomain.values.PickListFieldValue;

public class PickListField extends Field {
    private int picklistId;
    private PickList pickList;

    public int getPicklistId() {
        return picklistId;
    }

    public void setPicklistId(int picklistId) {
        this.picklistId = picklistId;
    }

    public PickList getPickList() {
        return pickList;
    }

    public void setPickList(PickList pickList) {
        this.pickList = pickList;
    }

    @Override
    public FieldValue getValue() {
        PickListFieldValue pickListFieldValue = new PickListFieldValue();
        populateFieldValue(pickListFieldValue);
        return pickListFieldValue;
    }
}
