package unitins.topicos1.Root.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/") // Define a rota na raiz
public class RootResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        return "Welcome to a Coffee Ecommerce API!";
    }
}