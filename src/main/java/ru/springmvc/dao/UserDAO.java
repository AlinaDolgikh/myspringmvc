package ru.springmvc.dao;

import ru.springmvc.models.User;

import java.util.List;

public interface UserDAO {
    void createUsersTable();

    public void saveUser(User user);

    List<User> showAllUsers();

    public User getUser(int id);

    public void deleteUser(int id);
}
