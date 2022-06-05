package no.ntnu.hikingstore_6.service;

import no.ntnu.hikingstore_6.entities.Role;
import no.ntnu.hikingstore_6.entities.User;
import no.ntnu.hikingstore_6.security.CustomUserDetails;
import no.ntnu.hikingstore_6.repositories.RoleRepository;
import no.ntnu.hikingstore_6.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccessUserService implements UserDetailsService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;


/*    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new CustomUserDetails(user);
        } else {
            throw new UsernameNotFoundException("No user with that username found");
        }
    }*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return new User("foo","foo");

    }

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
            Optional<Role> customerRole = roleRepository.findByName("ROLE_CUSTOMER");
            if (customerRole.isPresent()) {
                User user = new User(username, email, encryptPassword(password));
                user.addRole(customerRole.get());
                userRepository.save(user);
            }
        }
        return errorMsg;
    }

    public String encryptPassword(String password) {

        return BCrypt.hashpw(password,BCrypt.gensalt());
    }
}
