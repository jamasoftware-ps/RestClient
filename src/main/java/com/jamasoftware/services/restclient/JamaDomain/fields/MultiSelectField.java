package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.picklists.PickList;
import com.jamasoftware.services.restclient.jamadomain.values.JamaFieldValue;
import com.jamasoftware.services.restclient.jamadomain.values.MultiSelectFieldValue;

public class MultiSelectField extends JamaField {
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
    public JamaFieldValue getValue() {
        MultiSelectFieldValue multiSelectFieldValue = new MultiSelectFieldValue();
        populateFieldValue(multiSelectFieldValue);
        return multiSelectFieldValue;
    }
}
