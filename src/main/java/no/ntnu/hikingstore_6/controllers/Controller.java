package no.ntnu.hikingstore_6.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/home")
    public String showHomePage() {
        return "test";
    }

}
