package net.valfridsson.gastolibro.resources;

import com.google.common.collect.ImmutableMap;
import io.dropwizard.jersey.params.AbstractParam;
import io.dropwizard.jersey.params.LongParam;
import net.valfridsson.gastolibro.GastolibroApplication;
import net.valfridsson.gastolibro.api.CreateEntry;
import net.valfridsson.gastolibro.core.Entry;
import net.valfridsson.gastolibro.jdbi.EntryDao;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/entries")
public class EntryResource {

    private final GastolibroApplication application;

    public EntryResource(GastolibroApplication application) {
        this.application = application;
    }

    @POST
    public Response create(@Valid @NotNull(message = "'Create entry must be set") CreateEntry entry,
                           @Context UriInfo uriInfo) {
        Entry insert = application.getDbi().onDemand(EntryDao.class).insert(entry);
        return Response.created(uriInfo.getAbsolutePathBuilder().path(Long.toString(insert.id)).build()).build();
    }

    @GET
    public Response getEntries(@QueryParam("amount") Optional<LongParam> numberOfEntries,
                               @QueryParam("id") Optional<LongParam> id) {
        return Response.ok(application.getDbi().onDemand(EntryDao.class)
                        .findX(numberOfEntries.map(LongParam::get).orElse(10L),
                                id.map(LongParam::get).orElse(0L))).build();
    }

}
