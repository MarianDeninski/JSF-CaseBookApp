package cbook.repository;

import cbook.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private EntityManager entityManager;


    public UserRepositoryImpl() {
    }

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User add(User entity) {


        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public List<User> getAll() {

      return  this.entityManager.createQuery("SELECT u FROM User u",User.class).getResultList();
    }
    @Override
    public User getById(String id) {

     return this.entityManager.createQuery("SELECT u FROM User u WHERE u.id =:id",User.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    @Override
    public User getByName(String name) {
        return this.entityManager.createQuery("SELECT u FROM User u WHERE u.username =:username",User.class)
                .setParameter("username",name)
                .getSingleResult();
    }

    @Override
    public void update(User user) {

        this.entityManager.getTransaction().begin();
        this.entityManager.merge(user);
        this.entityManager.getTransaction().commit();

    }
}
