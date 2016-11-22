package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.jamadomain.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.fields.JamaField;
import com.jamasoftware.services.restclient.jamadomain.stagingresources.StagingJamaItemType;

public class JsonStagingItemType extends StagingJamaItemType {

    JsonStagingItemType(){}

    @Override
    public void writeContentTo(JamaDomainObject jamaDomainObject) {
        super.writeContentTo(jamaDomainObject);
    }

    public JsonStagingItemType setTypeKey(String typeKey) {
        this.typeKey = typeKey;
        return this;
    }

    public JsonStagingItemType setDisplay(String display) {
        this.display = display;
        return this;
    }

    public JsonStagingItemType setDisplayPlural(String displayPlural) {
        this.displayPlural = displayPlural;
        return this;
    }

    public JsonStagingItemType setImage(byte[] imageData) {
        this.image = imageData;
        return this;
    }

    public JsonStagingItemType addField(JamaField jamaField){
        this.fields.add(jamaField);
        return this;
    }
}
