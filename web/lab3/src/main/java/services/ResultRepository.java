package services;

import entity.ResultEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ResultRepository {

    @PersistenceContext(unitName = "PU")
    private EntityManager entityManager;

    public List<ResultEntity> findAll() {
        return entityManager.createQuery("SELECT r FROM ResultEntity r", ResultEntity.class).getResultList();
    }

    @Transactional
    public void save(ResultEntity entity) {
        entityManager.persist(entity);
    }
}
