package net.valfridsson.gastolibro.resources;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.dropwizard.testing.FixtureHelpers;
import io.dropwizard.testing.junit.ResourceTestRule;
import net.valfridsson.gastolibro.GastolibroApplication;
import net.valfridsson.gastolibro.core.Entry;
import net.valfridsson.gastolibro.jdbi.EntryDao;
import net.valfridsson.gastolibro.jdbi.EntryDaoTest;
import net.valfridsson.gastolibro.jdbi.TestDB;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BookResourceTest {

    @Rule
    public TestDB testDB = new TestDB();

    private static GastolibroApplication application = Mockito.mock(GastolibroApplication.class);

    @Before
    public void before() {
        initMocks(application);
        when(application.getDbi()).thenReturn(testDB.getDbi());
    }

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
        .addResource(new BookResource(application))
        .build();

    @Test
    public void insert() throws Exception {

        ImmutableList<Entry> entries = testDB.getDbi().onDemand(EntryDao.class).findAll(10);
        assertThat(entries.stream().filter(e -> e.email.equals("test@testsson.se")).count()).isEqualTo(0);

        ImmutableMap.Builder<String, Object> builder = new ImmutableMap.Builder<>();
        builder.put("name", "Test Testsson");
        builder.put("headline", "headline");
        builder.put("email", "test@testsson.se");
        builder.put("city", "city");
        builder.put("country", "country");
        builder.put("message", "message");

        Response response = resources.client().target("/books/10/entries").request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(builder.build()));
        assertThat(response.getStatus()).isEqualTo(201);
        entries = testDB.getDbi().onDemand(EntryDao.class).findAll(10);
        assertThat(entries.stream().filter(e -> e.email.equals("test@testsson.se")).count()).isEqualTo(1);
    }


}
