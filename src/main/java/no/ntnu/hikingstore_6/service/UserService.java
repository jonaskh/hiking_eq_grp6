package no.ntnu.hikingstore_6.service;

import no.ntnu.hikingstore_6.entities.Cart;
import no.ntnu.hikingstore_6.entities.User;
import no.ntnu.hikingstore_6.exceptions.UserNotFoundException;
import no.ntnu.hikingstore_6.repositories.CartRepository;
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

    public List<User> listAll() {
        return(List<User>) userRepository.findAll();
    }


    @Transactional
    public User save(User user) {
        //register
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            User savedUser = userRepository.save(user);

            // initial Cart
            Cart savedCart = cartRepository.save(new Cart(savedUser));
            savedUser.setCart(savedCart);
            return userRepository.save(savedUser);

        } catch (Exception e) {
            throw new UsernameNotFoundException("User can't be registered");
        }
    }

    public User update(User user) {
        User newUser = new User();
        Optional<User> optionalUser = userRepository.findByEmail(newUser.getEmail());

        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("newUser not found");
        } else {
            newUser = optionalUser.get();
        }

        newUser.setEmail(newUser.getEmail());
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setAddress(newUser.getAddress());
        newUser.setZipcode(newUser.getZipcode());

        return userRepository.save(newUser);
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
