package com.jamasoftware.services.restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.JamaInstance;
import com.jamasoftware.services.restclient.jamadomain.LazyResource;
import com.jamasoftware.services.restclient.jamadomain.fields.JamaField;

import java.util.ArrayList;
import java.util.List;

public class JamaItemType extends LazyResource {
    protected String typeKey;
    protected String display;
    protected String displayPlural;
    protected List<JamaField> fields = new ArrayList<>();
    protected byte[] image;

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


    public byte[] getImage() {
        fetch();
        return image;
    }

    public List<JamaField> getFields() {
        fetch();
        return fields;
    }

    public JamaField getField(String name) {
        fetch();
        for(JamaField field : fields) {
            if(field.getName().equals(name)) {
                return field;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return getDisplay();
    }

    @Override
    protected String getEditUrl() throws RestClientException {
        throw new RestClientException("An attempt was made to edit an Item Type which is not editable. ");
    }
}
