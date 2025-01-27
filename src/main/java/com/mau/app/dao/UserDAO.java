package com.mau.app.dao;

import com.mau.app.model.User;

import java.util.List;

public interface UserDAO {
    /**
     * Creates a new user.
     *
     * @param user the user to be created
     * @return the created user
     */
    User createUser(User user);

    /**
     * Retrieves a user by ID.
     *
     * @param id the ID of the user to be retrieved
     * @return the retrieved user
     */
    User getUserById(int id);

    /**
     * Updates an existing user.
     *
     * @param user the user to be updated
     * @return the updated user
     */
    User updateUser(User user);

    /**
     * Deletes a user by ID.
     *
     * @param id the ID of the user to be deleted
     * @return true if the user is deleted, false otherwise
     */
    boolean deleteUser(int id);

    /**
     * Retrieves all users.
     *
     * @return a list of all users
     */
    List<User> getAllUsers();


    User getUserByNameAndPassword(String name, String password);

}