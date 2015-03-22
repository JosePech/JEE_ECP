package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import persistencia.models.entities.utils.Escolaridad;

@Path("/Utils")
public class UtilsRest {

    @Path("/escolaridad")
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public Escolaridad[] consulta() {
        return Escolaridad.values();
    }

}
