package net.valfridsson.gastolibro.resources;

import com.google.common.collect.ImmutableMap;
import io.dropwizard.jersey.params.LongParam;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import net.valfridsson.gastolibro.GastolibroApplication;
import net.valfridsson.gastolibro.api.CreateEntry;
import net.valfridsson.gastolibro.core.Book;
import net.valfridsson.gastolibro.core.Entry;
import net.valfridsson.gastolibro.jdbi.BookDao;
import net.valfridsson.gastolibro.jdbi.EntryDao;
import static net.valfridsson.gastolibro.util.HandleException.logException;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    private final GastolibroApplication application;

    public BookResource(GastolibroApplication application) {
        this.application = application;
    }
/*
    @GET
    @Path("/{id}/entries")
    public Response findAll(@PathParam("id") LongParam id) {
        return logException(() -> {
            Optional<Book> book = application.getDbi().onDemand(BookDao.class).findById(id.get());
            return book.map(book1 -> Response.ok(ImmutableMap.of(
                    "book", book1,
                    "entries", application.getDbi().onDemand(EntryDao.class).findAll(id.get())
            )).build()).orElseGet(() -> Response.status(404).entity(ImmutableMap.of(
                    "entity", "book",
                    "id", id.get()
            )).build());
        }, String.format("Calling findAll(%s)", id.get()));
    }

    @POST
    @Path("{id}/entries")
    public Response insert(@PathParam("id") LongParam id, @Valid CreateEntry entry, @Context HttpServletRequest request, @Context UriInfo uriInfo) {
        return logException(() -> {
            Optional<Book> book = application.getDbi().onDemand(BookDao.class).findById(id.get());
            if (book.isPresent()) {
                Entry savedEntry = application.getDbi().onDemand(EntryDao.class).insert(entry, id.get(), request != null ? request.getRemoteAddr() : "unknown");
                return Response
                    .created(uriInfo.getAbsolutePathBuilder().path(Long.toString(savedEntry.id)).build())
                    .entity(savedEntry)
                    .build();
            } else {
                return Response.status(404).entity(ImmutableMap.of(
                    "entity", "book",
                    "id", id.get()
                )).build();
            }
        }, String.format("Calling insert(%s, %s)", id.get(), entry));
    }
 
   */ 
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response RecieveGastroLibroEntry(CreateEntry entry) throws IOException {

      if (entry.name == null)
        return Response.status(400).build();


      // DEt här får inte du Fixa, Jag skall fixa det så for jag förstår hur det är tänkt att de ska fungera.


      System.out.println(entry);

      return Response.status(200).build();

    }
}
