package org.aja.people.resource;

import com.codahale.metrics.annotation.Timed;
import org.aja.people.api.Player;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/v1/")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource {

    final static private Logger log = LoggerFactory.getLogger(PlayerResource.class);

    public PlayerResource() {
    }

    @PUT
    @Path("player/{id}")
    @Timed
    public void update(@Suspended final AsyncResponse asyncResponse, @PathParam("id") Integer id, @NotNull Player people) {
        asyncResponse.resume(Response.status(HttpStatus.OK_200).entity(people).build());
    }

    @POST
    @Path("player")
    @Timed
    public void add(@Suspended final AsyncResponse asyncResponse, @NotNull Player people) {
        asyncResponse.resume(Response.status(HttpStatus.OK_200).build());
    }

    @GET
    @Path("player/{id}")
    @Timed
    public void get(@Suspended final AsyncResponse asyncResponse, @PathParam("id") Integer id) throws InterruptedException {

        //JUST A TEST TO SHOW ASYNCH
        Player p = new Player(1, "Superman", 25, true);
        asyncResponse.resume(Response.status(HttpStatus.OK_200).entity(p).build());
        Thread.sleep(5000);
        log.info("Done now");

    }

    @DELETE
    @Path("player/{id}")
    @Timed
    public void delete(@Suspended final AsyncResponse asyncResponse, @PathParam("id") Integer id) {
        asyncResponse.resume(Response.status(HttpStatus.OK_200).build());
    }

    @GET
    @Path("player")
    @Timed
    public void getAll(@Suspended final AsyncResponse asyncResponse) {
        final List<Player> peopleList = Arrays.asList(new Player(1, "Jonas", 50, true), new Player(1, "Sonja", 45, false));
        asyncResponse.resume(Response.status(HttpStatus.OK_200).entity(peopleList).build());
    }
}
