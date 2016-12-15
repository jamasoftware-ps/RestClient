package restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.JamaConfig;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.httpconnection.TestHttpClient;
import com.jamasoftware.services.restclient.jamadomain.core.JamaInstance;
import com.jamasoftware.services.restclient.jamadomain.fields.*;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItemType;
import junit.framework.TestCase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class JamaItemTypeTest extends TestCase {

    public void testItemType() throws RestClientException {
        JamaConfig jamaConfig = new JamaConfig();
        jamaConfig.setBaseUrl("test");
        jamaConfig.setHttpClient(new TestHttpClient());
        jamaConfig.setPassword("");
        jamaConfig.setUsername("");
        jamaConfig.setResourceTimeOut(1);

        JamaInstance jamaInstance = new JamaInstance(jamaConfig);
        JamaItemType jamaItemType= jamaInstance.getItemType(89011);

        assertEquals(89011, jamaItemType.getId().intValue());
        assertEquals("TC", jamaItemType.getTypeKey().toString());
        assertEquals("Test Case", jamaItemType.getDisplay().toString());
        assertEquals("Test Cases", jamaItemType.getDisplayPlural().toString());
        assertEquals(JamaItemType.class, jamaItemType.getClass());
        //TODO: need to getImage and check it is of type ItemTypeImage
        assertEquals(ArrayList.class, jamaItemType.getFields().getClass());
        try {
            refresh(jamaItemType);
            fields(jamaItemType);
        } catch (Exception e) {
            throw new RestClientException(e);
        }
        System.out.println("Done");
    }

    public void refresh(JamaItemType jamaItemType) throws Exception {

        Field field = JamaItemType.class.getDeclaredField("display");
        field.setAccessible(true);
        String oldName = "oldName";
        field.set(jamaItemType, oldName);
        assertEquals("oldName", field.get(jamaItemType));
        TimeUnit.SECONDS.sleep(1);
        assertEquals("Test Case", jamaItemType.getDisplay());
    }

    public void checkField(Class clazz, JamaField fieldValue) {
        assertTrue(clazz.isInstance(fieldValue));
    }

    public void fields(JamaItemType jamaItemType) throws Exception, RestClientException {
        checkField(RichTextField.class, jamaItemType.getField("description"));
        checkField(TextField.class, jamaItemType.getField("name"));
        checkField(TestCaseStatusField.class, jamaItemType.getField("testCaseStatus"));
        checkField(TestCaseStepsField.class, jamaItemType.getField("testCaseSteps"));
        checkField(UserField.class, jamaItemType.getField("assignedTo"));
        checkField(PickListField.class, jamaItemType.getField("priority"));
        checkField(IntegerField.class, jamaItemType.getField("custom_number"));
    }
}
