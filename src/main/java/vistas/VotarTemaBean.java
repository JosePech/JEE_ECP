package vistas;

import org.apache.logging.log4j.LogManager;

import persistencia.models.entities.Tema;
import persistencia.models.entities.Voto;
import persistencia.models.entities.utils.Escolaridad;
import controladores.ControllerFactory;

public class VotarTemaBean {
    
    private ControllerFactory controller;
    
    private Integer temaId;
    
    private String ip;
    
    private Integer voto;
    
    private Escolaridad escolaridad;
    
    private boolean result;

    public VotarTemaBean(ControllerFactory controller) {
        this.controller = controller;
    }

    public static final String PATH_VOTAR_TEMA = "votarTema";

    public String process() {
        try{
            this.setResult(validarDatos());
            if(this.getResult()){                
                Tema tema = new Tema();
                tema.setId(temaId);
                controller.getVotarController().votar(new Voto(tema, voto, escolaridad, ip));
            }
        }catch(Exception e){
            this.setResult(false);
            LogManager.getLogger(VotarTemaBean.class).debug(e.getMessage());            
        }finally{
            this.resetBean();
        }        
        return PATH_VOTAR_TEMA;
    }
    
    private void resetBean(){
        this.temaId = null;
        this.ip = null;
        this.voto = null;
        this.escolaridad = null;
    }
    
    private boolean validarDatos(){
        boolean valido = true;
        valido &= temaId != null;        
        valido &= voto != null;
        valido &= ip != null;
        valido &= escolaridad != null;
        //LogManager.getLogger(VotarTemaBean.class).debug(String.format("TemaId: %s , Voto: %d , Ip: %s , Escolaridad: %s", temaId, voto, ip, escolaridad.toString()));
        return valido;
    }
    
    private void setResult(boolean result){
        this.result = result;
    }
    
    public boolean getResult(){
        return this.result;
    }

    public void setTemaId(Integer id) {
         this.temaId = id;   
    }

    public void setIp(String hostAddress) {
        this.ip = hostAddress;
    }

    public void setVoto(Integer parameter) {
        this.voto = parameter;
    }

    public void setEscolaridad(String parameter) {
        this.escolaridad = Escolaridad.valueOf(parameter);
    }
}
