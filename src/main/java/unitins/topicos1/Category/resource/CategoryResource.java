package unitins.topicos1.Category.resource;

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
import unitins.topicos1.Category.dto.CategoryDTO;
import unitins.topicos1.Category.service.CategoryService;


@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
// @RolesAllowed("Employee")
public class CategoryResource {
    
    @Inject
    public CategoryService service;

    private static final Logger LOG = Logger.getLogger(CategoryResource.class);

    @POST
    public Response create(CategoryDTO dto){

        LOG.info("Running create");
        LOG.debugf("DTO: %s", dto);

        return Response.ok(service.create(dto)).build();

    }

    @DELETE
    @Path("/{id}")
    public Response delete( @PathParam("id") Long id){

        LOG.info("Running delete");

        if(service.delete(id))

            return Response.status(Status.NO_CONTENT).build();

        return Response.status(Status.NOT_FOUND).build();
        
    }

    @PUT
    @Path("/{id}")
    public Response update( @PathParam("id") Long id, CategoryDTO dto){
        
        LOG.info("Running update");
        service.update(id, dto);

        return Response.status(Status.NO_CONTENT).build();

    }

    @GET
    public Response findAll(
            @QueryParam("page") @DefaultValue("-1") int page,
            @QueryParam("pageSize") @DefaultValue("-1") int pageSize) {
    
        if (page >= 0 && pageSize > 0) {
            return Response.ok(service.findAll(page, pageSize)).build();
        }
    
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
    public Response findByName
                            (

                                @QueryParam("page") @DefaultValue("0") int page,
                                @QueryParam("pageSize") @DefaultValue("100") int pageSize,
                                @PathParam("name") String name

                            ) {
        return Response.ok(service.findByName(page, pageSize, name)).build();
    }

    @GET
    @Path("/count")
    public long count(){

        return service.count();

    }
}

