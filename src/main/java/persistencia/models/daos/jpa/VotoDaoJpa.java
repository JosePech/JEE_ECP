package persistencia.models.daos.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import persistencia.models.daos.VotoDAO;
import persistencia.models.entities.Voto;
import persistencia.models.entities.utils.VotoSummary;

public class VotoDaoJpa extends GenericDaoJpa<Voto, Integer> implements VotoDAO {

    public VotoDaoJpa() {
        super(Voto.class);
    }

    @Override
    public List<VotoSummary> getSummary() {
        EntityManager entityManager = DaoJpaFactory.getEntityManagerFactory().createEntityManager();
        // Se crea un criterio de consulta
        TypedQuery<VotoSummary> query = entityManager.createNamedQuery(Voto.FIND_ALL_AVG_GROUP_TEMA_ESCOLARIDAD, VotoSummary.class);
        List<VotoSummary> result = query.getResultList();
        entityManager.close();
        return result;
    }
}