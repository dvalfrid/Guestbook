package net.valfridsson.gastolibro.resources;

import java.io.IOException;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.common.collect.ImmutableList;
import io.dropwizard.jersey.params.LongParam;

import net.valfridsson.gastolibro.GastolibroApplication;
import net.valfridsson.gastolibro.core.Book;
import net.valfridsson.gastolibro.core.Entry;
import net.valfridsson.gastolibro.jade.GastroJadeConfig;
import net.valfridsson.gastolibro.jade.Models;
import net.valfridsson.gastolibro.jdbi.BookDao;
import net.valfridsson.gastolibro.jdbi.EntryDao;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class LandingPageResource {

    private final GastolibroApplication application;

    private ImmutableList<Entry> entry;
    private ImmutableList<Book> book;

    public LandingPageResource(GastolibroApplication application) {
        this.application = application;

        this.entry = ImmutableList
                .<Entry>builder()
                .add(Entry.newBuilder()
                        .id(103)
                        .name("Smith")
                        .ip("IPSmith")
                        .headline("HeadlineSmith")
                        .email("EmailSmith")
                        .city("CitySmith")
                        .country("CountrySmith")
                        .message("MessageSmith")
                        .viewAble(true)
                        .build())
                .build();


        this.book = ImmutableList.<Book>builder()
                .add(new Book(103, "Smith", true))
                .build();

    }


    @GET
    public Response LoadLandingPage(@QueryParam("bookId") LongParam bookId) throws IOException {
        Optional<Book> byId = application.getDbi().onDemand(BookDao.class).findById(bookId.get());
        if (byId.isPresent()) {
            Book book = byId.get();
            ImmutableList<Entry> entries = application.getDbi().onDemand(EntryDao.class).findAll(bookId.get());
            // TODO Jonas
            return Response
                    .ok(Models.buildModel()
                            .data(GastroJadeConfig.getInstance().ENTRYKEY, this.entry)
                            .data(GastroJadeConfig.getInstance().BOOKSKEY, this.book)
                            .render()
                    ).build();
        } else {
            return Response.status(404).build();
        }
    }
}
