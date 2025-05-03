package unitins.topicos1.Telephone.resource;

import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import unitins.topicos1.Telephone.dto.TelephoneDTO;
import unitins.topicos1.Telephone.service.TelephoneService;
import unitins.topicos1.validation.GlobalExceptionMapper;

@Path("/telephones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TelephoneResource {

    @Inject
    TelephoneService service;

    private static final Logger LOG = Logger.getLogger(GlobalExceptionMapper.class);

    @POST
    public Response create(TelephoneDTO dto){

        LOG.info("Running create");
        LOG.debugf("DTO: %s", dto);

        return Response.ok(service.create(dto)).build();

    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id){

        LOG.info("Running delete");
        LOG.info("Provided id: " + id);

        if (service.delete(id)){

            return Response.status(Response.Status.NO_CONTENT).build();

        }

        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, TelephoneDTO dto){

        LOG.info("Running update");
        LOG.debugf("New DTO: %s", dto);

        service.update(id, dto);

        return Response.status(Response.Status.NO_CONTENT).build();

    }

    @GET
    public Response findAll(){
        
        LOG.info("Running findAll");

        return Response.ok(service.findAll()).build();

    }

    @GET
    @Path("/search/id/{id}")
    public Response findById( @PathParam("id") Long id){
        LOG.infof("Running findById method. Id: %s", id.toString());
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/ddd/{ddd}")
    public Response findByDdd( @PathParam("ddd") String ddd){
        LOG.infof("Running findByDdd method. Ddd: %s", ddd.toString());
        return Response.ok(service.findByDdd(ddd)).build();
    }

}
