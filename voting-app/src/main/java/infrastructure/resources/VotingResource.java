package infrastructure.resources;

import java.util.List;

import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;

import api.ElectionApi;
import api.dto.out.Election;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Path("/api/voting")
public class VotingResource {

    private final ElectionApi api;

    public VotingResource(ElectionApi api) {
        this.api = api;
    }

    @GET
    public List<Election> elections() {
        return api.findAll(); 
    }

    @POST
    @Path("/elections/{electionId}/candidates/{candidateId}")
    @ResponseStatus(RestResponse.StatusCode.ACCEPTED)
    @Transactional
    public void vote(@PathParam("electionId") String electionId, @PathParam("candidateId") String candidateId) {
        api.vote(electionId, candidateId);      
    } 

}
