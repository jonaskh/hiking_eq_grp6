package no.ntnu.hikingstore_6;

import no.ntnu.hikingstore_6.models.Role;
import no.ntnu.hikingstore_6.models.User;
import no.ntnu.hikingstore_6.repositories.RoleRepository;
import no.ntnu.hikingstore_6.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * A class which inserts some dummy data into the database, when Spring Boot app has started
 */
@Component
public class UserInit implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final Logger logger = LoggerFactory.getLogger("DummyInit");

    /**
     * This method is called when the application is ready (loaded)
     *
     * @param event Event which we don't use :)
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Optional<User> existingUser = userRepository.findByUsername("userTest");
        if (existingUser.isEmpty()) {
            logger.info("Importing test data...");

            User userTest = new User("userTest", "$2a$12$/NoknpFFPDlzL3kBryJfsur0yeYC2JFqAs7Fd79ypMP6PN/mtSYmC");
            User adminTest = new User("adminTest", "$2a$10$nwbEjYKgcomq2rjUPge2JegqI.y4zEcNqRMPdqwFnd1ytorNCQM/y");
            Role user = new Role("ROLE_USER");
            Role admin = new Role("ROLE_ADMIN");

            userTest.addRole(user);
            adminTest.addRole(admin);

            roleRepository.save(user);
            roleRepository.save(admin);

            userRepository.save(userTest);
            userRepository.save(adminTest);

            logger.info("DONE importing test data");
        } else {
            logger.info("Users already in the database, not importing anything");
        }
    }
}

