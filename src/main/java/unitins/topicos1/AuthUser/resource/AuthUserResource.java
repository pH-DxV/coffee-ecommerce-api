package unitins.topicos1.AuthUser.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import unitins.topicos1.Jwt.service.JwtService;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthUserResource {
    
    @Inject
    public CustomerService customerService;

    @Inject
    public EmployeeService employeeService;

    @Inject
    public HashService hashService;

    @Inject
    JwtService jwtService;
}
