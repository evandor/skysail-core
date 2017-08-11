package io.skysail.core.it;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationTests3 {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    protected Bundle thisBundle = FrameworkUtil.getBundle(this.getClass());

    private CloseableHttpClient httpclient = HttpClients.createDefault();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TestRule watcher = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            log.info("");
            log.info("--------------------------------------------");
            log.info("{}running test '{}'", "TEST", description.getMethodName());
            log.info("--------------------------------------------");
            log.info("");
        }
    };

    @Test
    public void retrieves_json_representation_containing_the_root_application_for_the_appsEndpoint() throws Exception {
        String responseBody = get("http://localhost:8080/root/apps");
        assertTrue(responseBody.contains("{\"name\":\"root\",\"context\":\"/root\"}"));
    }

    @Test
    public void stopping_and_starting_demo_bundle_does_not_break_anything() throws Exception {
        stopAndStartBundle("skysail.app.demo");
        String responseBody = get("http://localhost:8080/root/apps");
        assertTrue(responseBody.contains("{\"name\":\"root\",\"context\":\"/root\"}"));
    }

    private void stopAndStartBundle(String symbolicName) throws BundleException {
        Bundle[] bundles = FrameworkUtil.getBundle(this.getClass()).getBundleContext().getBundles();
        Bundle bundle = Arrays.stream(bundles)
                .filter(b -> symbolicName.equals(b.getSymbolicName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());

        log.info("stopping bundle " + bundle.getSymbolicName());
        bundle.stop();
        log.info("starting bundle " + bundle.getSymbolicName());
        bundle.start();
    }

    protected void stopAndStartBundle(Class<?> cls) throws BundleException {
        Bundle bundle = FrameworkUtil.getBundle(cls);
        log.info("stopping bundle " + bundle.getSymbolicName());
        bundle.stop();
        log.info("starting bundle " + bundle.getSymbolicName());
        bundle.start();
    }

    private String get(String path) throws ClientProtocolException, IOException {
        HttpGet httpget = new HttpGet(path);
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            @Override
            public String handleResponse(
                    HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }

        };
        String responseBody = httpclient.execute(httpget, responseHandler);
        System.out.println("--------------------------------------------------------");
        System.out.println(responseBody);
        System.out.println("--------------------------------------------------------");
        return responseBody;
    }

}
