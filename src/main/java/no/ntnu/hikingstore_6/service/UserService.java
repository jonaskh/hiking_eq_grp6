package no.ntnu.hikingstore_6.service;

import no.ntnu.hikingstore_6.entities.User;
import no.ntnu.hikingstore_6.exceptions.UserNotFoundException;
import no.ntnu.hikingstore_6.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository repo;

    public List<User> listAll() {
        return(List<User>) repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = repo.findById(id);
        if ( result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not be find any users with ID " + id);

    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count= repo.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could not find any users with ID " + id);
        }
        repo.deleteById(id);
    }


    /**

    public User getCurrentlyLoggedInUser(Authentication authentication) {

        if(authentication == null) return null;

        User user = null;
        Object principal = authentication.getPrincipal();

        user = CustomerUserDetails


        return user;
    }
     */

}
