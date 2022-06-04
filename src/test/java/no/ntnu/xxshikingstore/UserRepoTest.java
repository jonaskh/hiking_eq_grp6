package no.ntnu.hikingstore_6;

import no.ntnu.hikingstore_6.entities.User;
import no.ntnu.hikingstore_6.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepoTest {
    @Autowired
    private UserRepository userRepository;

    /*
    Positive test to check users get properly added to the repository.
     */
    @Test
    public void addUser() {
        User user = new User("jonaskh","password","jonaskh@ntnu.no");
        User savedUser = userRepository.save(user);

        Assertions.assertThat((userRepository.findByUsername(user.getUsername()).isPresent()));
        Assertions.assertThat(savedUser.getUsername()).isNotEmpty();
    }

    @Test
    public void updateUser() {
        Optional<User> optionalUser = userRepository.findByUsername("jonaskh");
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEmail("jonaskh@gmail.com");

            User updatedUser = null;
            if (userRepository.findByUsername("jonaskh").isPresent()) {
                updatedUser = userRepository.findByUsername("jonaskh").get();
                Assertions.assertThat(updatedUser.getEmail().equalsIgnoreCase("jonaskh@gmail.com"));

            }
        }
    }

    @Test
    public void deleteUserByUsername() {
        String usernameToDelete = "jonaskh";

        Optional<User> userToDelete = userRepository.findByUsername(usernameToDelete);
        if (userToDelete.isPresent()) {
            User user = userToDelete.get();
            userRepository.delete(user);

            Optional<User> deletedUser = userRepository.findByUsername("jonaskh");
            Assertions.assertThat(deletedUser.isEmpty());

        }
    }
}
