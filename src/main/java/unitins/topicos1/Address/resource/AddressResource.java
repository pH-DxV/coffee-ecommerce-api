package unitins.topicos1.Address.resource;

import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import unitins.topicos1.Address.dto.AddressDTO;
import unitins.topicos1.Address.service.AddressService;
import unitins.topicos1.validation.GlobalExceptionMapper;

@Path("/address")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressResource {
    
    @Inject
    AddressService service;

    private static final Logger LOG = Logger.getLogger(GlobalExceptionMapper.class);

    @POST
    public Response create (AddressDTO dto){

        LOG.info("Running create");
        LOG.debugf("DTO: %s", dto);

        return Response.ok(service.create(dto)).build();
        
    }


}
