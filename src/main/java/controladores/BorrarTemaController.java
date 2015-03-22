package controladores;

import persistencia.models.entities.Tema;

public interface BorrarTemaController extends TemasController {

    void borrar(Tema tema);

}
