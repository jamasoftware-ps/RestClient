package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.JamaParent;
import com.jamasoftware.services.restclient.exception.JsonException;
import com.jamasoftware.services.restclient.jamadomain.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.LazyResource;
import com.jamasoftware.services.restclient.jamadomain.TestCaseStep;
import com.jamasoftware.services.restclient.jamadomain.fields.JamaField;
import com.jamasoftware.services.restclient.jamadomain.fields.RollupField;
import com.jamasoftware.services.restclient.jamadomain.fields.TestCaseStatusField;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItem;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaProject;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.PickListOption;
import com.jamasoftware.services.restclient.jamadomain.stagingresources.StagingItem;
import com.jamasoftware.services.restclient.jamadomain.values.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SimpleJsonSerializer implements JsonSerializer {
    private SimpleJsonUtil util = new SimpleJsonUtil();

    public String serialize(JamaDomainObject object) throws JsonException {
        JSONObject jsonObject = null;

        if(object instanceof StagingItem) {
            jsonObject = serializeItem((StagingItem)object);
        }

        if(jsonObject == null) {
            throw new JsonException("Unable to serialize " + object.getClass() +
                    " to JSON for " + object);
        }
        return jsonObject.toJSONString();
    }

    @SuppressWarnings("unchecked")
    public JSONObject serializeItem(StagingItem jamaItem) {
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

    public Object serializeValue(JamaFieldValue value) {
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

    private Integer getIdOrNull(LazyResource object) {
        return object == null ? null : object.getId();
    }

}
