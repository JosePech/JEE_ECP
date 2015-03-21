package rest;

import java.net.InetAddress;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import persistencia.models.daos.DaoFactory;
import persistencia.models.entities.Tema;
import persistencia.models.entities.Voto;
import persistencia.models.entities.utils.Escolaridad;
import persistencia.models.entities.utils.VotoSummary;

@Path("/Votos")
public class VotosRest {
    
    @POST
    @Produces({MediaType.APPLICATION_XML})
    public Response consulta(@FormParam("temaId") int temaId, @FormParam("valor") int valor, @FormParam("escolaridad") String escolaridad) {
        try{
        Tema tema = new Tema();
        tema.setId(temaId);
        Voto voto = new Voto(tema, valor, Escolaridad.valueOf(escolaridad), InetAddress.getLocalHost().getHostAddress());
        
        DaoFactory.getFactory().getVotoDao().create(voto);
        
        return Response.status(201).entity(voto).build();
        }catch(Exception e){
            return Response.status(400).build();
        }
    }
    
    @Path("/consultar")
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<VotoSummary> consulta(@DefaultValue("0") @QueryParam("start") int start,
            @DefaultValue("10") @QueryParam("size") int size) {
        List<VotoSummary> list = DaoFactory.getFactory().getVotoDao().getSummary()
                .stream().skip(start).limit(size).collect(Collectors.toList());
        return list;
    }

}
