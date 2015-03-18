package vistas;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import persistencia.models.entities.Tema;
import persistencia.models.entities.utils.VotoSummary;
import controladores.ConsultarController;
import controladores.ControllerFactory;

@ManagedBean
public class ConsultarVotosBean {
    
    @ManagedProperty(value = "#{controllerFactoryEjbs}")
    private ControllerFactory controller;
    
    private ConsultarController consultarController;
    
    public static final String PATH_CONSULTAR_VOTOS = "consultarVotos";

    public ConsultarVotosBean(ControllerFactory controller) {
        this.controller = controller;
        consultarController = this.controller.getConsultarController();
    }
    
    public ConsultarVotosBean() {        
    }
    
    @PostConstruct
    public void update () {
        this.consultarController = controller.getConsultarController();
    }
    
    public List<VotoSummary> getResumen(){
        return consultarController.consultar();
    }

    public Long getTotalPorTema(Tema tema){
        return consultarController.getTotalTema(tema);
    }

    public ControllerFactory getController() {
        return controller;
    }

    public void setController(ControllerFactory controller) {
        this.controller = controller;
    }

}
