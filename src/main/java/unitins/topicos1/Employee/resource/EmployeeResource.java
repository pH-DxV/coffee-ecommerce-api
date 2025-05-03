package unitins.topicos1.Employee.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import unitins.topicos1.Employee.dto.EmployeeDTO;
import unitins.topicos1.Employee.dto.EmployeePasswordUpdatetDTO;
import unitins.topicos1.Employee.dto.EmployeeUsernameUpdateDTO;
import unitins.topicos1.Employee.service.EmployeeService;
import unitins.topicos1.User.repository.UserRepository;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {
    
    @Inject
    public EmployeeService service;

    @Inject
    UserRepository userRepository;

    @Inject
    JsonWebToken jwt;

    private static final Logger LOG = Logger.getLogger(EmployeeResource.class);

    @POST
    public Response create(EmployeeDTO dto) {

        return Response.ok(service.create(dto)).build();
        
    }

    @DELETE
    @Path("/{id}")
    //@RolesAllowed("Employee")
    public Response delete(@PathParam("id") Long id) {

        if(service.delete(id))

            return Response.status(Status.NO_CONTENT).build();


        return Response.status(Status.NOT_FOUND).build();


    }

    @PUT
    @Path("/{id}")
    //@RolesAllowed("Employee")
    public Response update(@PathParam("id") Long id, EmployeeDTO dto) {

        try {

            service.update(id, dto);

        } catch (ValidationException e) {

            LOG.warnf("Validation error", e.getMessage());
            
        }
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    //@RolesAllowed("Employee")
    @Path("/update-password")
    public Response updateUserPassword(EmployeePasswordUpdatetDTO passwordUpdateDTO) {
        
        service.updateUserPassword(passwordUpdateDTO);

        return Response.status(Status.NO_CONTENT).build();

    }

    @PATCH
    //@RolesAllowed("Employee")
    @Path("/update-username")
    public Response updateUserUsername(EmployeeUsernameUpdateDTO usernameUpdateDTO) {

        service.updateUserUsername(usernameUpdateDTO);

        return Response.status(Status.NO_CONTENT).build();
        
    }

    @GET
    //@RolesAllowed("Employee")
    public Response findAll() {

        return Response.ok(service.findAll()).build();

    }

    @GET
    @Path("/search/id/{id}")
    //@RolesAllowed("Employee")
    public Response findById(@PathParam("id") Long id) {

        return Response.ok(service.findById(id)).build();

    }

    @GET
    @Path("/search/name/{name}")
    //@RolesAllowed("Employee")
    public Response findByName(@PathParam("name") String name) {

        return Response.ok(service.findByName(name)).build();

    }

    @GET
    @Path("/search/cpf/{cpf}")
    //@RolesAllowed("Employee")
    public Response findByCpf(@PathParam("cpf") String cpf) {

        return Response.ok(service.findByCpf(cpf)).build();

    }

    @GET
    @Path("/search/username/{username}")
    //@RolesAllowed("Employee")
    public Response findByUsername(@PathParam("username") String username) {

        return Response.ok(service.findByUsername(username)).build();

    }

}
