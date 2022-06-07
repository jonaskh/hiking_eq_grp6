package no.ntnu.hikingstore_6;

import no.ntnu.hikingstore_6.entities.Cart;
import no.ntnu.hikingstore_6.entities.Product;

import no.ntnu.hikingstore_6.entities.ProductInCart;
import no.ntnu.hikingstore_6.entities.User;
import no.ntnu.hikingstore_6.repositories.ProductInCartRepository;
import no.ntnu.hikingstore_6.repositories.ProductRepository;
import no.ntnu.hikingstore_6.repositories.RoleRepository;
import no.ntnu.hikingstore_6.repositories.UserRepository;
import no.ntnu.hikingstore_6.service.ShoppingCartService;
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

    @Autowired
    private ShoppingCartService cartService;

    private final Logger logger = LoggerFactory.getLogger("DummyInit");

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        Optional<User> existingUser = userRepository.findByEmail("test@uis.no");




        if (existingUser.isEmpty()) {

            logger.info("Starting dummy init...");

            Product sweater = new Product("Sweater","Sweaters",1000,"Large","asd","The classic sweater from devold.",9);
            Product greySweater = new Product("Grey Sweater","Sweaters",1000,"Medium","asd","New sweater from bergans.",9);

            productRepository.save(sweater);
            productRepository.save(greySweater);

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String pw1 = "test2022";

            User customer1 = new User("test@uis.no",passwordEncoder.encode(pw1),6300,"Ålesund");
            customer1.addRole(roleRepository.findById(1).get());
            userRepository.save(customer1);

            User customer2 = new User("test@uiv.no",passwordEncoder.encode(pw1),6300,"Ålesund");
            customer2.addRole(roleRepository.findById(2).get());
            userRepository.save(customer2);


            cartService.addProductToCart(customer1.getCartID(),sweater);
            cartService.addProductToCart(customer2.getCartID(),greySweater);




            logger.info("Done importing data.");
        }  else {
            logger.info("Data already loaded, not importing.");
        }

    }
}
