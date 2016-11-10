package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.picklists.PickList;
import com.jamasoftware.services.restclient.jamadomain.values.PickListFieldValue;

public class PickListField extends JamaField {
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
    public PickListFieldValue getValue() {
        PickListFieldValue pickListFieldValue = new PickListFieldValue();
        populateFieldValue(pickListFieldValue);
        return pickListFieldValue;
    }
}
