package webServices.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import persistencia.models.entities.utils.Escolaridad;
import webServices.UtilUris;

@Path(UtilUris.PATH_UTILS)
public class UtilsRest {

    @Path(UtilUris.PATH_ESCOLARIDAD)
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public Escolaridad[] consulta() {
        return Escolaridad.values();
    }

}
