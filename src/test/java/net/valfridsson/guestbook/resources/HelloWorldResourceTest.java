package net.valfridsson.guestbook.resources;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class HelloWorldResourceTest {


    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new HelloWorldResource())
            .build();

    @Test
    public void helloWorld() throws Exception {
        Response response = resources.client().target("/hello-world").request().get();
        Assert.assertEquals(response.getStatus(), 200);
    }

}