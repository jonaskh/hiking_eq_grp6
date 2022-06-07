package no.ntnu.hikingstore_6;

import no.ntnu.hikingstore_6.entities.Product;

import no.ntnu.hikingstore_6.entities.ProductInCart;
import no.ntnu.hikingstore_6.entities.User;
import no.ntnu.hikingstore_6.repositories.ProductInCartRepository;
import no.ntnu.hikingstore_6.repositories.ProductRepository;
import no.ntnu.hikingstore_6.repositories.RoleRepository;
import no.ntnu.hikingstore_6.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private ProductInCartRepository productInCartRepository;

    private final Logger logger = LoggerFactory.getLogger("DummyInit");

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        Optional<User> existingUser = userRepository.findByEmail("test@uis.no");



        if (existingUser.isEmpty()) {

            logger.info("Starting dummy init...");

            Product product = new Product("jacket","Jackets",1000,"Large","asd","stygg",9);
            Product product2 = new Product("bottle","Bottles",1000,"Venti","asd","stygg",9);

            productRepository.save(product);
            productRepository.save(product2);





            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String pw1 = "test2022";

            User customer1 = new User("test@uis.no",passwordEncoder.encode(pw1),6300,"Ålesund");
            customer1.addRole(roleRepository.findById(1).get());
            userRepository.save(customer1);

            User customer2 = new User("test@uiv.no",passwordEncoder.encode(pw1),6300,"Ålesund");
            customer2.addRole(roleRepository.findById(2).get());
            userRepository.save(customer2);

            ProductInCart productInCart1 = product.toProductInCart(product);
            ProductInCart productInCart2 = product2.toProductInCart(product2);

            customer1.getCart(customer1.getId()).addProductToCart(productInCart1);
            customer2.getCart(customer2.getId()).addProductToCart(productInCart2);
            productInCartRepository.save(productInCart1);
            productInCartRepository.save(productInCart2);




            logger.info("Done importing data.");
        }  else {
            logger.info("Data already loaded, not importing.");
        }

    }
}
