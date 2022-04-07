package no.ntnu.hikingstore_6.controllers;

import no.ntnu.hikingstore_6.models.AuthenticateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class Controller {


    @GetMapping("")
    public String homePage() {

        return "Home Page";
    }

    @GetMapping("user")

    public String userPage() {

        return "User page";
    }

    @GetMapping("admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminPage() {
        return "Admin page";
    }




}
