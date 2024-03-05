package ru.springmvc.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.springmvc.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void createUsersTable() {
        entityManager.createNativeQuery("""
                CREATE TABLE IF NOT EXISTS users (
                Id SERIAL PRIMARY KEY,
                Name VARCHAR(50),
                LastName VARCHAR(50),
                Department VARCHAR(50),
                Salary INT);""").executeUpdate();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public List<User> showAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    @Transactional
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(int id) {
        entityManager.createQuery("delete from User where id=:userId")
                .setParameter("userId", id).executeUpdate();
    }
}
