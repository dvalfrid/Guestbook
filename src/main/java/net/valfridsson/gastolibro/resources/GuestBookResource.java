package net.valfridsson.gastolibro.resources;

import com.google.common.collect.ImmutableMap;
import io.dropwizard.jersey.params.LongParam;
import net.valfridsson.gastolibro.GastolibroApplication;
import net.valfridsson.gastolibro.core.GuestBook;
import net.valfridsson.gastolibro.jdbi.EntryDao;
import net.valfridsson.gastolibro.jdbi.GuestBookDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
public class GuestBookResource {

    private final GastolibroApplication application;

    public GuestBookResource(GastolibroApplication application) {
        this.application = application;
    }

    @GET
    @Path("/{id}/entries")
    public Response findAll(@PathParam("id")LongParam id) {
        Optional<GuestBook> book = application.getDbi().onDemand(GuestBookDao.class).findById(id.get());
        if(book.isPresent()) {
            return Response.ok(ImmutableMap.of(
                "book", book.get(),
                "entries", application.getDbi().onDemand(EntryDao.class).findAll(10)
            )).build();
        } else {
            return Response.status(404).entity(ImmutableMap.of(
                "entity", "book",
                "id", id.get()
            )).build();
        }
    }
}
