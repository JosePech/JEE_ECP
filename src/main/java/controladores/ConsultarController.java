package controladores;

import java.util.List;

import persistencia.models.entities.utils.VotoSummary;

public interface ConsultarController {
    
    List<VotoSummary> consultar();

}
