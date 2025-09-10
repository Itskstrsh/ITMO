package dao;

import entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Optional;

public class UserDAO {
    @PersistenceContext(unitName = "PU")
    private EntityManager em;

    @Transactional
    public void save(UserEntity user) {
        em.persist(user);
    }

    public Optional<UserEntity> findByUsername(String username) {
        return em.createQuery("SELECT u FROM UserEntity u WHERE u.username = :username", UserEntity.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }
    public Optional<UserEntity> findById(Long id) {
        return em.find(UserEntity.class, id) != null ? Optional.of(em.find(UserEntity.class, id)) : Optional.empty();
    }

}
