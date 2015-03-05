package persistencia.models.daos.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import persistencia.models.daos.TemaDAO;
import persistencia.models.entities.Tema;

public class TemaDaoJpa extends GenericDaoJpa<Tema, Integer> implements TemaDAO {

    @Override
    public void create(Tema entity) {
        assert entity != null;
        EntityManager em = DaoJpaFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public Tema read(Integer id) {
        assert id != null;
        EntityManager em = DaoJpaFactory.getEntityManagerFactory().createEntityManager();
        return em.find(Tema.class, id);
    }

    @Override
    public void update(Tema entity) {
        assert entity.getId() != null;
        EntityManager em = DaoJpaFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    @Override
    public void deleteById(Integer id) {
        assert id != null;
        EntityManager em = DaoJpaFactory.getEntityManagerFactory().createEntityManager();
        Tema tema = em.find(Tema.class, id);
        em.getTransaction().begin();
        em.remove(tema);
        em.getTransaction().commit();
    }

    @Override
    public List<Tema> findAll() {
        EntityManager em = DaoJpaFactory.getEntityManagerFactory().createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tema> criteriaQuery = criteriaBuilder.createQuery(Tema.class);
        Root<Tema> rootTema = criteriaQuery.from(Tema.class);
        criteriaQuery.select(rootTema);
        TypedQuery<Tema> temaQuery = em.createQuery(criteriaQuery);
        return temaQuery.getResultList();
    }

}
