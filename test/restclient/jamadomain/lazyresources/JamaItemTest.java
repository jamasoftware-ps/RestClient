package restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.JamaConfig;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.httpconnection.TestHttpClient;
import com.jamasoftware.services.restclient.jamadomain.core.JamaInstance;
import com.jamasoftware.services.restclient.jamadomain.fields.RichTextField;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItem;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItemType;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaProject;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaUser;
import com.jamasoftware.services.restclient.jamadomain.values.*;
import junit.framework.TestCase;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JamaItemTest extends TestCase {

    public void testRetrieval() throws RestClientException {
        JamaConfig jamaConfig = new JamaConfig();
        jamaConfig.setBaseUrl("test");
        jamaConfig.setHttpClient(new TestHttpClient());
        jamaConfig.setPassword("");
        jamaConfig.setUsername("");
        jamaConfig.setResourceTimeOut(1);

        JamaInstance jamaInstance = new JamaInstance(jamaConfig);
        JamaItem jamaItem = jamaInstance.getItem(2210690);

        assertEquals("Test Item", jamaItem.getName().getValue());
        assertEquals("GID-AFD-113502", jamaItem.getGlobalId());
        assertEquals("Z-AFD-1", jamaItem.getDocumentKey());
        assertEquals(JamaItemType.class, jamaItem.getItemType().getClass());
        assertEquals(JamaProject.class, jamaItem.getProject().getClass());
        assertNull(jamaItem.getChildItemType());
        Date createdDate = new Date(1478733450000L);
        assertEquals(createdDate, jamaItem.getCreatedDate());
        assertNull(jamaItem.getModifiedDate());
        assertEquals(JamaUser.class, jamaItem.getCreatedBy().getClass());
        assertEquals(jamaItem.getSequence(), "1.1");
        //TODO assertEquals(jamaItem.getParent().getName(), "AllFieldsDemos");
        assertFalse(jamaItem.isLocked());
        assertEquals(createdDate, jamaItem.lastLockedDate());
        assertEquals(createdDate, jamaItem.lastLockedDate());
        assertNull(jamaItem.lockedBy());
        assertEquals(2210690, jamaItem.getId().intValue());
        try {
            refresh(jamaItem);
            fields(jamaItem);
        } catch (Exception e) {
            throw new RestClientException(e);
        }

    }

    public void refresh(JamaItem jamaItem) throws Exception {
        Field field = JamaItem.class.getDeclaredField("name");
        field.setAccessible(true);
        TextFieldValue oldNameValue = new TextFieldValue();
        oldNameValue.setValue("oldName");
        field.set(jamaItem, oldNameValue);
        assertEquals("oldName", field.get(jamaItem).toString());
        TimeUnit.SECONDS.sleep(1);
        assertEquals("Test Item", jamaItem.getName().getValue());
    }

    public void checkField(Class clazz, String value, JamaFieldValue fieldValue) {
        assertTrue(clazz.isInstance(fieldValue));
        assertEquals(value, fieldValue.toString());
    }

    public void fields(JamaItem jamaItem) throws Exception {
        checkField(TextFieldValue.class, "Z-AFD-1", jamaItem.getFieldValueByName("documentKey"));
        checkField(RichTextFieldValue.class, "<p>Description</p>\n", jamaItem.getFieldValueByName("description"));
        //TODO: test with multiSelectFieldValue
        checkField(FlagFieldValue.class, "true", jamaItem.getFieldValueByName("flag"));
        assertEquals(new Date(1478592000000L), jamaItem.getFieldValueByName("date").getValue());
        checkField(DateFieldValue.class, "Tue Nov 08 00:00:00 PST 2016", jamaItem.getFieldValueByName("date"));
        checkField(URLFieldValue.class, "http://www.google.com", jamaItem.getFieldValueByName("url"));
        //TODO: test with userFieldValue, PickListFieldValue, ReleaseFieldValue
        checkField(RollupFieldValue.class, "50", jamaItem.getFieldValueByName("rollup"));
        checkField(CalculatedFieldValue.class, "3", jamaItem.getFieldValueByName("calculated"));
    }
}
