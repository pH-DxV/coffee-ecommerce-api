package unitins.topicos1.Customer.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
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
    SecutityContext securityContext;
    
}
