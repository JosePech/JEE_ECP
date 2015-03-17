package vistas;

import java.util.List;

import org.apache.logging.log4j.LogManager;

import persistencia.models.entities.Tema;
import persistencia.models.entities.utils.Escolaridad;
import controladores.ControllerFactory;

public class TemasVotoBean {

    public static final String PATH_TEMAS_VOTOS = "temasVoto";
    
    private ControllerFactory controller;
    
    private List<Tema> temas;
    
    private Tema tema;
    
    private Integer temaId;
    
    private boolean result;
    
    public TemasVotoBean(ControllerFactory controller){
        this.controller = controller;
    }
    
    public void fetchTemas(){
        this.setTemas(controller.getVotarController().getTemas());
    }

    public List<Tema> getTemas() {
        return temas;
    }

    private void setTemas(List<Tema> temas) {
        this.temas = temas;
    }
    
    public String process(){
        try{
            this.setResult(validarDatos());
            if(isResult()){
                Tema buscarTema = new Tema();
                buscarTema.setId(getTemaId());
                setTema(getTemas().get(getTemas().indexOf(buscarTema)));
                return VotarTemaBean.PATH_VOTAR_TEMA;
            }
        }catch(Exception e){
            this.setResult(false);
            LogManager.getLogger(TemasVotoBean.class).debug(e.getMessage());
        }finally{
            resetBean();
        }
        return PATH_TEMAS_VOTOS;
    }
    
    public Escolaridad[] getEscolaridadValues(){
        return Escolaridad.values();
    }
    
    private void resetBean() {
        setTemaId(null);
    }

    private boolean validarDatos(){
        return getTemaId() != null;
    }

    public Integer getTemaId() {
        return temaId;
    }

    public void setTemaId(Integer temaId) {
        this.temaId = temaId;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

}
