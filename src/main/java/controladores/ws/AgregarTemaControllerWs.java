package controladores.ws;

import persistencia.models.entities.Tema;
import webServices.TemaUris;
import controladores.AgregarTemaController;

public class AgregarTemaControllerWs implements AgregarTemaController {

    @Override
    public void agregar(Tema tema) {
        assert tema != null;
        ControllerWs.buildWebServiceManager(TemaUris.PATH_TEMAS).create(tema);
    }

}
