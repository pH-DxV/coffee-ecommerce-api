package unitins.topicos1.AuthUser.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import unitins.topicos1.AuthUser.dto.AuthUserDTO;
import unitins.topicos1.Customer.service.CustomerService;
import unitins.topicos1.Employee.service.EmployeeService;
import unitins.topicos1.Hash.service.HashService;
import unitins.topicos1.Jwt.service.JwtService;
import unitins.topicos1.User.dto.UserResponseDTO;

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

    @POST
    public Response login(AuthUserDTO dto){

        String hashPassword = hashService.getHashPassword(dto.password());

        UserResponseDTO user = null;

        if(dto.type() == 1){

            // cliente
            user = customerService.login(dto.username(), hashPassword);

        } else if (dto.type() == 2){

            // funcionario
            user = employeeService.login(dto.username(), hashPassword);

        } else {

            return Response.status(Status.NOT_FOUND)
                            .header("Profile", "types of existing profiles: 1- customer | 2- employee")
                            .build();
        
        }

        if(user != null){

            return Response.ok(user)
                            .header("Authorization", jwtService.generateJwt(dto, user))
                            .status(Status.CREATED)
                            .build();

        } else {

            return Response.status(Status.NOT_FOUND).build();

        }
        
    }



}
