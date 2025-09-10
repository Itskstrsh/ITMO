package dao;

import entity.ResultEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Stateless
public class ResultDAO {

    @PersistenceContext(unitName = "PU")
    private EntityManager em;

    @Transactional
    public void save(ResultEntity result) {
        em.persist(result);
    }
    public List<ResultEntity> findByUserId(Long userId) {
        return em.createQuery("SELECT r FROM ResultEntity r WHERE r.user.id = :userId", ResultEntity.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
