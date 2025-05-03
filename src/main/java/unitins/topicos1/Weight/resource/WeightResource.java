package unitins.topicos1.Weight.resource;

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
import unitins.topicos1.Weight.dto.WeightDTO;
import unitins.topicos1.Weight.service.WeightService;
import unitins.topicos1.validation.GlobalExceptionMapper;

@Path("/weights")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
// @RolesAllowed("Funcionario")
public class WeightResource {
    
    @Inject
    WeightService service;

    private static final Logger LOG = Logger.getLogger(GlobalExceptionMapper.class);

    @POST
    public Response create(WeightDTO dto) {

        LOG.info("Creating new weight package");
        LOG.debugf("DTO: %s", dto);
        
        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {

        LOG.infof("Deleting weight package with id: %d", id);

        if (service.delete(id)) {

            return Response.status(Status.NO_CONTENT).build();

        }

        return Response.status(Status.NOT_FOUND).build();
    
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, WeightDTO dto) {

        LOG.infof("Updating weight package with id: %d", id);
        LOG.debugf("New DTO: %s", dto);

        service.update(id, dto);

        return Response.status(Status.NO_CONTENT).build();

    }

    @GET
    public Response findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("100") int pageSize)
            {
                
                LOG.info("Getting all weight packages (paginated)");
               
                return Response.ok(service.findAll(page, pageSize)).build();
            
            }

    @GET
    @Path("/all")
    public Response findAll() {

        LOG.info("Getting all weight packages");

        return Response.ok(service.findAll()).build();

    }

    @GET
    @Path("/count")
    public long count() {

        LOG.info("Counting weight packages");

        return service.count();

    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {

        LOG.infof("Finding weight package by id: %d", id);

        return Response.ok(service.findById(id)).build();

    }

    @GET
    @Path("/search/value/{value}")
    public Response findByWeightValue(@PathParam("value") Double value) {
       
        LOG.infof("Finding weight packages by value: %f", value);
        
        return Response.ok(service.findByWeightValue(value)).build();
   
    }

    @GET
    @Path("/search/unit/{unit}")
    public Response findByUnitOfMeasurement(@PathParam("unit") String unit) {
        
        LOG.infof("Finding weight packages by unit: %s", unit);
        
        return Response.ok(service.findByUnitOfMeasurement(unit)).build();
   
    }

    @GET
    @Path("/search/package/{type}")
    public Response findByPackageType(@PathParam("type") String type) {
      
        LOG.infof("Finding weight packages by type: %s", type);
       
        return Response.ok(service.findByPackageType(type)).build();
    
    }
    
}
