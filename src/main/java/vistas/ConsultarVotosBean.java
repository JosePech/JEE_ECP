package vistas;

import java.util.List;

import persistencia.models.entities.Tema;
import persistencia.models.entities.utils.VotoSummary;
import controladores.ControllerFactory;

public class ConsultarVotosBean {
    
    private ControllerFactory controller;
    
    public static final String PATH_CONSULTAR_VOTOS = "consultarVotos";

    public ConsultarVotosBean(ControllerFactory controller) {
        this.controller = controller;
    }
    
    public List<VotoSummary> getResumen(){
        return controller.getConsultarController().consultar();
    }

    public Long getTotalPorTema(Tema tema){
        return controller.getConsultarController().getTotalTema(tema);
    }

}
