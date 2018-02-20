package org.aja.player.resource;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import org.aja.player.api.Player;
import org.aja.player.api.PlayerRequest;
import org.aja.player.common.Transformer;
import org.aja.player.auth.User;
import org.aja.player.db.PlayerDAO;
import org.aja.player.db.PlayerEntity;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import io.dropwizard.auth.Auth;

@Path("/v1/")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource {

    final static private Logger log = LoggerFactory.getLogger(PlayerResource.class);

    @Inject
    private PlayerDAO playerDAO;

    public PlayerResource() {

    }

    @PUT
    @Path("player/{playerId}")
    @RolesAllowed("ADMIN")
    @Timed
    public void update(@Suspended final AsyncResponse asyncResponse, @PathParam("playerId") Integer playerId,
                       @NotNull Player people) {
        asyncResponse.resume(Response.status(HttpStatus.OK_200).entity(people).build());
    }

    @POST
    @Path("player")
    @RolesAllowed("ADMIN")
    @Timed
    public void add(@Suspended final AsyncResponse asyncResponse, @Auth User user,
                    @Context UriInfo uriInfo, @NotNull PlayerRequest request) {
        Integer playerId = playerDAO.getNextPlayerId();
        URI locationUri = uriInfo.getRequestUri().resolve(UriBuilder.
                fromUri("../player/{playerId}").build(playerId));

        PlayerEntity entity = Transformer.convertPlayerRequest(playerId, request);
        playerDAO.insert(entity);
        asyncResponse.resume(Response.status(HttpStatus.CREATED_201).location(locationUri).build());
    }

    @GET
    @Path("player/{playerId}")
    @Timed
    public void get(@Suspended final AsyncResponse asyncResponse,
                    @PathParam("playerId") Integer playerId) throws InterruptedException {

        //JUST A TEST TO SHOW ASYNCH
        Player p = new Player( "Superman", 25, true);
        asyncResponse.resume(Response.status(HttpStatus.OK_200).entity(p).build());
        Thread.sleep(5000);
        log.info("Done now");

    }

    @DELETE
    @Path("player/{playerId}")
    @RolesAllowed("ADMIN")
    @Timed
    public void delete(@Suspended final AsyncResponse asyncResponse, @PathParam("playerId") Integer playerId) {
        asyncResponse.resume(Response.status(HttpStatus.OK_200).build());
    }

    @GET
    @Path("player")
    @Timed
    public void getAll(@Suspended final AsyncResponse asyncResponse) {
        final List<Player> peopleList = Arrays.asList(new Player("Jonas", 50, true), new Player( "Sonja", 45, false));
        asyncResponse.resume(Response.status(HttpStatus.OK_200).entity(peopleList).build());
    }
}
