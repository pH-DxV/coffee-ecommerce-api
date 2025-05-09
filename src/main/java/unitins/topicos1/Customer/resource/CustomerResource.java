package unitins.topicos1.Customer.resource;

import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.core.Response.Status;
import jakarta.xml.bind.ValidationException;
import unitins.topicos1.Customer.dto.CustomerAddAddressDTO;
import unitins.topicos1.Customer.dto.CustomerDTO;
import unitins.topicos1.Customer.dto.CustomerPasswordUpdateDTO;
import unitins.topicos1.Customer.dto.CustomerUpdateDTO;
import unitins.topicos1.Customer.dto.CustomerUsernameUpdateDTO;
import unitins.topicos1.Customer.service.CustomerService;
import unitins.topicos1.Jwt.service.JwtService;
import unitins.topicos1.User.repository.UserRepository;
import unitins.topicos1.User.service.UserService;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Inject
    CustomerService service;

    @Inject
    UserService userService;

    @Inject
    UserRepository userRepository;

    @Inject
    JwtService jwtService;

    @Context
    SecurityContext securityContext;


    private static final Logger LOG = Logger.getLogger(CustomerResource.class);

    @POST
    // Deixar sem nada?
    // @RolesAllowed("Employee")
    public Response create(CustomerDTO dto){

        return Response.ok(service.create(dto)).build();
        
    }

    @DELETE
    @Path("/{id}")
    //@RolesAllowed("Employee")
    public Response delete( @PathParam("id") Long id){

        if(service.delete(id))

            return Response.status(Status.NO_CONTENT).build();

        return Response.status(Status.NOT_FOUND).build();

    }



    @PUT
    @Path("/{id}")
    //@RolesAllowed("Employee")
    public Response update( @PathParam("id") Long id, CustomerUpdateDTO dto){

        try {

            service.update(id, dto);

        } catch (ValidationException e) {

            LOG.warnf("Validation Error", e.getMessage());

        }

        return Response.status(Status.NO_CONTENT).build();

    }

    @GET
    @Path("/my-account")
    //@RolesAllowed("Customer")
    public Response getMyAccount() {

        try {

            return Response.ok(service.getMyAccount()).build();

        } catch (EntityNotFoundException e) {

            LOG.error("Customer not found.", e);

            return Response.status(Status.NOT_FOUND).entity("ustomer not found.").build();
        
        } catch (Exception e) {

            LOG.error("Error fetching account.", e);

            
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Error fetching account. "+ e).build();
        
        }

    }

    @PUT
    @Path("/my-account")
    //@RolesAllowed("Customer")
    public Response updateMyAccount(CustomerUpdateDTO dto) {

        try {

            service.updateMyAccount(dto);

            return Response.noContent().build();

        } catch (EntityNotFoundException e) {

            LOG.error("Customer not found.", e);

            return Response.status(Status.NOT_FOUND).entity("Customer not found.").build();
        
        } catch (Exception e) {

            LOG.error("Error updating account.", e);

            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Error updating account. "+ e).build();
      
        }
    }

    @PATCH
    //@RolesAllowed("Customer")
    @Path("/update-password")
    public Response updateUserPassword(CustomerPasswordUpdateDTO passwordUpdateDTO){

        service.updateUserPassword(passwordUpdateDTO);

        return Response.status(Status.NO_CONTENT).build();

    }

    @PATCH
    //@RolesAllowed("Customer")
    @Path("/update-username")
    public Response updateUserUsername(CustomerUsernameUpdateDTO usernameUpdateDTO){

        service.updateUserUsername(usernameUpdateDTO);
        
        return Response.status(Status.NO_CONTENT).build();

    }

    @PATCH
    @Path("/add-address/{id}")
    public Response addAddress(@PathParam("id") Long idCustomer, CustomerAddAddressDTO dto){

        service.addAddress(idCustomer, dto);

        return Response.status(Status.NO_CONTENT).build();

    }

    @GET
    //@RolesAllowed({"Employee", "Customer"})
    public Response findAll(){

        return Response.ok(service.findAll()).build();

    }

    @GET
    @Path("/search/{id}/addresses")
    //@RolesAllowed({"Employee", "Customer"})
    public Response findAllAddressesById( @PathParam("id") Long id ){

        return Response.ok(service.findAllAddressesById(id)).build();

    }

    @GET
    @Path("/search/id/{id}")
    //@RolesAllowed("Employee")
    public Response findById( @PathParam("id") Long id){

        return Response.ok(service.findById(id)).build();

    }

    @GET
    @Path("/search/name/{name}")
    //@RolesAllowed("Employee")
    public Response findByName( @PathParam("name") String name){

        // service.findByName(name);

        return Response.ok(service.findByName(name)).build();

    }

    @GET
    @Path("/search/cpf/{cpf}")
    //@RolesAllowed("Employee")
    public Response findByCpf( @PathParam("cpf") String cpf){

        // service.findByName(name);

        return Response.ok(service.findByCpf(cpf)).build();

    }

    @GET
    @Path("/search/username/{username}")
    //@RolesAllowed("Employee")
    public Response findByUsername( @PathParam("username") String username){

        return Response.ok(service.findByUsername(username)).build();
        
    }







}
