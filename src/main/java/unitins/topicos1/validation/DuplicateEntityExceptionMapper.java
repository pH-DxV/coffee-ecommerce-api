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
public class DuplicateEntityExceptionMapper implements ExceptionMapper<Throwable>{
    
    private static final Logger LOG = Logger.getLogger (DuplicateEntityExceptionMapper.class);

    @Override
    public Response toResponse (Throwable exception) {

        Throwable cause = exception;
        
        // Navega pela cadeia de exceções para encontrar a ConstraintViolationException
        while (cause != null) {

            if (cause instanceof org.hibernate.exception.ConstraintViolationException hibernateEx) {
                
                ValidationError validationError = new ValidationError("409", "Duplicate Record Error");
                
                String fieldName = extractFieldName (hibernateEx.getMessage());
                String message = "This " + fieldName + " already exists. Try another one";

                validationError.addFieldError (fieldName, message);
                LOG.error ("Duplicity Error: " + hibernateEx.getMessage());

                return Response.status (Response.Status.CONFLICT).entity (validationError).build();
            }

            cause = cause.getCause();

        }

        //LOG.error("Erro não tratado: " + exception.getMessage());
        return null;
        //return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno do servidor").build();
    }

    private String extractFieldName (String errorMessage) {
        
        // Exemplo: busca o conteúdo entre parênteses após "Detalhe: Chave (campo)=..."
        Pattern pattern = Pattern.compile("Detail: Key \\((.*?)\\)=");
        Matcher matcher = pattern.matcher(errorMessage);
        
        if (matcher.find()) {
            return matcher.group(1);
        }

        return "unkown_field"; // Fallback caso o campo não seja encontrado

    }

}
