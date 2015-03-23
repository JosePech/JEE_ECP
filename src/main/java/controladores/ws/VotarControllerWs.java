package controladores.ws;

import javax.ws.rs.core.GenericType;

import persistencia.models.entities.Voto;
import persistencia.models.entities.utils.Escolaridad;
import webServices.UtilUris;
import webServices.VotoUris;
import controladores.VotarController;

public class VotarControllerWs extends TemasControllerWs implements VotarController {

    @Override
    public void votar(Voto voto) {
        assert voto != null;
        ControllerWs.buildWebServiceManager(VotoUris.PATH_VOTOS).create(voto);
    }

    @Override
    public Escolaridad[] getEscolaridadValues() {
        return ControllerWs.buildWebServiceManager(UtilUris.PATH_UTILS, UtilUris.PATH_ESCOLARIDAD)
                .entities(new GenericType<Escolaridad[]>() {
                });
    }
}
