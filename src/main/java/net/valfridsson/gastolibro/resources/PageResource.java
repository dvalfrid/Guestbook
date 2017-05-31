package net.valfridsson.gastolibro.resources;

import java.io.IOException;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.common.collect.ImmutableList;
import io.dropwizard.jersey.params.LongParam;

import net.valfridsson.gastolibro.GastolibroApplication;
import net.valfridsson.gastolibro.api.CreateEntry;
import net.valfridsson.gastolibro.core.Book;
import net.valfridsson.gastolibro.core.Entry;
import net.valfridsson.gastolibro.jade.GastroJadeConfig;
import net.valfridsson.gastolibro.jade.Models;
import net.valfridsson.gastolibro.jdbi.BookDao;
import net.valfridsson.gastolibro.jdbi.EntryDao;

@Path("/")
public class PageResource {

    private final GastolibroApplication application;

    public PageResource(GastolibroApplication application) {
        this.application = application;
    }


    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response LoadLandingPage(@QueryParam("bookId") LongParam bookId) throws IOException {
        Optional<Book> byId = application.getDbi().onDemand(BookDao.class).findById(bookId != null ? bookId.get() : -1);
        if (byId.isPresent()) {
            Book book = byId.get();
            ImmutableList<Entry> entries = application.getDbi().onDemand(EntryDao.class).findAll(bookId.get());
            return Response
                    .ok(Models.buildModel()
                            .data(GastroJadeConfig.getInstance().EntryKey, entries)
                            .data(GastroJadeConfig.getInstance().BookKey, book)
                            .render()
                    ).build();
        } else {
            return Response.status(404).build();
        }
    }


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
