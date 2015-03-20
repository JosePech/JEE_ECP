package vistas;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.logging.log4j.LogManager;

import controladores.ControllerFactory;
import persistencia.models.entities.Tema;
import utils.Converter;

@ManagedBean
@RequestScoped
public class BorrarTemaBean{

    @ManagedProperty(value = "#{controllerFactoryEjbs}")
    private ControllerFactory controller;
    
    @ManagedProperty(value = "#{accesoTemasBean}")
    private AccesoTemasBean accesoBean;
    
    @PostConstruct
    public void update() {
        System.out.println("pase por aqui..." + accesoBean.getAccesoDenegado());
        System.out.println("pase por aqui..." + accesoBean.getClave());
        accesoDenegado = accesoBean.getAccesoDenegado();
    }
    
    public AccesoTemasBean getAccesoBean() {
        return accesoBean;
    }

    public void setAccesoBean(AccesoTemasBean accesoBean) {
        this.accesoBean = accesoBean;
    }

    private Integer accesoDenegado;
    
    private String temaId2;
    
    private boolean result;
    
    private List<Tema> temas;
    
    public static final String PATH_BORRAR_TEMA = "borrarTema";
    
    public BorrarTemaBean(ControllerFactory factory){
        this.controller = factory;
    }
    
    public BorrarTemaBean(){
    }

    public ControllerFactory getController() {
        return controller;
    }

    public void setController(ControllerFactory controller) {
        this.controller = controller;
    }

    public String getTemaId2() {
        return temaId2;
    }

    public void setTemaId2(String temaId) {
        this.temaId2 = temaId;
    }
    
    public String process(){
        System.out.println(accesoDenegado);
        if(accesoDenegado == null || accesoDenegado == -1){
            return AccesoTemasBean.PATH_ACCESO_TEMA;
        }
        try{
            this.setResult(validarDatos());
            if(this.getResult()){
                Tema tema = new Tema();
                tema.setId(Converter.parseInt(temaId2));
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
        this.setTemaId2(null);
    }
    
    private boolean validarDatos(){
        return temaId2 != null;
    }
    
    private void setResult(boolean result){
        this.result = result;
    }
    
    public boolean getResult(){
        return this.result;
    }

    public List<Tema> getTemas() {
        this.setTemas(controller.getBorrarTemaController().getTemas());
        return temas;
    }

    private void setTemas(List<Tema> temas) {
        this.temas = temas;
    }

    public Integer getAccesoDenegado() {
        return accesoDenegado;
    }

    public void setAccesoDenegado(Integer accesoDenegado) {
        this.accesoDenegado = accesoDenegado;
    }

}
