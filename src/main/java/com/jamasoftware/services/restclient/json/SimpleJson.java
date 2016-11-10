package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.JamaParent;
import com.jamasoftware.services.restclient.jamadomain.JamaInstance;
import com.jamasoftware.services.restclient.jamadomain.JamaLocation;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.*;
import com.jamasoftware.services.restclient.jamadomain.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.fields.*;
import com.jamasoftware.services.restclient.jamadomain.values.JamaFieldValue;
import com.jamasoftware.services.restclient.jamadomain.values.RichTextFieldValue;
import com.jamasoftware.services.restclient.jamadomain.values.TextFieldValue;
import com.jamasoftware.services.restclient.jamaclient.JamaPage;
import com.jamasoftware.services.restclient.exception.JsonException;
import com.jamasoftware.services.restclient.exception.RestClientException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SimpleJson implements JsonSerializerDeserializer {
    private JSONParser jsonParser = new JSONParser();
    private SimpleJsonUtil util = new SimpleJsonUtil();

    public JamaDomainObject deserialize(String json, JamaInstance jamaInstance) throws JsonException {
        JSONObject jsonObject = util.parseObject(json, jsonParser);
        return typeCheckResource(jsonObject, jamaInstance);
    }

    private JamaDomainObject typeCheckResource(JSONObject resourceJson, JamaInstance jamaInstance) throws JsonException {
        String type = util.requireString(resourceJson, "type");
        switch(type) {
            case "itemtypes":
                return deserializeItemType(resourceJson, jamaInstance);
            case "items":
                return deserializeItem(resourceJson, jamaInstance);
            case "projects":
                return deserializeProject(resourceJson, jamaInstance);
            case "users":
                return deserializeUser(resourceJson, jamaInstance);
            case "picklistoptions":
                return deserializeOption(resourceJson, jamaInstance);
            case "releases":
                return deserializeRelease(resourceJson, jamaInstance);
            default:
                throw new JsonException("type not found for object: " + resourceJson.toJSONString());
        }
    }

    public String serialize(JamaItem item) throws JsonException {
        throw new NotImplementedException();
    }

    public String serialize(Relationship relationship) throws JsonException {
        throw new NotImplementedException();
    }

    public Relationship deserializeRelationship(String json) throws JsonException {
        throw new NotImplementedException();
    }

    public String serialize(JamaItemType itemType) throws JsonException {
        throw new NotImplementedException();
    }

    public Release deserializeRelease(JSONObject releaseJson, JamaInstance jamaInstance) throws JsonException {
        Release release = new Release();
        release.associate(util.requireInt(releaseJson, "id"), jamaInstance);
        release.setName(util.requestString(releaseJson, "name"));
        release.setDescription(util.requestString(releaseJson, "description"));
        Integer projectId = util.requireInt(releaseJson, "project");
        if(projectId != null) {
            JamaProject project = new JamaProject();
            project.associate(projectId, jamaInstance);
            release.setProject(project);
        }
        release.setReleaseDate(util.requestDate(releaseJson, "releaseDate"));
        release.setActive(util.requireBoolean(releaseJson, "active"));
        release.setArchived(util.requireBoolean(releaseJson, "archived"));
        release.setItemCount(util.requestInt(releaseJson, "itemCount"));
        return release;
    }

    public PickListOption deserializeOption(JSONObject optionJson, JamaInstance jamaInstance) throws JsonException {
        PickListOption option = new PickListOption();
        option.associate(util.requireInt(optionJson, "id"), jamaInstance);
        option.setName(util.requestString(optionJson, "name"));
        option.setDescription(util.requestString(optionJson, "description"));
        option.setActive(util.requireBoolean(optionJson, "active"));
        option.setColor(util.requestString(optionJson, "color"));
        option.setDefaultValue(util.requireBoolean(optionJson, "default"));
        return option;
    }

    public JamaUser deserializeUser(String json, JamaInstance jamaInstance) throws JsonException {
        JSONObject userJson = util.parseObject(json, jsonParser);
        return deserializeUser(userJson, jamaInstance);
    }

    public JamaUser deserializeUser(JSONObject userJson, JamaInstance jamaInstance) throws JsonException {
        JamaUser user = new JamaUser();
        user.associate(util.requireInt(userJson, "id"), jamaInstance);

        user.setUsername(util.requireString(userJson, "username"));
        user.setFirstName(util.requestString(userJson, "firstName"));
        user.setLastName(util.requestString(userJson, "lastName"));
        user.setEmail(util.requestString(userJson, "email"));
        user.setPhone(util.requestString(userJson, "phone"));
        user.setTitle(util.requestString(userJson, "title"));
        user.setLocation(util.requestString(userJson, "location"));
        user.setLicenseType(util.requestString(userJson, "licenseType"));
        user.setActive(util.requireBoolean(userJson, "active"));
        return user;
    }

    public JamaProject deserializeProject(String json, JamaInstance jamaInstance) throws JsonException {
        JSONObject projectJson = util.parseObject(json, jsonParser);
        return deserializeProject(projectJson, jamaInstance);
    }

    private JamaProject deserializeProject(JSONObject projectJson, JamaInstance jamaInstance) throws JsonException {
        JamaProject project = new JamaProject();
        project.associate(util.requireInt(projectJson, "id"), jamaInstance);

        project.setFolder(util.requireBoolean(projectJson, "isFolder"));
        project.setCreatedDate(util.requestDate(projectJson, "createdDate"));
        project.setModifiedDate(util.requestDate(projectJson, "modifiedDate"));
        project.setProjectKey(util.requestString(projectJson, "projectKey"));

        JamaUser createdBy = new JamaUser();
        createdBy.associate(util.requireInt(projectJson, "createdBy"), jamaInstance);
        project.setCreatedBy(createdBy);

        Integer modifiedById = util.requestInt(projectJson, "modifiedBy");
        if(modifiedById != null) {
            JamaUser modifiedBy = new JamaUser();
            modifiedBy.associate(modifiedById, jamaInstance);
            project.setModifiedBy(modifiedBy);
        }

        JSONObject fields = util.requireObject(projectJson, "fields");
        project.setDescription(util.requestString(fields, "description"));
        project.setName(util.requireString(fields, "name"));

        return project;
    }

    public JamaItem deserializeItem(String json, JamaInstance jamaInstance) throws JsonException {
        JSONObject itemJson = util.parseObject(json, jsonParser);
        return deserializeItem(itemJson, jamaInstance);
    }

    private JamaItem deserializeItem(JSONObject itemJson, JamaInstance jamaInstance) throws JsonException {
        JamaItem item = new JamaItem();

        item.setGlobalId(util.requireString(itemJson, "globalId"));
        item.setDocumentKey(util.requireString(itemJson, "documentKey"));

        JamaProject project = new JamaProject();
        project.associate(util.requireInt(itemJson, "project"), jamaInstance);
        item.setProject(project);

        JamaItemType itemType = new JamaItemType();
        itemType.associate(util.requireInt(itemJson, "itemType"), jamaInstance);
        item.setItemType(itemType);

        Integer childItemTypeId = util.requestInt(itemJson, "childItemType");
        if(childItemTypeId != null) {
            JamaItemType childItemType = new JamaItemType();
            childItemType.associate(childItemTypeId, jamaInstance);
            item.setChildItemType(childItemType);
        }

        item.setCreatedDate(util.requestDate(itemJson, "createdDate"));
        item.setCreatedDate(util.requestDate(itemJson, "modifiedDate"));
        item.setCreatedDate(util.requestDate(itemJson, "lastActivityDate"));

        Integer createdById = util.requestInt(itemJson, "createdBy");
        if(createdById != null) {
            JamaUser createdBy = new JamaUser();
            createdBy.associate(createdById, jamaInstance);
            item.setCreatedBy(createdBy);
        }

        Integer modifiedById = util.requestInt(itemJson, "modifiedBy");
        if(modifiedById != null) {
            JamaUser modifiedBy = new JamaUser();
            modifiedBy.associate(modifiedById, jamaInstance);
            item.setCreatedBy(modifiedBy);
        }

        item.setLocation(deserializeLocation(itemJson, jamaInstance));

        JSONObject fields = util.requireObject(itemJson, "fields");

        List<JamaFieldValue> fieldValues = new ArrayList<>();
        for(JamaField field : itemType.getFields()) {
            JamaFieldValue fieldValue = field.getValue();
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

    private JamaLocation deserializeLocation(JSONObject itemJson, JamaInstance jamaInstance) throws JsonException {
        JamaLocation jamaLocation = new JamaLocation();
        JSONObject location = util.requireObject(itemJson, "location");
        jamaLocation.setSortOrder(util.requestInt(location, "sortOrder"));
        jamaLocation.setGlobalSortOrder(util.requestInt(location, "globalSortOrder"));
        jamaLocation.setSequence(util.requestString(location, "sequence"));
        JSONObject parent = util.requireObject(location, "parent");
        JamaParent jamaParent;
        Integer parentId = util.requestInt(parent, "item");
        if(parentId != null) {
            JamaItem parentItem = new JamaItem();
            parentItem.associate(parentId, jamaInstance);
            jamaParent = parentItem;
        } else {
            parentId = util.requireInt(parent, "project");
            JamaProject parentProject = new JamaProject();
            parentProject.associate(parentId, jamaInstance);
            jamaParent = parentProject;
        }
        jamaLocation.setParent(jamaParent);

        return jamaLocation;
    }

    public JamaItemType deserializeItemType(String json, JamaInstance jamaInstance) throws JsonException {
        JSONObject itemTypeJson = util.parseObject(json, jsonParser);
        return deserializeItemType(itemTypeJson, jamaInstance);
    }

    public JamaItemType deserializeItemType(JSONObject itemTypeJson, JamaInstance jamaInstance) throws JsonException {
        JamaItemType itemType = new JamaItemType();
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

        List<JamaField> fields = new ArrayList<>();
        for(Object object : fieldsJson) {
            JamaField field = deserializeField((JSONObject)object);
            if(field != null) {
                field.setJamaInstance(jamaInstance);
                fields.add(field);
            }
        }

        itemType.setFields(fields);
        return itemType;
    }

    public JamaPage getPage(String json, JamaInstance jamaInstance) throws JsonException {
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
        JamaPage page = new JamaPage(startIndex, resultCount, totalResults);

        JSONArray data = util.requireArray(response, "data");
        for(Object object : data) {
            JSONObject resource = (JSONObject)object;
            JamaDomainObject domainObject = typeCheckResource(resource, jamaInstance);
            if(domainObject instanceof LazyResource) {
                try {
                    Field field = LazyResource.class.getDeclaredField("fetched");
                    field.setAccessible(true);
                    field.set(domainObject, true);
                } catch(NoSuchFieldException | IllegalAccessException e) {
                    throw new JsonException(e.getClass().toString());
                }
            }
            page.addResource(domainObject);
        }

        return page;
    }

    private JamaField deserializeField(JSONObject fieldJson) throws JsonException {
        String type = util.requireString(fieldJson, "fieldType");
        JamaField field = null;
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
            case "ROLLUP":
                field = new RollupField();
                break;
            case "CALCULATED":
                field = new CalculatedField();
                break;
        }
        if(field == null) {
            throw new JsonException("JamaField type not recognized: " + type);
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
