package no.ntnu.hikingstore_6.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
public class Controller {


    @GetMapping("/")
    public String showHomePage(Model model) {

        return "index";
    }

}
