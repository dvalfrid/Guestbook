package net.valfridsson.gastolibro.resources;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jersey.repackaged.com.google.common.collect.ImmutableList;

import net.valfridsson.gastolibro.GastolibroApplication;
import net.valfridsson.gastolibro.core.Book;
import net.valfridsson.gastolibro.core.Entry;
import net.valfridsson.gastolibro.jade.GastroJadeConfig;
import net.valfridsson.gastolibro.jade.Models;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class LandingPageResource {

  private final GastolibroApplication application;

  ImmutableList<Entry> entry;
  ImmutableList<Book> book;

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
    public Response LoadLandingPage() throws IOException {
      return Response
        .ok(
            Models.buildModel()
            .data(GastroJadeConfig.getInstance().ENTRYKEY, this.entry)
            .data(GastroJadeConfig.getInstance().BOOKSKEY, this.book)
            .render()
            ).build();
    } 
}
