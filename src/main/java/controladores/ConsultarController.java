package controladores;

import java.util.List;

import persistencia.models.entities.Tema;
import persistencia.models.entities.utils.VotoSummary;

public interface ConsultarController {
    
    List<VotoSummary> consultar();
    
    Long getTotalTema(Tema tema);

}
