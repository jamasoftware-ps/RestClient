package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.JamaParent;
import com.jamasoftware.services.restclient.exception.JsonException;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.core.LazyResource;
import com.jamasoftware.services.restclient.jamadomain.TestCaseStep;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.*;
import com.jamasoftware.services.restclient.jamadomain.stagingresources.StagingItem;
import com.jamasoftware.services.restclient.jamadomain.values.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SimpleJsonSerializer implements JsonSerializer {
    private SimpleJsonUtil util = new SimpleJsonUtil();


    public String serializeCreated(JamaDomainObject jamaDomainObject) throws JsonException, RestClientException {
        return serialize(jamaDomainObject, true);
    }

    public String serializeEdited(JamaDomainObject jamaDomainObject) throws JsonException, RestClientException {
        return serialize(jamaDomainObject, false);
    }

    private String serialize(JamaDomainObject object, boolean create) throws JsonException, RestClientException {
        JSONObject jsonObject = null;

        if(object instanceof StagingItem) {
            jsonObject = serializeItem((StagingItem)object, create);
        }
        if(jsonObject == null) {
            throw new JsonException("Unable to serialize " + object.getClass() +
                    " to JSON for " + object);
        }
        return jsonObject.toJSONString();
    }


    private JSONObject serializeItem(StagingItem jamaItem, boolean create) throws JsonException {
        JSONObject payload = serializeItem(jamaItem);

        if(create) {
            if(jamaItem.getParent().isProject()) {
                util.putIfNotNull(payload, "project", getIdOrNull((LazyResource)jamaItem.getParent()));
            }
            util.putIfNotNull(payload, "itemType", getIdOrNull(jamaItem.getItemType()));
        }
        return payload;
    }

    @SuppressWarnings("unchecked")
    private JSONObject serializeItem(StagingItem jamaItem) {
        JSONObject itemJson = new JSONObject();
        JSONObject fields = new JSONObject();
        JSONObject location = new JSONObject();
        JSONObject parent = new JSONObject();

        itemJson.put("fields", fields);
        itemJson.put("location", location);
        location.put("parent", parent);

        fields.put("name", serializeValue(jamaItem.getName()));
        for(JamaFieldValue value : jamaItem.getFieldValues()) {
            util.putIfNotNull(fields, value.getName(), serializeValue(value));
        }

        util.putIfNotNull(itemJson, "childItemType", getIdOrNull(jamaItem.getChildItemType()));
        util.putIfNotNull(itemJson, "project", getIdOrNull(jamaItem.getProject()));

        util.putIfNotNull(itemJson, "globalId", jamaItem.getGlobalId());

        JamaParent jamaParent = jamaItem.getParent();
        if(jamaParent instanceof JamaProject) {
            parent.put("project", ((JamaProject) jamaParent).getId());
        } else if(jamaParent instanceof JamaItem) {
            parent.put("item", ((JamaItem) jamaParent).getId());
        }

        return itemJson;
    }


    private Object serializeValue(JamaFieldValue value) {
        if(value.readOnly()) return null;
        if(value instanceof CalculatedFieldValue) return null;
        if(value instanceof RollupFieldValue) return null;
        if(value instanceof TestCaseStatusFieldValue) return null;
        if(value instanceof RichTextFieldValue) return ((RichTextFieldValue)value).getValue().getValue();
        if(value instanceof MultiSelectFieldValue) {
            MultiSelectFieldValue multiSelect = (MultiSelectFieldValue)value;
            JSONArray options = new JSONArray();
            for(PickListOption option : multiSelect.getValue()) {
                options.add(option.getId());
            }
            return options;
        }
        if(value instanceof PickListFieldValue) {
            return getIdOrNull(((PickListFieldValue)value).getValue());
        }
        if(value instanceof ProjectFieldValue) {
            return getIdOrNull(((ProjectFieldValue)value).getValue());
        }
        if(value instanceof ReleaseFieldValue) {
            return getIdOrNull(((ReleaseFieldValue)value).getValue());
        }
        if(value instanceof TestCaseStepsFieldValue) {
            TestCaseStepsFieldValue steps = (TestCaseStepsFieldValue)value;
            JSONArray stepArray = new JSONArray();
            for(TestCaseStep step : steps.getValue()) {
                stepArray.add(serializeStep(step));
            }
            return stepArray;
        }
        if(value instanceof UserFieldValue) {
            return getIdOrNull(((UserFieldValue)value).getValue());
        }

        return value.getValue();
    }

    private JSONObject serializeStep(TestCaseStep step) {
        JSONObject stepJson = new JSONObject();
        util.putIfNotNull(stepJson, "action", step.getAction());
        util.putIfNotNull(stepJson, "expectedResults", step.getExpectedResult());
        util.putIfNotNull(stepJson, "notes", step.getNotes());
        return stepJson;
    }

    public JSONObject serializeProject(JamaProject jamaProject) {
        JSONObject project = new JSONObject();

        util.putIfNotNull(project, "isFolder", jamaProject.isFolder());
        util.putIfNotNull(project, "projectKey", jamaProject.getProjectKey());
        util.putIfNotNull(project, "parent", 0);

        JSONObject fields = new JSONObject();
        util.putIfNotNull(fields, "name", jamaProject.getName());
        util.putIfNotNull(fields, "description", jamaProject.getDescription());
        util.putIfNotNull(fields, "legacyId", jamaProject.getId());
        util.putIfNotNull(project, "fields", fields);
        return project;
    }

    private Integer getIdOrNull(LazyResource object) {
        return object == null ? null : object.getId();
    }

}
