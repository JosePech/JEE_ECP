package controladores;

import persistencia.models.entities.Voto;

public interface VotarController extends TemasController{
    
    void votar(Voto voto);

}
