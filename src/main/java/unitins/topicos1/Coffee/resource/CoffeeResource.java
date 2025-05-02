package unitins.topicos1.Coffee.resource;

import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import unitins.topicos1.Coffee.dto.CoffeeDTO;

@Path("/coffee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoffeeResource {
    
    @Inject
    public CoffeeService service;

    @Inject
    public CoffeeFileServiceImpl fileService;

    private static final Logger LOG = Logger.getLogger(ColorResource.class);

    @POST
    public Response create(@PathParam("id")Long id, CoffeeDTO dto){

        LOG.info("Running update");
        LOG.debugf("New DTO: %s", dto);
        service.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();

    }

    @PUT
    @Path("/{id}")
    public Response update( @PathParam("id") Long id, CoffeeDTO dto){

        LOG.info("Running update");
        LOG.debugf("New DTO: %s", dto);
        service.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();

    }

    @GET
    public Response findAll(@QueryParam("page")@DefaultValue("0") int page,
                            @QueryParam("pageSize") @DefaultValue("100") int pageSize){

                                return Response.ok(service.findAll(page, pageSize)).build();
    
    }

    @GET
    public Response findAll(){

        LOG.info("Running FindAll");

        return Response.ok(service.findAll()).build();

    }

    @GET
    @Path("/search/id/{id}")
    public Response findById(@PathParam("id") Long id){
        LOG.infof("Running FindById. Id: %s", id.toString());

        if(service.findById(id) != null){

            return Response.ok(service.findById(id)).build();
        }
        
        return Response.status(Status.NOT_FOUND).build();

    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){

        if(service.delete(id)){

            return Response.status(Status.NO_CONTENT).build();

        }

        return Response.status(Status.NOT_FOUND).build();
    }

    /*
        ==== IMPLEMENTAR PARA PODER ADICIONAR IMAGEM ====


    @PATCH
    @Path("/imagem/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    //@RolesAllowed("Funcionario")
    public Response salvarImagem(@MultipartForm TenisImageForm form) {
        fileService.upload(form.getId(), form.getNomeImagem(), form.getImagem());
        return Response.noContent().build();
    }


    @GET
    @Path("/image/download/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    //@RolesAllowed({"Cliente", "Funcionario"})
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        return Response.ok(fileService.download(nomeImagem))
               .header("Content-Disposition", "attachment;filename=" + nomeImagem).build();
    }
               
        ==================================================
    */





}
