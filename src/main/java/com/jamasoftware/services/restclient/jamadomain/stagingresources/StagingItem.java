package com.jamasoftware.services.restclient.jamadomain.stagingresources;

import com.jamasoftware.services.restclient.JamaParent;
import com.jamasoftware.services.restclient.exception.JamaFieldNotFound;
import com.jamasoftware.services.restclient.exception.JamaTypeMismatchException;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.JamaLocation;
import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.core.JamaInstance;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.*;
import com.jamasoftware.services.restclient.jamadomain.values.JamaFieldValue;
import com.jamasoftware.services.restclient.jamadomain.values.TextFieldValue;

import java.io.IOException;
import java.io.OutputStream;

public class StagingItem extends JamaItem implements StagingResource {
    final private JamaItem originatingItem;
    private boolean invalid = false;

    private void testValidity() throws RestClientException {
        if(invalid) {
            throw new RestClientException("Staging item may not be accessed after commit is called.");
        }
    }

    protected StagingItem(){
        originatingItem = null;
    }

    protected StagingItem(JamaItem item) throws RestClientException{
        super(item);
        super.associate(item.getId(), item.getJamaInstance());
        originatingItem = item;
    }

    protected StagingItem(JamaInstance jamaInstance, String name, JamaParent parent, JamaItemType itemType) throws RestClientException{
        setItemType(itemType);
        setName(name);
        this.location = new JamaLocation();
        this.location.setParent(parent);
        this.jamaInstance = jamaInstance;
        originatingItem = null;
    }

    @Override
    protected void copyContentFrom(JamaDomainObject jamaDomainObject) {
        super.copyContentFrom(jamaDomainObject);
    }

    public StagingItem setName(String name) throws RestClientException {
        testValidity();
        if(this.name == null) {
            this.name = (TextFieldValue) itemType.getField("name").getValue();
        }
        this.name.setValue(name);
        return this;
    }


    public StagingItem setGlobalId(String globalId) throws RestClientException {
        testValidity();
        //TODO need to change the query parameters
        this.globalId = globalId;
        return this;
    }

    public StagingItem setItemType(JamaItemType itemType) throws RestClientException {
        testValidity();
        this.itemType = itemType;
        return this;
    }

    public StagingItem setChildItemType(JamaItemType childItemType) throws RestClientException{
        testValidity();
        this.childItemType = childItemType;
        return this;
    }

    public StagingItem setModifiedBy(JamaUser modifiedBy) throws RestClientException {
        testValidity();
        this.modifiedBy = modifiedBy;
        return this;
    }

    public StagingItem addFieldValue(JamaFieldValue fieldValue) throws RestClientException {
        testValidity();
        fieldValues.add(fieldValue);
        return this;
    }

    public StagingItem setFieldValue(String fieldName, Object value) throws RestClientException {
        testValidity();
        if(fieldName.equals("name")) {
            setName(value.toString());
            return this;
        }
        JamaFieldValue replacementValue = itemType.getField(fieldName).getValue();
        replacementValue.setValue(value);
        replaceFieldValue(replacementValue);
        return this;
    }

    public StagingItem setFieldValueQuietly(String fieldName, Object value, OutputStream out) throws IOException, RestClientException{
        testValidity();
        try{
            setFieldValue(fieldName, value);
        } catch(JamaTypeMismatchException | JamaFieldNotFound e) {
            //Deliberately ignoring exception
            if(out != null) {
                out.write(e.getMessage().getBytes());
            }
        }
        return this;
    }

    public StagingItem setFieldValueQuietly(String fieldName, Object value) throws RestClientException {
        testValidity();
        try{
            setFieldValueQuietly(fieldName, value, null);
        } catch(IOException e) {
            // No output stream passed in. No exception will be thrown
        }
        return this;
    }

    private void replaceFieldValue(JamaFieldValue value) throws JamaFieldNotFound {
        for(int i = 0; i < fieldValues.size(); ++i) {
            if(fieldValues.get(i).getName().equals(value.getName())) {
                fieldValues.set(i, value);
                return;
            }
        }
        fieldValues.add(value);
    }

    public JamaItem commit() throws RestClientException {
        testValidity();
        invalid = true; // never to be unset...
        if (getId() == null) {
            return postCommit();
        } else {
            return putCommit();
        }
    }

    private JamaItem postCommit() throws RestClientException {
        return (JamaItem)post(JamaItem.class);
    }

    private JamaItem putCommit() throws RestClientException {
        put();
        invalidate(originatingItem);
        return originatingItem;
    }

    @Override
    protected String getEditUrl() throws RestClientException {
        return "items/" + getId();
    }

    public StagingItem setParent(JamaParent item) throws RestClientException{
        testValidity();
        this.makeChildOf(item);
        return this;
    }

//    public StagingItem addChildToItem(JamaItem jamaItem) throws RestClientException {
//        testValidity();
//        this.addChild(jamaItem);
//        return this;
//    }

    @Override
    public void associate(int id, JamaInstance jamaInstance) throws RestClientException {
        testValidity();
        throw new RestClientException("You cannot associate an item while it is in edit mode.");
    }

    @Override
    protected String getCreateUrl() throws RestClientException {
        return "items";
    }
}
