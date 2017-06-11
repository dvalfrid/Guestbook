package net.valfridsson.gastolibro.resources;

import com.google.common.collect.ImmutableList;

import io.dropwizard.jersey.params.LongParam;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

import net.valfridsson.gastolibro.GastolibroApplication;
import net.valfridsson.gastolibro.api.CreateEntry;
import net.valfridsson.gastolibro.core.Book;
import net.valfridsson.gastolibro.core.Entry;
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
            return Response.ok(getBook(bookId.get().intValue())).build();
    }

    private String getBook(int id) throws IOException {
      InputStream in;
      switch (id) {
        case 10:
           in = PageResource.class.getClassLoader().getResourceAsStream("assets/html/page10.html");
           break;
        default:
           return "<html><body>Page Not Available</body> </html>";
      }

      return IOUtils.toString(in);
    }
}
