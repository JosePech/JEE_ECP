package vistas;

import java.util.List;

import org.apache.logging.log4j.LogManager;

import controladores.ControllerFactory;
import persistencia.models.entities.Tema;

public class BorrarTemaBean {
    
    private ControllerFactory controller;
    
    private Integer temaId;
    
    private boolean result;
    
    private List<Tema> temas;
    
    public static final String PATH_BORRAR_TEMA = "borrarTema";
    
    public BorrarTemaBean(ControllerFactory factory){
        this.controller = factory;
    }

    public void setTemaId(Integer temaId) {
        this.temaId = temaId;
    }
    
    public String process(){
        try{
            this.setResult(validarDatos());
            if(this.getResult()){
                Tema tema = new Tema();
                tema.setId(temaId);
                controller.getBorrarTemaController().borrar(tema);
            }
        }catch(Exception e){
            this.setResult(false);
            LogManager.getLogger(BorrarTemaBean.class).debug(e.getMessage());
        }
        this.resetBean();
        return PATH_BORRAR_TEMA;
    }
    
    public void fetchTemas(){
        this.setTemas(controller.getBorrarTemaController().getTemas());
    }
    
    private void resetBean(){
        this.setTemaId(null);
    }
    
    private boolean validarDatos(){
        return temaId != null;
    }
    
    private void setResult(boolean result){
        this.result = result;
    }
    
    public boolean getResult(){
        return this.result;
    }

    public List<Tema> getTemas() {
        return temas;
    }

    private void setTemas(List<Tema> temas) {
        this.temas = temas;
    }

}
