package com.mau.app.dao;

import com.mau.app.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpLocal implements UserDAO {
    private final List<User> storeUsers;

    public UserDaoImpLocal() {
        this.storeUsers = new ArrayList<>();
        // Initialize with 6 example users
        storeUsers.add(new User(1, "John", "Doe", "password123"));
        storeUsers.add(new User(2, "Jane", "Doe", "password456"));
        storeUsers.add(new User(3, "Bob", "Smith", "password789"));
        storeUsers.add(new User(4, "Alice", "Johnson", "password1011"));
        storeUsers.add(new User(5, "Mike", "Williams", "password1213"));
        storeUsers.add(new User(6, "Emma", "Davis", "password1415"));
        storeUsers.add(new User(7, "mau", "Davis", "passmau"));
    }

    @Override
    public User createUser(User user) {
        user.setId(getNextId());
        storeUsers.add(user);
        return user;
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) {
        return storeUsers.stream()
                .filter(user -> user.getName().equals(name) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User getUserById(int id) {
        return storeUsers.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public User updateUser(User user) {
        Optional<User> existingUser = storeUsers.stream()
                .filter(u -> u.getId() == user.getId())
                .findFirst();

        if (existingUser.isPresent()) {
            existingUser.get().setName(user.getName());
            existingUser.get().setLastName(user.getLastName());
            return existingUser.get();
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteUser(int id) {
        return storeUsers.removeIf(user -> user.getId() == id);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(storeUsers);
    }

    private int getNextId() {
        return storeUsers.isEmpty() ? 1 : storeUsers.get(storeUsers.size() - 1).getId() + 1;
    }
}