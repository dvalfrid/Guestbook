package net.valfridsson.gastolibro.resources;

import com.google.common.collect.ImmutableMap;
import io.dropwizard.jersey.params.LongParam;
import net.valfridsson.gastolibro.GastolibroApplication;
import net.valfridsson.gastolibro.api.CreateEntry;
import net.valfridsson.gastolibro.api.CreateUpdateRequest;
import net.valfridsson.gastolibro.core.Book;
import net.valfridsson.gastolibro.core.Entry;
import net.valfridsson.gastolibro.core.UpdateRequest;
import net.valfridsson.gastolibro.jdbi.BookDao;
import net.valfridsson.gastolibro.jdbi.EntryDao;
import net.valfridsson.gastolibro.api.CreateUpdateResponse;
import net.valfridsson.gastolibro.core.UpdateResponse;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Optional;
import java.util.stream.Collectors;

import static net.valfridsson.gastolibro.util.HandleException.logException;

@Path("/books/{bookId}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    private final GastolibroApplication application;

    public BookResource(GastolibroApplication application) {
        this.application = application;
    }

    @GET
    @Path("/{id}/entries")
    public Response xEntriesDateRestricted(@PathParam("id") LongParam id, @Valid CreateUpdateRequest UpdateRequest) {
      return Response.ok
        (UpdateResponse
         .newBuilder()
         .entries(
           application
           .getDbi()
           .onDemand(EntryDao.class)
           .findX(UpdateRequest.id, UpdateRequest.x, UpdateRequest.date)
           ).build())
        .build();
    }


    @GET
    @Path("/{id}/entries")
    public Response findAll(@PathParam("id") LongParam id) {
        return logException(() -> {
            Optional<Book> book = application.getDbi().onDemand(BookDao.class).findById(id.get());
            return book.map(book1 -> Response.ok(ImmutableMap.of(
                    "book", book1,
                    "entries", application.getDbi().onDemand(EntryDao.class).findAll(id.get()).stream().limit(5).collect(Collectors.toList())
            )).build()).orElseGet(() -> Response.status(404).entity(ImmutableMap.of(
                    "entity", "book",
                    "id", id.get()
            )).build());
        }, String.format("Calling findAll(%s)", id.get()));
    }


    @POST
    @Path("/entries")
    public Response dataBaseInsert(@PathParam("bookId") LongParam id,
                           @Valid CreateEntry entry,
                           @Context HttpServletRequest request,
                           @Context UriInfo uriInfo) {
        return logException(() -> {
            Optional<Book> book = application.getDbi().onDemand(BookDao.class).findById(id.get());
            if (book.isPresent()) {
                Entry savedEntry = application.getDbi()
                        .onDemand(EntryDao.class)
                        .insert(entry, id.get(), request != null ? request.getRemoteAddr() : "unknown");

                return Response.created(uriInfo.getAbsolutePathBuilder()
                        .path(Long.toString(savedEntry.id)).build())
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
}
