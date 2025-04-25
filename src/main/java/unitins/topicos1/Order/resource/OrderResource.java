package unitins.topicos1.Order.resource;

import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    public OrderService service;

    private static final Logger LOG = Logger.getLogger(ColorResource.class);

    @POST
    public Response create(OrderDTO dto){

        LOG.info("Running create");
        LOG.debugf("DTO: %s", dto);
        return Response.ok(service.create(dto)).build();     
    
    }
}
