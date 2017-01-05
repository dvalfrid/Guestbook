package net.valfridsson.gastolibro.resources;

import com.google.common.collect.ImmutableMap;
import io.dropwizard.testing.FixtureHelpers;
import io.dropwizard.testing.junit.ResourceTestRule;
import net.valfridsson.gastolibro.GastolibroApplication;
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
    public void findAll() throws Exception {
        EntryDao entryDao = testDB.getDbi().onDemand(EntryDao.class);
        entryDao.insert(EntryDaoTest.getCreateEntry(), 10, "127.0.0.1");
        entryDao.insert(EntryDaoTest.getCreateEntry(), 10, "127.0.0.1");
        entryDao.insert(EntryDaoTest.getCreateEntry(), 10, "127.0.0.1");
        entryDao.insert(EntryDaoTest.getCreateEntry(), 10, "127.0.0.1");
        entryDao.insert(EntryDaoTest.getCreateEntry(), 10, "127.0.0.1");
        entryDao.insert(EntryDaoTest.getCreateEntry(), 10, "127.0.0.1");

        Response response = resources.client().target("/books/10/entries").request().get();
        assertEquals(response.getStatus(), 200);
        JSONAssert.assertEquals(FixtureHelpers.fixture("fixtures/bookFindAll.json"), response.readEntity(String.class), false);
    }

    @Test
    public void findAll_bookDoesNotExist() {
        Response response = resources.client().target("/books/0/entries").request().get();
        assertEquals(response.getStatus(), 404);
    }

    @Test
    public void insert() throws Exception {
        ImmutableMap.Builder<String, Object> builder = new ImmutableMap.Builder<>();
        builder.put("name", "Test Testsson");
        builder.put("headline", "headline");
        builder.put("email", "test@testsson.se");
        builder.put("city", "city");
        builder.put("country", "country");
        builder.put("message", "message");

        Response response = resources.client().target("/books/10/entries").request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(builder.build()));
        Assertions.assertThat(response.getStatus()).isEqualTo(201);
    }
}