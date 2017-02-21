package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.JamaParent;
import com.jamasoftware.services.restclient.exception.JsonException;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamaclient.JamaPage;
import com.jamasoftware.services.restclient.jamadomain.*;
import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.core.JamaInstance;
import com.jamasoftware.services.restclient.jamadomain.core.LazyResource;
import com.jamasoftware.services.restclient.jamadomain.fields.*;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.*;
import com.jamasoftware.services.restclient.jamadomain.values.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class SimpleJsonDeserializer {

    private JSONParser jsonParser = new JSONParser();
    private SimpleJsonUtil util = new SimpleJsonUtil();

    protected JamaDomainObject deserialize(String json, JamaInstance jamaInstance) throws RestClientException {
        JSONObject jsonObject = util.parseObject(json, jsonParser);
        return typeCheckResource(jsonObject, jamaInstance);
    }

    protected Integer deserializeLocation(String response) throws RestClientException{
        JSONObject jsonObject = util.parseObject(response, jsonParser);
        try {
            JSONObject meta = (JSONObject)jsonObject.get("meta");
            String location = (String)meta.get("location");
            String[] segments = location.split("/");
            String id = segments[segments.length - 1];
            return Integer.valueOf(id);
        } catch (ClassCastException | NumberFormatException | NullPointerException e) {
            return null;
        } catch (Exception e) {
            throw new RestClientException(e);
        }
    }

    private JamaDomainObject typeCheckResource(JSONObject resourceJson, JamaInstance jamaInstance) throws RestClientException {
        System.out.println(resourceJson.toJSONString());
        String type = util.requestString(resourceJson, "type");
        if(type == null) {
            if(resourceJson.get("suspect") != null) {
                return deserializeRelationship(resourceJson, jamaInstance);
            }
            type = "Invalid type.  Default case will catch this String.";
        }
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
            case "relationshiptypes":
                return deserializeRelationshipType(resourceJson, jamaInstance);
            case "picklists":
                return deserializePickList(resourceJson, jamaInstance);
            default:
                throw new JsonException("type not found for object: " + resourceJson.toJSONString());
        }
    }

    private JamaRelationship deserializeRelationship(JSONObject relJson, JamaInstance jamaInstance) throws RestClientException {
        JsonStagingRelationship relationship = new JsonStagingRelationship();

        int toItemId = util.requireInt(relJson, "toItem");
        JamaItem toItem = checkItemPool(toItemId, jamaInstance);
        relationship.setToItem(toItem);
        int fromItemId = util.requireInt(relJson, "fromItem");
        JamaItem fromItem = checkItemPool(fromItemId, jamaInstance);
        relationship.setFromItem(fromItem);

        Integer relationshipTypeId = util.requestInt(relJson, "relationshipType");
        if(relationshipTypeId != null) {
            JamaRelationshipType relType = (JamaRelationshipType)checkPool(JamaRelationshipType.class, relationshipTypeId, jamaInstance);
            relationship.setRelationshipType(relType);
        }

        int relationshipId = util.requireInt(relJson, "id");
        JamaRelationship jamaRelationship = (JamaRelationship)checkPool(JamaRelationship.class, relationshipId, jamaInstance);
        checkIds(relJson, jamaRelationship);
        relationship.writeContentTo(jamaRelationship);

        return jamaRelationship;
    }

    private JamaRelationshipType deserializeRelationshipType(JSONObject relTypeJson, JamaInstance jamaInstance) throws RestClientException {
        JsonStagingRelationshipType relType = new JsonStagingRelationshipType();

        relType.setName(util.requestString(relTypeJson, "name"));
        relType.setDefault(util.requireBoolean(relTypeJson, "isDefault"));

        int relationshipTypeId = util.requireInt(relTypeJson, "id");
        JamaRelationshipType jamaRelType = (JamaRelationshipType)checkPool(JamaRelationshipType.class, relationshipTypeId, jamaInstance);
        checkIds(relTypeJson, jamaRelType);
        relType.writeContentTo(jamaRelType);

        return jamaRelType;
    }

    private Release deserializeRelease(JSONObject releaseJson, JamaInstance jamaInstance) throws RestClientException {
        JsonStagingRelease release = new JsonStagingRelease();
        release.setName(util.requestString(releaseJson, "name"));
        release.setDescription(util.requestString(releaseJson, "description"));

        Integer projectId = util.requireInt(releaseJson, "project");
        JamaProject project = checkProjectPool(projectId, jamaInstance);
        project.associate(projectId, jamaInstance);
        release.setProject(project);

        release.setReleaseDate(util.requestDate(releaseJson, "releaseDate"));
        release.setActive(util.requireBoolean(releaseJson, "active"));
        release.setArchived(util.requireBoolean(releaseJson, "archived"));
        release.setItemCount(util.requestInt(releaseJson, "itemCount"));

        int releaseId = util.requireInt(releaseJson, "id");
        Release jamaRelease = (Release)checkPool(Release.class, releaseId, jamaInstance);
        checkIds(releaseJson, jamaRelease);
        release.writeContentTo(jamaRelease);

        return jamaRelease;
    }

    private PickList deserializePickList(JSONObject pickListJson, JamaInstance jamaInstance) throws RestClientException {
        JsonStagingPickList pickList = new JsonStagingPickList();
        pickList.setName(util.requireString(pickListJson, "name"));
        pickList.setDescription(util.requestString(pickListJson, "description"));
        PickList jamaPickList = (PickList)checkPool(PickList.class, util.requireInt(pickListJson, "id"), jamaInstance);
        checkIds(pickListJson, jamaPickList);
        pickList.writeContentTo(jamaPickList);

        return jamaPickList;
    }

    private PickListOption deserializeOption(JSONObject optionJson, JamaInstance jamaInstance) throws RestClientException {
        JsonStagingPickListOption option = new JsonStagingPickListOption();
        option.setName(util.requestString(optionJson, "name"));
        option.setDescription(util.requestString(optionJson, "description"));
        option.setActive(util.requireBoolean(optionJson, "active"));
        option.setColor(util.requestString(optionJson, "color"));
        option.setDefaultValue(util.requireBoolean(optionJson, "default"));

        int optionId = util.requireInt(optionJson, "id");
        PickListOption jamaOption = (PickListOption)checkPool(PickListOption.class, optionId, jamaInstance);
        checkIds(optionJson, jamaOption);
        option.writeContentTo(jamaOption);

        return jamaOption;
    }

    private JamaUser deserializeUser(JSONObject userJson, JamaInstance jamaInstance) throws RestClientException {
        int userId = util.requireInt(userJson, "id");
        JsonStagingUser user = new JsonStagingUser();

        user.setUsername(util.requireString(userJson, "username"));
        user.setFirstName(util.requestString(userJson, "firstName"));
        user.setLastName(util.requestString(userJson, "lastName"));
        user.setEmail(util.requestString(userJson, "email"));
        user.setPhone(util.requestString(userJson, "phone"));
        user.setTitle(util.requestString(userJson, "title"));
        user.setLocation(util.requestString(userJson, "location"));
        user.setLicenseType(util.requestString(userJson, "licenseType"));
        user.setActive(util.requireBoolean(userJson, "active"));

        JamaUser jamaUser = checkUserPool(userId, jamaInstance);
        checkIds(userJson, jamaUser);
        user.writeContentTo(jamaUser);
        return jamaUser;
    }

    private JamaProject deserializeProject(JSONObject projectJson, JamaInstance jamaInstance) throws RestClientException{
        JsonStagingProject project = new JsonStagingProject();

        project.setFolder(util.requireBoolean(projectJson, "isFolder"));
        project.setCreatedDate(util.requestDate(projectJson, "createdDate"));
        project.setModifiedDate(util.requestDate(projectJson, "modifiedDate"));
        project.setProjectKey(util.requestString(projectJson, "projectKey"));

        int createdById = util.requireInt(projectJson, "createdBy");
        JamaUser createdBy = checkUserPool(createdById, jamaInstance);
        createdBy.associate(util.requireInt(projectJson, "createdBy"), jamaInstance);
        project.setCreatedBy(createdBy);

        Integer modifiedById = util.requestInt(projectJson, "modifiedBy");
        if(modifiedById != null) {
            JamaUser modifiedBy = checkUserPool(modifiedById, jamaInstance);
            modifiedBy.associate(modifiedById, jamaInstance);
            project.setModifiedBy(modifiedBy);
        }

        JSONObject fields = util.requireObject(projectJson, "fields");
        project.setDescription(util.requestString(fields, "description"));
        project.setName(util.requireString(fields, "name"));

        int projectId = util.requireInt(projectJson, "id");
        JamaProject jamaProject = checkProjectPool(projectId, jamaInstance);
        checkIds(projectJson, jamaProject);
        project.writeContentTo(jamaProject);
        return jamaProject;
    }

    private JamaDomainObject checkPool(Class clazz, String id, JamaInstance jamaInstance) throws RestClientException {
        try {
            return checkPool(clazz, Integer.valueOf(id), jamaInstance);
        } catch(NumberFormatException e) {
            throw new JsonException(e);
        }
    }

    private JamaDomainObject checkPool(Class clazz, int id, JamaInstance jamaInstance) throws RestClientException {
        JamaDomainObject jamaDomainObject = jamaInstance.checkPool(clazz, id);
        if(jamaDomainObject != null) {
            return jamaDomainObject;
        }
        try {
            jamaDomainObject = (JamaDomainObject)clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new JsonException(e);
        }
        jamaInstance.addToPool(clazz, id, jamaDomainObject);
        if(jamaDomainObject instanceof LazyResource) {
            ((LazyResource)jamaDomainObject).associate(id, jamaInstance);
        }
        return jamaDomainObject;
    }

    private JamaProject checkProjectPool(int id, JamaInstance jamaInstance) throws RestClientException {
        return (JamaProject)checkPool(JamaProject.class, id, jamaInstance);
    }

    private JamaUser checkUserPool(int id, JamaInstance jamaInstance) throws RestClientException {
        return (JamaUser) checkPool(JamaUser.class, id, jamaInstance);
    }

    private JamaItem checkItemPool(int id, JamaInstance jamaInstance) throws RestClientException {
        return (JamaItem) checkPool(JamaItem.class, id, jamaInstance);
    }

    private JamaItemType checkItemTypePool(int id, JamaInstance jamaInstance) throws RestClientException {
        return (JamaItemType) checkPool(JamaItemType.class, id, jamaInstance);
    }

    private JamaItem deserializeItem(JSONObject itemJson, JamaInstance jamaInstance) throws RestClientException {
        int itemId = util.requireInt(itemJson, "id");
        JsonStagingItem item = new JsonStagingItem();

        item.setGlobalId(util.requireString(itemJson, "globalId"));
        item.setDocumentKey(util.requireString(itemJson, "documentKey"));

        int projectId = util.requireInt(itemJson, "project");
        JamaProject project = checkProjectPool(projectId, jamaInstance);
        project.associate(projectId, jamaInstance);
        item.setProject(project);

        int itemTypeId = util.requireInt(itemJson, "itemType");
        JamaItemType itemType = checkItemTypePool(itemTypeId, jamaInstance);
        itemType.associate(itemTypeId, jamaInstance);
        item.setItemType(itemType);

        Integer childItemTypeId = util.requestInt(itemJson, "childItemType");
        if(childItemTypeId != null) {
            JamaItemType childItemType = checkItemTypePool(childItemTypeId, jamaInstance);
            item.setChildItemType(childItemType);
        }

        item.setCreatedDate(util.requestDate(itemJson, "createdDate"));
        item.setCreatedDate(util.requestDate(itemJson, "modifiedDate"));
        item.setCreatedDate(util.requestDate(itemJson, "lastActivityDate"));

        Integer createdById = util.requestInt(itemJson, "createdBy");
        if(createdById != null) {
            JamaUser createdBy = checkUserPool(createdById, jamaInstance);
            item.setCreatedBy(createdBy);
        }

        Integer modifiedById = util.requestInt(itemJson, "modifiedBy");
        if(modifiedById != null) {
            JamaUser modifiedBy = checkUserPool(modifiedById, jamaInstance);
            item.setCreatedBy(modifiedBy);
        }


        JSONObject fields = util.requireObject(itemJson, "fields");

        for(JamaField field : itemType.getFields()) {
            JamaFieldValue fieldValue = getValueForField(field, fields, itemTypeId);
            if(fieldValue.getName().equals("name")) {
                if(!(fieldValue instanceof TextFieldValue)) throw new JsonException("Name must be a text field.");
                item.setName(((TextFieldValue)fieldValue).getValue());
            } else {
                item.addFieldValue(fieldValue);
            }
        }
        item.setLocation(deserializeLocation(itemJson, jamaInstance));
        item.setLock(deserializeLockStatus(itemJson, jamaInstance));
        JamaItem jamaItem = checkItemPool(itemId, jamaInstance);
        checkIds(itemJson, jamaItem);
        item.writeContentTo(jamaItem);

        return jamaItem;
    }

    private JamaFieldValue getValueForField(JamaField field, JSONObject fields, int itemTypeId) throws JsonException {
        JamaFieldValue fieldValue = field.getValue();
        String fieldName = fieldValue.getName();
        try {
            if(fieldValue instanceof TestCaseStepsFieldValue) {
                ((TestCaseStepsFieldValue) fieldValue).setValue(getStepList(fields, fieldName));
                return fieldValue;
            }
            fieldValue.setValue(util.getFieldValue(fields, fieldName, itemTypeId));
            return fieldValue;
        } catch (RestClientException e) {
            throw new JsonException(e);
        }
    }

    private void checkIds(JSONObject jsonObject, LazyResource resource) throws JsonException{
        if(!util.requireInt(jsonObject, "id").equals(resource.getId())){
            throw new JsonException("Retrieved resource ID did not match existing ID in: " + jsonObject.toJSONString());
        }
    }

    private LockStatus deserializeLockStatus(JSONObject itemJson, JamaInstance jamaInstance) throws RestClientException {
        LockStatus lockStatus = new LockStatus();
        JSONObject lockJson = util.requireObject(itemJson, "lock");
        lockStatus.setLocked(util.requireBoolean(lockJson, "locked"));
        lockStatus.setLastLocked(util.requestDate(lockJson, "lastLockedDate"));
        Integer lockedById = util.requestInt(lockJson, "lockedBy");
        if(lockedById != null) {
            JamaUser lockedBy = checkUserPool(lockedById, jamaInstance);
            lockStatus.setLockedBy(lockedBy);
        }
        return lockStatus;
    }

    private List<TestCaseStep> getStepList(JSONObject fields, String fieldName) {
        List<TestCaseStep> stepList = new ArrayList<>();
        JSONArray steps = (JSONArray)fields.get(fieldName);
        for(Object s : steps) {
            TestCaseStep step = new TestCaseStep();
            JSONObject jsonStep = (JSONObject) s;
            step.setAction((String)jsonStep.get("action"));
            step.setExpectedResult((String)jsonStep.get("expectedResult"));
            step.setNotes((String)jsonStep.get("notes"));
            stepList.add(step);
        }
        return stepList;
    }

    private JamaLocation deserializeLocation(JSONObject itemJson, JamaInstance jamaInstance) throws RestClientException {
        JamaLocation jamaLocation = new JamaLocation();
        JSONObject location = util.requireObject(itemJson, "location");
        jamaLocation.setSortOrder(util.requestInt(location, "sortOrder"));
        jamaLocation.setGlobalSortOrder(util.requestInt(location, "globalSortOrder"));
        jamaLocation.setSequence(util.requestString(location, "sequence"));
        JSONObject parent = util.requireObject(location, "parent");
        JamaParent jamaParent;
        Integer parentId = util.requestInt(parent, "item");
        if(parentId != null) {
            JamaItem parentItem = checkItemPool(parentId, jamaInstance);
            parentItem.associate(parentId, jamaInstance);
            jamaParent = parentItem;
        } else {
            parentId = util.requireInt(parent, "project");
            JamaProject parentProject = checkProjectPool(parentId, jamaInstance);
            parentProject.associate(parentId, jamaInstance);
            jamaParent = parentProject;
        }
        jamaLocation.setParent(jamaParent);

        return jamaLocation;
    }

    private JamaItemType deserializeItemType(JSONObject itemTypeJson, JamaInstance jamaInstance) throws RestClientException {
        String category = util.requestString(itemTypeJson, "category");
        if(category != null && category.equals("CORE")) {
            return null;
        }

        JsonStagingItemType itemType = new JsonStagingItemType();
        itemType.setDisplay(util.requireString(itemTypeJson, "display"));
        itemType.setDisplayPlural(util.requireString(itemTypeJson, "displayPlural"));
        String imageUrl = util.requireString(itemTypeJson, "image");
        ItemTypeImage itemTypeImage = new ItemTypeImage();
        itemTypeImage.setJamaInstance(jamaInstance);
        itemTypeImage.setImageUrl(imageUrl);
        itemType.setImage(itemTypeImage);
//        byte[] imageData;
//        try {
//            imageData = jamaInstance.retrieveItemTypeImage(imageUrl);
//        } catch (RestClientException e) {
//            throw new JsonException(e);
//        }
//        itemType.setImage(imageData);
        itemType.setTypeKey(util.requireString(itemTypeJson, "typeKey"));
        JSONArray fieldsJson = util.requireArray(itemTypeJson, "fields");

        for(Object object : fieldsJson) {
            JamaField field = deserializeField((JSONObject)object, jamaInstance);
            if(field != null) {
                field.setJamaInstance(jamaInstance);
                itemType.addField(field);
            }
        }

        int itemTypeId = util.requireInt(itemTypeJson, "id");
        JamaItemType jamaItemType = checkItemTypePool(itemTypeId, jamaInstance);
        checkIds(itemTypeJson, jamaItemType);
        itemType.writeContentTo(jamaItemType);
        return jamaItemType;
    }




    protected JamaPage getPage(String json, JamaInstance jamaInstance) throws RestClientException {
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
                forceValue(domainObject, LazyBase.class, "shouldFetch", false);
                forceValue(domainObject, LazyBase.class, "lastFetch", System.currentTimeMillis());
            }
            page.addResource(domainObject);
        }

        return page;
    }

    private void forceValue(Object object, Class clazz, String fieldName, Object value) throws JsonException {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch(NoSuchFieldException | IllegalAccessException e) {
            throw new JsonException(e.getClass().toString());
        }
    }

    private JamaField deserializeField(JSONObject fieldJson, JamaInstance jamaInstance) throws RestClientException {
        String type = util.requireString(fieldJson, "fieldType");
        JamaField field = null;
        switch (type) {
            case "DATE":
                field = new DateField(type);
                break;
            case "BOOLEAN":
                field = new FlagField(type);
                break;
            case "INTEGER":
                field = new IntegerField(type);
                break;
            case "MULTI_LOOKUP":
                MultiSelectField multiSelectField = new MultiSelectField(type);
                int multiListId = util.requireInt(fieldJson, "pickList");
                multiSelectField.setPickList((PickList)checkPool(PickList.class, multiListId, jamaInstance));
                field = multiSelectField;
                break;
            case "LOOKUP":
                PickListField pickListField = new PickListField(type);
                int pickListId = util.requireInt(fieldJson, "pickList");
                pickListField.setPickList((PickList)checkPool(PickList.class, pickListId, jamaInstance));
                field = pickListField;
                break;
            case "RELEASE":
                field = new ReleaseField(type);
                break;
            case "URL_STRING":
                field = new URLField(type);
                break;
            case "USER":
                field = new UserField(type);
                break;
            case "TEXT":
                String textType = (String) fieldJson.get("textType");
                if (textType.equals("RICHTEXT")) {
                    field = new RichTextField(textType);
                } else if (textType.equals("TEXTAREA")) {
                    field = new TextBoxField(textType);
                }
                break;
            case "STRING":
                field = new TextField(type);
                break;
            //todo: I'm betting that these are the same
            case "TEST_RUN_STATUS":
            case "TEST_CASE_STATUS":
                field = new TestCaseStatusField(type);
                break;
            case "STEPS":
                field = new TestCaseStepsField(type);
                break;
            case "PROJECT":
                field = new ProjectField(type);
                break;
            case "TIME":
                field = new TimeField(type);
                break;
            case "DOCUMENT_TYPE_ITEM_LOOKUP":
            case "DOCUMENT_TYPE_CATEGORY_ITEM_LOOKUP":
                return null;
            case "ROLLUP":
                field = new RollupField(type);
                break;
            case "CALCULATED":
                field = new CalculatedField(type);
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

}
