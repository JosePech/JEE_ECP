package controladores.ejbs;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import controladores.AgregarTemaController;
import controladores.BorrarTemaController;
import controladores.ConsultarController;
import controladores.ControllerFactory;
import controladores.VotarController;

@ManagedBean
@ApplicationScoped
public class ControllerFactoryEjbs extends ControllerFactory {

    @Override
    public VotarController getVotarController() {
        return new VotarControllerEjbs();
    }

    @Override
    public ConsultarController getConsultarController() {
        return new ConsultarControllerEjbs();
    }

    @Override
    public AgregarTemaController getAgregarTemaController() {
        return new AgregarControllerEjbs();
    }

    @Override
    public BorrarTemaController getBorrarTemaController() {
        return new BorrarControllerEjbs();
    }
}
