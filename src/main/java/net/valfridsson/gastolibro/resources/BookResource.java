package net.valfridsson.gastolibro.resources;

import com.google.common.collect.ImmutableMap;
import io.dropwizard.jersey.params.LongParam;

import net.valfridsson.gastolibro.GastolibroApplication;
import net.valfridsson.gastolibro.api.CreateEntry;
import net.valfridsson.gastolibro.core.Book;
import net.valfridsson.gastolibro.core.Entry;
import net.valfridsson.gastolibro.jdbi.BookDao;
import net.valfridsson.gastolibro.jdbi.EntryDao;

import java.io.IOException;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Context;


import java.util.Base64;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/books/{bookId}")
public class BookResource {

    private final GastolibroApplication application;

    public BookResource(GastolibroApplication application) {
        this.application = application;
    }

    @GET
    public Response getBook(@PathParam("bookId") LongParam bookId) {
        Optional<Book> optional = application.getDbi().onDemand(BookDao.class).findById(bookId.get());
        return optional.map(book -> Response.ok(book).build()).orElseGet(() -> Response.status(404).build());
    }

    @GET
    @Path("/entries")
    public Response getEntries(@PathParam("bookId") LongParam bookId,
                               @QueryParam("numberOfEntries") LongParam numberOfEntries,
                               @QueryParam("afterId") LongParam afterId) {
        return Response.ok(ImmutableMap.of(
                "entries", application.getDbi().onDemand(EntryDao.class)
                        .findX(bookId.get(), numberOfEntries.get(), afterId.get()))).build();
    }

    @GET
    @Path("/entries/{entryId}")
    public Response getEntry(@PathParam("bookId") LongParam bookId,
                             @PathParam("entryId") LongParam entryId) {
        Optional<Entry> optional = application.getDbi().onDemand(EntryDao.class).findBy(bookId.get(), entryId.get());
        return optional.map(entry -> Response.ok(entry).build()).orElseGet(() -> Response.status(404).build());
    }

    @GET
    @Path("/search")
    public Response searchEntries(@PathParam("bookId") long id,
                                  @QueryParam("snippet") String snippet) {
        //TODO: returnera id för inlägget som har matchingar och en kompirmerad snutt med text för att visa i sökningen, förslagsvis stället där texten matchar.
        //Det finns inbyggda funktioner i postgres för detta. Postgres måste dock få indexera databasen för att göra det snabbt nog :)!
        return Response.status(200).build();
    }

}
