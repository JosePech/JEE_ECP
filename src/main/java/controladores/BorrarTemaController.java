package controladores;

import java.util.List;

import persistencia.models.entities.Tema;

public interface BorrarTemaController {
    
    void borrar(Tema tema);
    
    List<Tema> getTemas();
}
