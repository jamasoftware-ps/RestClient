package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.JamaDomain.JamaInstance;
import com.jamasoftware.services.restclient.JamaDomain.Location;
import com.jamasoftware.services.restclient.JamaDomain.lazyresources.*;
import com.jamasoftware.services.restclient.JamaDomain.JamaDomainObject;
import com.jamasoftware.services.restclient.JamaDomain.fields.*;
import com.jamasoftware.services.restclient.JamaDomain.values.FieldValue;
import com.jamasoftware.services.restclient.JamaDomain.values.RichTextFieldValue;
import com.jamasoftware.services.restclient.JamaDomain.values.TextFieldValue;
import com.jamasoftware.services.restclient.jamaclient.Page;
import com.jamasoftware.services.restclient.exception.JsonException;
import com.jamasoftware.services.restclient.exception.RestClientException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class SimpleJson implements Json {
    private JSONParser jsonParser = new JSONParser();
    private SimpleJsonUtil util = new SimpleJsonUtil();

    public JamaDomainObject deserialize(String json, JamaInstance jamaInstance) throws JsonException {
        JSONObject jsonObject = util.parseObject(json, jsonParser);
        String type = util.requireString(jsonObject, "type");
        switch(type) {
            case "itemtypes":
                return deserializeItemType(jsonObject, jamaInstance);
            case "items":
                return deserializeItem(jsonObject, jamaInstance);
            case "projects":
                return deserializeProject(jsonObject, jamaInstance);
            default:
                throw new JsonException("type not found for object: " + jsonObject.toJSONString());
        }
    }

    public String serialize(Item item) throws JsonException {
        throw new NotImplementedException();
    }

    public String serialize(Relationship relationship) throws JsonException {
        throw new NotImplementedException();
    }

    public Relationship deserializeRelationship(String json) throws JsonException {
        throw new NotImplementedException();
    }

    public String serialize(ItemType itemType) throws JsonException {
        throw new NotImplementedException();
    }

    public Project deserializeProject(String json, JamaInstance jamaInstance) throws JsonException {
        JSONObject projectJson = util.parseObject(json, jsonParser);
        return deserializeProject(projectJson, jamaInstance);
    }

    private Project deserializeProject(JSONObject projectJson, JamaInstance jamaInstance) throws JsonException {
        Project project = new Project();
        project.associate(util.requireInt(projectJson, "id"), jamaInstance);

        project.setFolder(util.requireBoolean(projectJson, "isFolder"));
        project.setCreatedDate(util.requestDate(projectJson, "createdDate"));
        project.setModifiedDate(util.requestDate(projectJson, "modifiedDate"));
        project.setProjectKey(util.requestString(projectJson, "projectKey"));

        User createdBy = new User();
        createdBy.associate(util.requireInt(projectJson, "createdBy"), jamaInstance);
        project.setCreatedBy(createdBy);

        Integer modifiedById = util.requestInt(projectJson, "modifiedBy");
        if(modifiedById != null) {
            User modifiedBy = new User();
            modifiedBy.associate(modifiedById, jamaInstance);
            project.setModifiedBy(modifiedBy);
        }

        JSONObject fields = util.requireObject(projectJson, "fields");
        project.setDescription(util.requestString(fields, "description"));
        project.setName(util.requireString(fields, "name"));

        return project;
    }

    public Item deserializeItem(String json, JamaInstance jamaInstance) throws JsonException {
        JSONObject itemJson = util.parseObject(json, jsonParser);
        return deserializeItem(itemJson, jamaInstance);
    }

    private Item deserializeItem(JSONObject itemJson, JamaInstance jamaInstance) throws JsonException {
        Item item = new Item();

        item.setGlobalId(util.requireString(itemJson, "globalId"));
        item.setDocumentKey(util.requireString(itemJson, "documentKey"));

        Project project = new Project();
        project.associate(util.requireInt(itemJson, "project"), jamaInstance);
        item.setProject(project);

        ItemType itemType = new ItemType();
        itemType.associate(util.requireInt(itemJson, "itemType"), jamaInstance);
        item.setItemType(itemType);

        Integer childItemTypeId = util.requestInt(itemJson, "childItemType");
        if(childItemTypeId != null) {
            ItemType childItemType = new ItemType();
            childItemType.associate(childItemTypeId, jamaInstance);
            item.setChildItemType(childItemType);
        }

        Integer createdById = util.requestInt(itemJson, "createdBy");
        if(createdById != null) {
            User createdBy = new User();
            createdBy.associate(createdById, jamaInstance);
            item.setCreatedBy(createdBy);
        }

        Integer modifiedById = util.requestInt(itemJson, "modifiedBy");
        if(modifiedById != null) {
            User modifiedBy = new User();
            modifiedBy.associate(modifiedById, jamaInstance);
            item.setCreatedBy(modifiedBy);
        }

        item.setLocation(deserializeLocation(itemJson));

        JSONObject fields = util.requireObject(itemJson, "fields");

        List<FieldValue> fieldValues = new ArrayList<>();
        for(Field field : itemType.getFields()) {
            FieldValue fieldValue = field.getValue();
            try {
                fieldValue.setValue(util.getFieldValue(fields, fieldValue.getName(), item.getItemType().getId()));
            } catch (RestClientException e) {
                throw new JsonException(e);
            }
            fieldValues.add(fieldValue);
            if(fieldValue.getName().equals("name")) {
                item.setName((TextFieldValue)fieldValue);
            } else if(fieldValue.getName().equals("description")) {
                item.setDescription((RichTextFieldValue)fieldValue);
            }
        }
        item.setFieldValues(fieldValues);

        item.associate(util.requireInt(itemJson, "id"), jamaInstance);

        return item;
    }

    private Location deserializeLocation(JSONObject item) {
        return null;
    }

    public ItemType deserializeItemType(String json, JamaInstance jamaInstance) throws JsonException {
        JSONObject itemTypeJson = util.parseObject(json, jsonParser);
        return deserializeItemType(itemTypeJson, jamaInstance);
    }

    public ItemType deserializeItemType(JSONObject itemTypeJson, JamaInstance jamaInstance) throws JsonException {
        ItemType itemType = new ItemType();
        String category = util.requestString(itemTypeJson, "category");
        if(category != null && category.equals("CORE")) {
            return null;
        }
        itemType.associate(util.requireInt(itemTypeJson, "id"), jamaInstance);
        itemType.setDisplay(util.requireString(itemTypeJson, "display"));
        itemType.setDisplayPlural(util.requireString(itemTypeJson, "displayPlural"));
        itemType.setImageURL(util.requireString(itemTypeJson, "image"));
        itemType.setTypeKey(util.requireString(itemTypeJson, "typeKey"));
        JSONArray fieldsJson = util.requireArray(itemTypeJson, "fields");

        List<Field> fields = new ArrayList<>();
        for(Object object : fieldsJson) {
            Field field = deserializeField((JSONObject)object);
            if(field != null) {
                field.setJamaInstance(jamaInstance);
                fields.add(field);
            }
        }

        itemType.setFields(fields);
        return itemType;
    }

    public Page getPage(String json, JamaInstance jamaInstance) throws JsonException {
        JSONObject response = util.parseObject(json, jsonParser);
        JSONObject meta = util.requireObject(response, "meta");
        JSONObject pageInfo = util.requestObject(meta, "pageInfo");
        if(pageInfo == null) {
            // todo handle beta case
            throw new NotImplementedException();
        }
        int startIndex = util.requireInt(pageInfo, "startIndex");
        int resultCount = util.requireInt(pageInfo, "resultCount");
        int totalResults = util.requireInt(pageInfo, "totalResults");
        Page page = new Page(startIndex, resultCount, totalResults);

        JSONArray data = util.requireArray(response, "data");
        for(Object object : data) {
            JSONObject resource = (JSONObject)object;
            JamaDomainObject domainObject = typeCheckResource(resource, jamaInstance);
            if(domainObject != null) {
                page.addResource(domainObject);
            }
        }

        return page;
    }

    private JamaDomainObject typeCheckResource(JSONObject resourceJson, JamaInstance jamaInstance) throws JsonException {
        String type = util.requireString(resourceJson, "type");
        switch(type) {
            case "itemtypes":
                return deserializeItemType(resourceJson.toJSONString(), jamaInstance);
            case "items":
                return deserializeItem(resourceJson.toJSONString(), jamaInstance);
            case "projects":
                return deserializeProject(resourceJson.toJSONString(), jamaInstance);
            default:
                System.out.println(resourceJson.toJSONString());
                return null;
        }
    }

    private Field deserializeField(JSONObject fieldJson) throws JsonException {
        String type = util.requireString(fieldJson, "fieldType");
        Field field = null;
        switch (type) {
            case "DATE":
                field = new DateField();
                break;
            case "BOOLEAN":
                field = new FlagField();
                break;
            case "INTEGER":
                field = new IntegerField();
                break;
            case "MULTI_LOOKUP":
                MultiSelectField multiSelectField = new MultiSelectField();
                multiSelectField.setPicklistId((int) (long) fieldJson.get("pickList"));
                field = multiSelectField;
                break;
            case "LOOKUP":
                PickListField pickListField = new PickListField();
                pickListField.setPicklistId((int) (long) fieldJson.get("pickList"));
                field = pickListField;
                break;
            case "RELEASE":
                field = new ReleaseField();
                break;
            case "URL_STRING":
                field = new URLField();
                break;
            case "USER":
                field = new UserField();
                break;
            case "TEXT":
                String textType = (String) fieldJson.get("textType");
                if (textType.equals("RICHTEXT")) {
                    field = new RichTextField();
                } else if (textType.equals("TEXTAREA")) {
                    field = new TextBoxField();
                }
                break;
            case "STRING":
                field = new TextField();
                break;
            //todo: I'm betting that these are the same
            case "TEST_RUN_STATUS":
            case "TEST_CASE_STATUS":
                field = new TestCaseStatusField();
                break;
            case "STEPS":
                field = new TestCaseStepsField();
                break;
            case "PROJECT":
                field = new ProjectField();
                break;
            case "TIME":
                field = new TimeField();
                break;
            case "DOCUMENT_TYPE_ITEM_LOOKUP":
            case "DOCUMENT_TYPE_CATEGORY_ITEM_LOOKUP":
                return null;
        }
        if(field == null) {
            throw new JsonException("Field type not recognized: " + type);
        }
        field.setId(util.requireInt(fieldJson, "id"));
        field.setName(util.requireString(fieldJson, "name"));
        field.setLabel(util.requireString(fieldJson, "label"));
        field.setReadOnly(util.requireBoolean(fieldJson, "readOnly"));
        field.setRequired(util.requireBoolean(fieldJson, "required"));
        field.setTriggerSuspect(util.requireBoolean(fieldJson, "triggerSuspect"));
        field.setSynchronize(util.requireBoolean(fieldJson, "synchronize"));
        return field;
    }

    @Override
    public String serialize(JamaDomainObject jamaDomainObject) throws JsonException {
        throw new NotImplementedException();
    }
}
