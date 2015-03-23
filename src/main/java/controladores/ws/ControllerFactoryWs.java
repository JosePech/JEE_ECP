package controladores.ws;

import controladores.AgregarTemaController;
import controladores.BorrarTemaController;
import controladores.ConsultarController;
import controladores.ControllerFactory;
import controladores.VotarController;

public class ControllerFactoryWs extends ControllerFactory{

    @Override
    public VotarController getVotarController() {
        return new VotarControllerWs();
    }

    @Override
    public ConsultarController getConsultarController() {
        return new ConsultarControllerWs();
    }

    @Override
    public AgregarTemaController getAgregarTemaController() {
        return new AgregarTemaControllerWs();
    }

    @Override
    public BorrarTemaController getBorrarTemaController() {
        return new BorrarTemaControllerWs();
    }

}
