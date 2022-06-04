package no.ntnu.hikingstore_6.service;

import no.ntnu.hikingstore_6.models.Role;
import no.ntnu.hikingstore_6.models.User;
import no.ntnu.hikingstore_6.repositories.RoleRepository;
import no.ntnu.hikingstore_6.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessUserService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * Return a list of all users in the database
     * @return a list of users
     */
    public List<User> listUsers() {

        return (List<User>) this.userRepository.findAll();
    }

    public String createNewUser(String email, String username, String password) {
        String errorMsg = null;
        if (email.isEmpty() || username.isEmpty() || password.length() < 8) {
            errorMsg = "Fields cannot be empty, and password must be more than 8 letters. Please try again.";
            return errorMsg;
        } else {
            Role customerRole = roleRepository.findByName("ROLE_CUSTOMER");
            if (customerRole != null) {
                User user = new User(username, email, encryptPassword(password));
                user.addRole(customerRole);
                userRepository.save(user);
            }
        }
        return errorMsg;
    }

    public String encryptPassword(String password) {

        return BCrypt.hashpw(password,BCrypt.gensalt());
    }
}
