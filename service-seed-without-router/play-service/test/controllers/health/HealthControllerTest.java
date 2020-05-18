package controllers.health;

import controllers.BaseControllerTest;
import controllers.TestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.mvc.Result;
import play.test.Helpers;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class HealthControllerTest extends BaseControllerTest {
    TestHelper testHelper;
    public static Application app;
    public static Map<String, String[]> headerMap;

    @Before
    public void setUp() throws Exception {

        testHelper = new TestHelper();
        app = Helpers.fakeApplication();
        Helpers.start(app);
        headerMap = testHelper.getHeaderMap();
    }

    @After
    public void tearDown() throws Exception {
        headerMap = null;
        app = null;
        testHelper = null;
    }
    @Test
    public void testGetHealthSuccess() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("accept", "yes");
        Result result = testHelper.performTest("/health", "GET", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 200);
    }
    @Test
    public void testGetHealthFailure() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("accept", "yes");
        Result result = testHelper.performTest("/health", "POST", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 404);
    }
}