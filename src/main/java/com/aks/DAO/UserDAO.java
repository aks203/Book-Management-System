package com.aks.DAO;

import com.aks.Entity.User;

import java.util.List;

public interface UserDAO{
    /**
     *
     * @param user
     * @return user_id
     */
    void createUser(User user);
    /**
     * @param id
     * @return User
     */
    User getUser(int id);

    /**
     *
     * @param email
     * @return
     */
    User getUser(String email);

    int deleteUser(int user_id);

    String getPasswordByEmail(String email);

    List<User> getUsers();
}