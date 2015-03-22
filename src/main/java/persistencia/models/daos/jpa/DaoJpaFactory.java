package persistencia.models.daos.jpa;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.persistence.config.PersistenceUnitProperties;

import persistencia.models.daos.DaoFactory;
import persistencia.models.daos.TemaDAO;
import persistencia.models.daos.VotoDAO;

public class DaoJpaFactory extends DaoFactory {

    private static final String PERSISTENCE_UNIT = "VotacionApp";

    private static EntityManagerFactory entityManagerFactory = null;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        return entityManagerFactory;
    }

    public static void dropAndCreateTables() {
        Map<String, String> properties = new HashMap<>();
        properties.put(PersistenceUnitProperties.DDL_GENERATION,
                PersistenceUnitProperties.DROP_AND_CREATE);
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, properties);
    }

    @Override
    public TemaDAO getTemaDao() {
        return new TemaDaoJpa();
    }

    @Override
    public VotoDAO getVotoDao() {
        return new VotoDaoJpa();
    }
}
