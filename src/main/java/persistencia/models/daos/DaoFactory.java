package persistencia.models.daos;

import persistencia.models.daos.jpa.DaoJpaFactory;

public abstract class DaoFactory {
    public static DaoFactory factory = null;

    public static void setFactory(DaoFactory factory) {
        DaoFactory.factory = factory;
    }

    public static DaoFactory getFactory() {
        if (factory == null) {
            factory = new DaoJpaFactory();
        }
        return factory;
    }

    public abstract TemaDAO getTemaDao();

    public abstract VotoDAO getVotoDao();

}
