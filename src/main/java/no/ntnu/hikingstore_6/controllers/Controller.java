package no.ntnu.hikingstore_6.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class Controller {


    @GetMapping("/")
    public String homePage() {

        return "Home Page";
    }

    @GetMapping("/user")
    public String userPage() {

        return "User page";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminPage() {
        return "Admin page";
    }



    @RequestMapping("/products")
    public String getProducts(Model model) {

        return productPage;

    }
}
