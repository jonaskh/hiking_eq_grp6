package no.ntnu.hikingstore_6;

import no.ntnu.hikingstore_6.entities.Product;
import no.ntnu.hikingstore_6.entities.Role;
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
        Optional<Product> existingProduct = productRepository.findById(1);
        Optional<User> existingUser = userRepository.findByUsername("Jonas");
        if (existingProduct.isEmpty()) {
            logger.info("Starting dummy init...");
            Product sweater = new Product("sweater","Sweaters", 199,"Medium","asdasdad","Nice sweater", 9);
            Product shoes = new Product("hiking shoe","Shoes", 999,"49","asdasd12e","Nice shoe", 11);



            productRepository.save(sweater);
            productRepository.save(shoes);

            logger.info("Done importing products.");
        }  else {
            logger.info("Data already loaded, not importing.");
        }

        if(existingUser.isEmpty()) {
            User customer2 = new User("Jonas","Jonas@stud.ntnu.no","12345");

            userRepository.save(customer2);



            logger.info("Done importing user.");
        }


    }
}
