package vistas;

import org.apache.logging.log4j.LogManager;

import controladores.ControllerFactory;
import persistencia.models.entities.Tema;

public class AgregarTemaBean {

    private Tema tema;
    
    private boolean result;
    
    public static final String PATH_AGREGAR_TEMA = "agregarTema";

    public void setTema(Tema tema) {
        this.tema = tema;
    }
    
    public String process(){
        try{
            this.setResult(validarDatos());
            if(this.getResult()){
                ControllerFactory.getFactory().getAgregarTemaController().agregar(tema);
            }
        }catch(Exception e){
            this.setResult(false);
            LogManager.getLogger(AgregarTemaBean.class).debug(e.getMessage());
        }
        this.resetBean();
        return PATH_AGREGAR_TEMA;
    }
    
    private void resetBean(){
        this.setResult(false);
        this.setTema(null);
    }
    
    private boolean validarDatos(){
        result = false;
        if(tema != null){
            if(tema.getNombre() != null)
                result = tema.getNombre().length() > 0;
            if(tema.getPregunta() != null)
                result = tema.getPregunta().length() > 0;
        }
        return result;
    }
    
    private void setResult(boolean result){
        this.result = result;
    }
    
    public boolean getResult(){
        return this.result;
    }

}
