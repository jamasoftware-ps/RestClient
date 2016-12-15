package restclient.jamadomain.lazyresources;

import com.jamasoftware.services.restclient.JamaConfig;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.httpconnection.TestHttpClient;
import com.jamasoftware.services.restclient.jamadomain.core.JamaInstance;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItem;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaProject;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaUser;
import junit.framework.TestCase;

import java.util.Date;
import java.util.List;

public class JamaProjectTest extends TestCase {

    public void testProject() throws RestClientException {

        JamaConfig jamaConfig = new JamaConfig();
        jamaConfig.setBaseUrl("test");
        jamaConfig.setHttpClient(new TestHttpClient());
        jamaConfig.setPassword("");
        jamaConfig.setUsername("");
        jamaConfig.setResourceTimeOut(1);

        JamaInstance jamaInstance = new JamaInstance(jamaConfig);
        JamaProject jamaProject = jamaInstance.getProject(20183);

        assertEquals("CoveragePlus - Agile", jamaProject.getName());
        assertEquals("CPA", jamaProject.getProjectKey());
        assertEquals(20183, jamaProject.getId().intValue());
        assertEquals("This project is constructed using artifacts that are most common when using an Agile project methodology.",
                jamaProject.getDescription());
        assertTrue(Date.class.isInstance(jamaProject.getCreatedDate()));
        Date createdDate = new Date(1323785140000L);
        assertEquals(createdDate, jamaProject.getCreatedDate());
        assertEquals(JamaProject.class, jamaProject.getClass());
        assertFalse(jamaProject.isFolder());
        assertTrue(JamaUser.class.isInstance(jamaProject.getCreatedBy()));
        List<JamaItem> children = jamaProject.getChildren();
        System.out.println("done");

//        assertFalse(jamaProject.isLocked());
//        assertEquals(createdDate, jamaProject.lastLockedDate());
//        assertEquals(createdDate, jamaProject.lastLockedDate());
//        assertNull(jamaProject.lockedBy());
//        assertEquals(2210690, jamaProject.getId().intValue());
//        try {
//            refresh(jamaProject);
//            fields(jamaProject);
//        } catch (Exception e) {
//            throw new RestClientException(e);
//        }

    }
}
