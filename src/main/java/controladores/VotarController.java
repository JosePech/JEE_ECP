package controladores;

import persistencia.models.entities.Voto;
import persistencia.models.entities.utils.Escolaridad;

public interface VotarController extends TemasController {

    void votar(Voto voto);
    
    Escolaridad[] getEscolaridadValues();

}
