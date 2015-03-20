package vistas;

import java.io.Serializable;
import java.net.InetAddress;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.logging.log4j.LogManager;

import persistencia.models.entities.Tema;
import persistencia.models.entities.Voto;
import persistencia.models.entities.utils.Escolaridad;
import utils.Converter;
import controladores.ControllerFactory;

@ManagedBean
public class VotarTemaBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @ManagedProperty(value = "#{controllerFactoryEjbs}")
    private ControllerFactory controller;
    
    @ManagedProperty("#{param.temaId}")
    private String temaId;
    
    private String ip;
    
    private String voto;
    
    private Escolaridad escolaridad;
    
    private int result;

    public VotarTemaBean(ControllerFactory controller) {
        this.controller = controller;
    }
    
    public VotarTemaBean() {
    }

    public static final String PATH_VOTAR_TEMA = "votarTema";

    public String process() {
        try{
            this.setIp(InetAddress.getLocalHost().getHostAddress());
            this.setResult(validarDatos());
            if(this.getResult() == 1){                
                Tema tema = new Tema();
                tema.setId(Converter.parseInt(temaId));
                controller.getVotarController().votar(new Voto(tema, Converter.parseInt(voto), escolaridad, ip));
            }
        }catch(Exception e){
            this.setResult(-1);
            LogManager.getLogger(VotarTemaBean.class).debug(e.getMessage());            
        }finally{
            this.resetBean();
        }        
        return TemasVotoBean.PATH_TEMAS_VOTOS;
    }
    
    private void resetBean(){
        this.temaId = null;
        this.ip = null;
        this.voto = null;
        this.escolaridad = null;
    }
    
    private int validarDatos(){
        int valido = 1;
        valido = Converter.parseInt(temaId) != null ? valido : -1;        
        valido = Converter.parseInt(voto) != null ? valido : -1;
        valido = ip != null ? valido : -1;
        valido = escolaridad != null ? valido: -1;
        LogManager.getLogger(VotarTemaBean.class).debug(String.format("TemaId: %s , Voto: %s , Ip: %s , Escolaridad: %s", temaId, voto, ip, escolaridad.toString()));
        return valido;
    }
    
    public Escolaridad[] getEscolaridadValues(){
        return Escolaridad.values();
    }
    
    private void setResult(int result){
        this.result = result;
    }
    
    public int getResult(){
        return this.result;
    }

    public void setTemaId(String id) {
         this.temaId = id;   
    }

    public void setIp(String hostAddress) {
        this.ip = hostAddress;
    }

    public void setVoto(String parameter) {
        this.voto = parameter;
    }

    public void setEscolaridad(String parameter) {
        if(parameter != null)
        this.escolaridad = Escolaridad.valueOf(parameter);
    }

    public ControllerFactory getController() {
        return controller;
    }

    public void setController(ControllerFactory controller) {
        this.controller = controller;
    }

    public Escolaridad getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(Escolaridad escolaridad) {
        this.escolaridad = escolaridad;
    }

    public String getTemaId() {
        return temaId;
    }

    public String getIp() {
        return ip;
    }

    public String getVoto() {
        return voto;
    }
}
