package webServices.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import persistencia.models.daos.DaoFactory;
import persistencia.models.entities.Tema;
import webServices.TemaUris;

@Path(TemaUris.PATH_TEMAS)
public class TemasRest {
    
    public static final String PARAM_ID  = "id";

    @POST
    @Produces({MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_XML)
    public Response create(Tema tema) {
        try {
            DaoFactory.getFactory().getTemaDao().create(tema);
            return Response.status(201).entity(tema).build();
        } catch (Exception e) {
            return Response.status(400).entity(tema).build();
        }
    }

    @Path(TemaUris.PATH_ID_PARAM)
    @DELETE
    @Produces({MediaType.APPLICATION_XML})
    public Response delete(@PathParam(PARAM_ID) Integer id) {
        try {
            DaoFactory.getFactory().getTemaDao().deleteById(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(400).build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<Tema> consulta(@DefaultValue("0") @QueryParam("start") int start,
            @DefaultValue("10") @QueryParam("size") int size) {
        List<Tema> list = DaoFactory.getFactory().getTemaDao().findAll().stream().skip(start)
                .limit(size).collect(Collectors.toList());
        return list;
    }

}
