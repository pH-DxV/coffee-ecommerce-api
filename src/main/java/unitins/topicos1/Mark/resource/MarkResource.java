package unitins.topicos1.Mark.resource;

import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import unitins.topicos1.Mark.dto.MarkDTO;
import unitins.topicos1.Mark.service.MarkService;
import unitins.topicos1.validation.GlobalExceptionMapper;

@Path("/marks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarkResource {
    
    @Inject
    public MarkService service;

    private static final Logger LOG = Logger.getLogger(GlobalExceptionMapper.class);

    @POST
    public Response create(MarkDTO dto){

        LOG.info("Running create");
        LOG.debugf("DTO: %s", dto);

        return Response.ok(service.create(dto)).build();
    
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam ("id") Long id){

        LOG.info("Running delete");
        LOG.info("Informed ID: " + id);

        if (service.delete(id)){

            return Response.status(Status.NO_CONTENT).build();

        }

        return Response.status(Status.NOT_FOUND).build();

    }

    @PUT
    @Path("/{id}")
    public Response update( @PathParam("id") Long id, MarkDTO dto){

        LOG.info("Running update");
        LOG.debugf("New DTO: %s", dto);

        service.update(id, dto);

        return Response.status(Status.NO_CONTENT).build();

    }

    @GET
    public Response findAll
                    (
                    @QueryParam("page")
                    @DefaultValue("0") int page,
                    @QueryParam("pageSize")
                    @DefaultValue("100") int pageSize
                    )
    {
                        
        return Response.ok(service.findAll(page, pageSize)).build();
                    
    }

    @GET
    public Response findAll(){

        return Response.ok(service.findAll()).build();

    }

    @GET
    @Path("/search/id/{id}")
    public Response findById( @PathParam("id") Long id){

        LOG.infof("Running findById method. Id: %s", id.toString());

        return Response.ok(service.findById(id)).build();

    }

    @GET
    @Path("/search/name/{name}")
    public Response findByName(
                            @QueryParam("page")
                            @DefaultValue("0") int page,
                            @QueryParam("pageSize")
                            @DefaultValue("100") int pageSize,
                            @PathParam("name") String name
                            )
    {

        return Response.ok(service.findByName(page, pageSize, name)).build();

    }

    @GET
    @Path("/count")
    public long count() {

        return service.count();
        
    }

}

