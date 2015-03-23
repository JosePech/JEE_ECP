package controladores.ws;

import persistencia.models.entities.Tema;
import webServices.TemaUris;
import webServices.rest.TemasRest;
import controladores.BorrarTemaController;

public class BorrarTemaControllerWs extends TemasControllerWs implements BorrarTemaController {

    @Override
    public void borrar(Tema tema) {
        WsManager wsManager = ControllerWs.buildWebServiceManager(TemaUris.PATH_TEMAS, TemaUris.PATH_ID_PARAM);
        wsManager.addParams(TemasRest.PARAM_ID, tema.getId().toString());
        wsManager.delete();
    }

}
