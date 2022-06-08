package no.ntnu.hikingstore_6.service;

import no.ntnu.hikingstore_6.entities.Role;
import no.ntnu.hikingstore_6.entities.User;
import no.ntnu.hikingstore_6.exceptions.UserNotFoundException;
import no.ntnu.hikingstore_6.repositories.CartRepository;
import no.ntnu.hikingstore_6.repositories.RoleRepository;
import no.ntnu.hikingstore_6.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> listAll() {
        return(List<User>) userRepository.findAll();
    }

    @Transactional
    public User save(User user) {
        //register
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {

            Role newUserRole = roleRepository.findByName("ROLE_CUSTOMER");
            user.addRole(newUserRole);

            return userRepository.save(user);

        } catch (Exception e) {
            throw new UsernameNotFoundException("User can't be registered");
        }
    }

    public void update(User user) throws UserNotFoundException {
        Optional<User> result = userRepository.findByEmail(user.getEmail());

        if (result.isEmpty()){
            throw new UserNotFoundException("Could not find any users with email " + user.getEmail());
        }

        User updatedUser = result.get();

        updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));

        updatedUser.setAddress(user.getAddress());

        updatedUser.setZipcode(user.getZipcode());

        userRepository.save(updatedUser);

    }

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = userRepository.findById(id);
        if ( result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not be find any users with ID " + id);

    }

    public void delete(String email) throws UserNotFoundException {

        Optional<User> userToDelete = userRepository.findByEmail(email);
        if (userToDelete.isEmpty()) {
            throw new UserNotFoundException("Could not find any users with email " + email);
        }


        userRepository.delete(userToDelete.get());
    }



}
