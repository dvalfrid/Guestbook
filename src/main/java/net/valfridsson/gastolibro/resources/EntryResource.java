package net.valfridsson.gastolibro.resources;

import io.dropwizard.jersey.params.LongParam;

import net.valfridsson.gastolibro.GastolibroApplication;
import net.valfridsson.gastolibro.api.CreateEntry;
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

@Path("/home")
public class EntryResource {

  private final GastolibroApplication application;

  public EntryResource(GastolibroApplication application) {
    this.application = application;
  }

  @Path("hello")
  @Produces(MediaType.TEXT_PLAIN)
  public Response helloWorld() {
    //Test
    return Response.ok("Hello World! :D").build();
  }

  @Path("{id}/create")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createEntry(@Valid CreateEntry createEntry, @PathParam("id") long id, HttpServletRequest request) {
    //TODO:Lägg Till dina Fel loggnings Stuffs
    application.getDbi()
      .onDemand(EntryDao.class)
      .insert(createEntry, id, request.getRemoteAddr());

    Response.ok().

    return Response.status(200).build();
  }

  @Path("{bookId}/request?{numberOfEntries}?{afterId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getEntries(@PathParam("bookId") long bookId, @PathParam("numberOfEntries") long numberOfEntries, @PathParam("afterId") long id) {
    //TODO: Hämta ut numberOfEntries från boken med id bookId där de är sorterat för datum och de är inlägget efter inlägget med id = :id
    //Jag vill få tillbaka entries'arna
    return Response.status(200).build();
  }

  @Path("{bookId}/request?{entryId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getEntry(@PathParam("bookId") long bookId, @PathParam("entryId") long entryId) {
    //TODO: Hämta ut entryt med id entryId ur book med bookId
    //Jag vill få tillbaka entriet
    return Response.status(200).build();
  }

  @Path("{bookId}/search?{snippet}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response fuzzySearchEntries(@PathParam("bookId") long id, @PathParam("snippet") String snippet) {
    //TODO: returnera id för inlägget som har matchingar och en kompirmerad snutt med text för att visa i sökningen, förslagsvis stället där texten matchar.
    //Det finns inbyggda funktioner i postgres för detta. Postgres måste dock få indexera databasen för att göra det snabbt nog :)!
    return Response.status(200).build();
  }

}
