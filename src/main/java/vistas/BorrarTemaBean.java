package vistas;

import org.apache.logging.log4j.LogManager;

import controladores.ControllerFactory;
import persistencia.models.entities.Tema;

public class BorrarTemaBean {
    
    private Integer temaId;
    
    private boolean result;
    
    public static final String PATH_BORRAR_TEMA = "borrarTema";

    public void setTemaId(Integer temaId) {
        this.temaId = temaId;
    }
    
    public String process(){
        try{
            this.setResult(validarDatos());
            if(this.getResult()){
                Tema tema = new Tema();
                tema.setId(temaId);
                ControllerFactory.getFactory().getBorrarTemaController().borrar(tema);
            }
        }catch(Exception e){
            this.setResult(false);
            LogManager.getLogger(AgregarTemaBean.class).debug(e.getMessage());
        }
        this.resetBean();
        return PATH_BORRAR_TEMA;
    }
    
    private void resetBean(){
        this.setResult(false);
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

}
