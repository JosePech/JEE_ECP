package vistas;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.logging.log4j.LogManager;

import controladores.ControllerFactory;
import persistencia.models.entities.Tema;

@ManagedBean
public class AgregarTemaBean {

    @ManagedProperty(value = "#{controllerFactoryEjbs}")
    private ControllerFactory controller;

    private int result;

    public static final String PATH_AGREGAR_TEMA = "agregarTema";

    private String temaNombre;

    private String temaPregunta;

    public AgregarTemaBean(ControllerFactory controller) {
        this.controller = controller;
    }

    public AgregarTemaBean() {
    }

    public String process() {
        try {
            this.setResult(validarDatos());
            if (this.getResult() == 1) {
                controller.getAgregarTemaController().agregar(
                        new Tema(getTemaNombre(), getTemaPregunta()));
            }
        } catch (Exception e) {
            this.setResult(-1);
            LogManager.getLogger(AgregarTemaBean.class).debug(e.getMessage());
        }
        this.resetBean();
        return PATH_AGREGAR_TEMA;
    }

    private void resetBean() {
        this.setTemaNombre(null);
        this.setTemaPregunta(null);
    }

    private int validarDatos() {
        int resultado = -1;
        if (this.getTemaNombre() != null)
            resultado = this.getTemaNombre().length() > 0 ? 1 : -1;
        if (this.getTemaPregunta() != null)
            resultado = this.getTemaPregunta().length() > 0 ? 1 : -1;
        return resultado;
    }

    private void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return this.result;
    }

    public ControllerFactory getController() {
        return controller;
    }

    public void setController(ControllerFactory controller) {
        this.controller = controller;
    }

    public String getTemaNombre() {
        return temaNombre;
    }

    public void setTemaNombre(String temaNombre) {
        this.temaNombre = temaNombre;
    }

    public String getTemaPregunta() {
        return temaPregunta;
    }

    public void setTemaPregunta(String temaPregunta) {
        this.temaPregunta = temaPregunta;
    }

}
