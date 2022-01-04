package com.dao;

import com.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDaoHibernateImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUserById(long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void updateUser(User user, long id) {
        user.setId(id);
        entityManager.merge(user);
    }

    @Override
    public User getUser(long id){
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Transactional
    @Override
    public User getUser(String username) {
        TypedQuery<User> q = (entityManager.createQuery("select u from User u " +
                "join fetch u.roles where u.username = :username",User.class));
        q.setParameter("username",username);
        return q.getResultList().stream().findFirst().orElse(null);

    }

}
