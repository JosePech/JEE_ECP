package controladores.ws;

import java.util.List;

import javax.ws.rs.core.GenericType;

import persistencia.models.entities.Tema;
import webServices.TemaUris;
import controladores.TemasController;

public class TemasControllerWs implements TemasController {

    @Override
    public List<Tema> getTemas() {
        return ControllerWs.buildWebServiceManager(TemaUris.PATH_TEMAS).entities(new GenericType<List<Tema>>(){});
    }

}
