package persistencia.models.daos.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import persistencia.models.daos.GenericDAO;

public abstract class GenericDaoJpa<T, ID> implements GenericDAO<T, ID> {

    protected Class<T> persistentClass;

    public GenericDaoJpa(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    @Override
    public void create(T entity) {
        EntityManager entityManager = DaoJpaFactory.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            LogManager.getLogger(GenericDaoJpa.class).debug("create: " + entity);
        } catch (Exception e) {
            LogManager.getLogger(GenericDaoJpa.class).error("create: " + e);
            if (entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public T read(ID id) {
        EntityManager entityManager = DaoJpaFactory.getEntityManagerFactory().createEntityManager();
        T entity = entityManager.find(persistentClass, id);
        entityManager.close();
        return entity;
    }

    @Override
    public void update(T entity) {
        EntityManager entityManager = DaoJpaFactory.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
            LogManager.getLogger(GenericDaoJpa.class).debug("update: " + entity);
        } catch (Exception e) {
            LogManager.getLogger(GenericDaoJpa.class).error("update: " + e);
            if (entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteById(ID id) {
        EntityManager entityManager = DaoJpaFactory.getEntityManagerFactory().createEntityManager();
        T entity = entityManager.find(persistentClass, id);
        if (entity != null) {
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(entity);
                entityManager.getTransaction().commit();
                LogManager.getLogger(GenericDaoJpa.class).debug("delete: " + entity);
            } catch (Exception e) {
                LogManager.getLogger(GenericDaoJpa.class).error("delete: " + e);
                if (entityManager.getTransaction().isActive())
                    entityManager.getTransaction().rollback();
            } finally {
                entityManager.close();
            }
        }
    }

    @Override
    public List<T> findAll() {
        EntityManager entityManager = DaoJpaFactory.getEntityManagerFactory().createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.persistentClass);

        Root<T> root = criteriaQuery.from(this.persistentClass);

        criteriaQuery.select(root);

        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(0); // El primero es 0
        typedQuery.setMaxResults(0); // Se realiza la query, se buscan todos
        List<T> result = typedQuery.getResultList();
        entityManager.close();
        return result;
    }

}
