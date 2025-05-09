package unitins.topicos1.CustomerOrder.resource;

import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import unitins.topicos1.CustomerOrder.dto.CustomerOrderDTO;
import unitins.topicos1.CustomerOrder.service.CustomerOrderService;
import unitins.topicos1.validation.GlobalExceptionMapper;

@Path ("/orders")
@Produces (MediaType.APPLICATION_JSON)
@Consumes (MediaType.APPLICATION_JSON)
public class CustomerOrderResource {

    @Inject
    public CustomerOrderService service;

    private static final Logger LOG = Logger.getLogger (GlobalExceptionMapper.class);

    @POST
    public Response create (CustomerOrderDTO dto){

        LOG.info ("Running create");
        LOG.debugf ("DTO: %s", dto);
        return Response.ok (service.create(dto)).build();     
    
    }

    @GET
    public Response findAll(){

        LOG.info("Running findAll");

        return Response.ok (service.findAll()).build();
    }

    @GET
    @Path("/search/customer/id/{id}")
    public Response findOrderByCustomerId (@PathParam ("id") Long id){

        LOG.infof ("Running findById method. Id: %s", id.toString());
        
        return Response.ok (service.findByCustomer(id)).build();
    
    }

    @GET
    @Path("/search/customer/{idCustomer}")
    public Response findByCustomer (@PathParam ("idCustomer") Long idCustomer ){
        
        LOG.infof("Running findByCustomer method. IdCustomer: %s", idCustomer.toString());
        
        return Response.ok(service.findByCustomer(idCustomer)).build();
    
    }

}
