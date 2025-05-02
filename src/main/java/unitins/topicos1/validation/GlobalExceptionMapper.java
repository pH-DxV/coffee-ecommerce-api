package unitins.topicos1.validation;

import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
@ApplicationScoped
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Logger LOG = Logger.getLogger (GlobalExceptionMapper.class);

    @Override
    public Response toResponse (Throwable exception) {
        
        // se for erro de constraint
        Throwable cause = exception.getCause();
        if (cause instanceof ConstraintViolationException) {

            return new DuplicateEntityExceptionMapper().toResponse ((ConstraintViolationException) cause);
        
        }

        // se quiser colocar outros erros...
        if (cause instanceof IllegalArgumentException){
           
            return new IllegalArgumentExceptionMapper().toResponse ((IllegalArgumentException) cause);
        
        }

        // generico
        LOG.error("Not treated Error: " + exception.getMessage());

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error").build();
   
    }
}