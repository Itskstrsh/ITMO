package service;

import dao.UserDAO;
import entity.UserEntity;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import utils.PasswordHasher;


import java.util.Optional;

@Stateless
public class UserService {

    @Inject
    private UserDAO userDAO;

    public UserEntity registerUser(String username, String password) {
        String passwordHash = PasswordHasher.hashPassword(password);
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordHash);
        userDAO.save(user);
        return user;
    }

    public UserEntity authenticateUser(String username, String password) {
        Optional<UserEntity> optionalUser = userDAO.findByUsername(username);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            if (PasswordHasher.verifyPassword(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
    public UserEntity findUserById(Long userId) {
        return userDAO.findById(userId).orElse(null);
    }

}
