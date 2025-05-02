package unitins.topicos1.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
@ApplicationScoped
public class IllegalArgumentExceptionMapper implements ExceptionMapper<Throwable>{

    private static final Logger LOG = Logger.getLogger(IllegalArgumentExceptionMapper.class);

    @Override
    public Response toResponse(Throwable exception) {

        Throwable cause = exception;
        
        // Navigate the exception chain to find the ConstraintViolationsException
        while (cause != null) {

            if (cause instanceof IllegalArgumentException hibernateEx) {
                
                ValidationError validationError = new ValidationError("404", "user not logged");
                
                String fieldName = extractFieldName(hibernateEx.getMessage() );
                String message = "user not logged";

                validationError.addFieldError (fieldName, message);

                LOG.error ("Error of 'not found': " + hibernateEx.getMessage() );

                return Response.status (Response.Status.CONFLICT) .entity (validationError) .build();
            }

            cause = cause.getCause();

        }

        LOG.error("Error no treated: " + exception.getMessage() );
        
        return Response.status (Response.Status.INTERNAL_SERVER_ERROR) .entity ("Server Internal Error") .build();
    
    }

    private String extractFieldName (String errorMessage) {
        
        // Exemplo: busca o conteúdo entre parênteses após "Detalhe: Chave (campo)=..."
        Pattern pattern = Pattern.compile("Detail: Key \\((.*?)\\)=");
        
        Matcher matcher = pattern.matcher (errorMessage);

        if (matcher.find()) {

            return matcher.group(1);

        }

        return "unknown_field"; // Fallback caso o campo não seja encontrado
   
    }

}
