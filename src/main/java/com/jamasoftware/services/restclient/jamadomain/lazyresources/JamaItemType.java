package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.exception.JsonException;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.core.JamaInstance;
import com.jamasoftware.services.restclient.jamadomain.core.LazyResource;
import com.jamasoftware.services.restclient.jamadomain.fields.JamaField;
import com.jamasoftware.services.restclient.json.ItemTypeImage;
import com.jamasoftware.services.restclient.util.CompareUtil;

import java.util.ArrayList;
import java.util.List;

public class JamaItemType extends LazyResource {
    protected String typeKey;
    protected String display;
    protected String displayPlural;
    protected List<JamaField> fields = new ArrayList<>();
//    protected byte[] image;
    protected ItemTypeImage image;

    public void checkType(JamaDomainObject jamaDomainObject){
        checkType(JamaItemType.class, jamaDomainObject);
    }
    @Override
    protected String getResourceUrl() {
        return "itemtypes/" + getId();
    }

    @Override
    public void copyContentFrom(JamaDomainObject jamaDomainObject) {
        checkType(jamaDomainObject);

        JamaItemType itemType = (JamaItemType) jamaDomainObject;
        typeKey = itemType.typeKey;
        display = itemType.display;
        displayPlural = itemType.displayPlural;
        fields = itemType.fields;
        image = itemType.image;
    }

    @Override
    protected void writeContentTo(JamaDomainObject jamaDomainObject) {
        checkType(jamaDomainObject);
        ((JamaItemType)jamaDomainObject).copyContentFrom(this);
    }

    @Override
    public void associate(int id, JamaInstance jamaInstance) throws RestClientException{
        super.associate(id, jamaInstance);
        for(JamaField field : fields) {
            field.setJamaInstance(jamaInstance);
        }
    }

    public String getTypeKey() {
        fetch();
        return typeKey;
    }

    public String getDisplay() {
        fetch();
        return display;
    }


    public String getDisplayPlural() {
        fetch();
        return displayPlural;
    }


    public byte[] getImage() throws JsonException {
        fetch();
        return image.getImage();
    }

    public List<JamaField> getFields() {
        fetch();
        return fields;
    }

    public JamaField getField(String name) throws RestClientException {
        fetch();
        for(JamaField field : fields) {
            if(CompareUtil.closeEnough(field.getName(), name)) {
                return field;
            }
        }
        throw new RestClientException("Unable to locate field with name \"" + name + "\" in Item Type " + this);
    }

    @Override
    public String toString() {
        //TODO fix this or replace it
        return "debug item type";
//        return getDisplay();
    }

    @Override
    protected String getEditUrl() throws RestClientException {
        throw new RestClientException("An attempt was made to edit an Item Type which is not editable. ");
    }
}
