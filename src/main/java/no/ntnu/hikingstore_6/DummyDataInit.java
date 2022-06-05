package no.ntnu.hikingstore_6;

import no.ntnu.hikingstore_6.entities.Product;
import no.ntnu.hikingstore_6.entities.User;
import no.ntnu.hikingstore_6.repositories.ProductRepository;
import no.ntnu.hikingstore_6.repositories.RoleRepository;
import no.ntnu.hikingstore_6.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DummyDataInit implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final Logger logger = LoggerFactory.getLogger("DummyInit");

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Optional<User> existingUser = userRepository.findByUsername("jonaskh");
        if (existingUser.isEmpty()) {
            logger.info("Starting dummy init...");
            Product sweater = new Product("sweater","Sweaters", 199,"Medium","asdasdad","Nice sweater", 9);
            Product shoes = new Product("hiking shoe","Shoes", 999,"49","asdasd12e","Nice shoe", 11);

            User customer1 = new User("jonaskh","jonaskh@stud.ntnu.no","12345");
            userRepository.save(customer1);

            productRepository.save(sweater);
            productRepository.save(shoes);

            logger.info("Done importing data.");
        }  else {
            logger.info("Data already loaded, not importing.");
        }

    }
}
