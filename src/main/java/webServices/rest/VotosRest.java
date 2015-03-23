package webServices.rest;

import java.net.InetAddress;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import persistencia.models.daos.DaoFactory;
import persistencia.models.entities.Voto;
import persistencia.models.entities.utils.VotoSummary;
import webServices.VotoUris;

@Path(VotoUris.PATH_VOTOS)
public class VotosRest {

    @POST
    @Produces({MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_XML)
    public Response create(Voto voto) {
        try {
            voto.setIp(InetAddress.getLocalHost().getHostAddress());
            DaoFactory.getFactory().getVotoDao().create(voto);
            return Response.status(201).entity(voto).build();
        } catch (Exception e) {
            return Response.status(400).entity(voto).build();
        }
    }

    @Path(VotoUris.PATH_CONSULTAR)
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<VotoSummary> consulta(@DefaultValue("0") @QueryParam("start") int start,
            @DefaultValue("10") @QueryParam("size") int size) {
        List<VotoSummary> list = DaoFactory.getFactory().getVotoDao().getSummary().stream()
                .skip(start).limit(size).collect(Collectors.toList());
        return list;
    }

}
