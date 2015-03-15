package controladores;

public abstract class ControllerFactory {
    
    public static ControllerFactory factory = null;

    public static void setFactory(ControllerFactory factory) {
        assert factory != null;
        ControllerFactory.factory = factory;
    }

    public static ControllerFactory getFactory() {
        assert factory != null;
        return factory;
    }
    
    public abstract VotarController getVotarController();

    public abstract ConsultarController getConsultarController();

    public abstract AgregarTemaController getAgregarTemaController();

    public abstract BorrarTemaController getBorrarTemaController();

}