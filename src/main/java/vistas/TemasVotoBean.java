package vistas;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.logging.log4j.LogManager;

import persistencia.models.entities.Tema;
import utils.Converter;
import controladores.ControllerFactory;

@ManagedBean
public class TemasVotoBean {

    public static final String PATH_TEMAS_VOTOS = "temasVoto";

    @ManagedProperty(value = "#{controllerFactoryEjbs}")
    private ControllerFactory controller;

    private List<Tema> temas;

    private Tema tema;

    private String temaId;

    private boolean result;

    public TemasVotoBean(ControllerFactory controller) {
        this.controller = controller;
    }

    public TemasVotoBean() {
    }

    public ControllerFactory getController() {
        return controller;
    }

    public void setController(ControllerFactory controller) {
        this.controller = controller;
    }

    public List<Tema> getTemas() {
        if (temas == null) {
            this.setTemas(controller.getVotarController().getTemas());
        }
        return temas;
    }

    private void setTemas(List<Tema> temas) {
        this.temas = temas;
    }

    public String process() {
        try {
            this.setResult(validarDatos());
            if (isResult()) {
                Tema buscarTema = new Tema();
                buscarTema.setId(Converter.parseInt(getTemaId()));
                setTema(getTemas().get(getTemas().indexOf(buscarTema)));
                return VotarTemaBean.PATH_VOTAR_TEMA;
            }
        } catch (Exception e) {
            this.setResult(false);
            LogManager.getLogger(TemasVotoBean.class).debug(e.getMessage());
        } finally {
            resetBean();
        }
        return PATH_TEMAS_VOTOS;
    }

    private void resetBean() {
        setTemaId(null);
    }

    private boolean validarDatos() {
        return Converter.parseInt(getTemaId()) != null;
    }

    public String getTemaId() {
        return temaId;
    }

    public void setTemaId(String temaId) {
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
