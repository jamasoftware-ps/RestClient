package com.jamasoftware.services.restclient.JamaDomain.lazyresources;

import com.jamasoftware.services.restclient.JamaDomain.JamaDomainObject;
import com.jamasoftware.services.restclient.JamaDomain.JamaInstance;
import com.jamasoftware.services.restclient.JamaDomain.SerializableJamaDomainObject;
import com.jamasoftware.services.restclient.JamaDomain.fields.Field;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.exception.UnexpectedJamaResponseException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ItemType extends LazyResource {
    private String typeKey;
    private String display;
    private String displayPlural;
    private String imageURL;
    private List<Field> fields = new ArrayList<>();
    private File image;

    @Override
    protected String getResourceUrl() {
        return "itemtypes/" + getId();
    }

    @Override
    protected void copyContentFrom(JamaDomainObject jamaDomainObject) {
        if (!(jamaDomainObject instanceof ItemType)) {
            throw new UnexpectedJamaResponseException("Expecting an Item Type from the Jama server. Instead, got: " + jamaDomainObject);
        }
        ItemType itemType = (ItemType) jamaDomainObject;

        typeKey = itemType.typeKey;
        display = itemType.display;
        displayPlural = itemType.displayPlural;
        imageURL = itemType.imageURL;
        fields = itemType.fields;
    }

    @Override
    public void associate(int id, JamaInstance jamaInstance) {
        super.associate(id, jamaInstance);
        for(Field field : fields) {
            field.setJamaInstance(jamaInstance);
        }
    }

    public String getTypeKey() {
        fetch();
        return typeKey;
    }

    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey;
    }

    public String getDisplay() {
        fetch();
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getDisplayPlural() {
        fetch();
        return displayPlural;
    }

    public void setDisplayPlural(String displayPlural) {
        this.displayPlural = displayPlural;
    }

    public File getImage() {
        fetch();
        throw new NotImplementedException();
        // todo: retrieve the image file
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getImageURL() {
        fetch();
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List<Field> getFields() {
        fetch();
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
