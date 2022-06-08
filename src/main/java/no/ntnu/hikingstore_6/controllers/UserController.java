package no.ntnu.hikingstore_6.controllers;

import no.ntnu.hikingstore_6.dtos.NewUserDTO;
import no.ntnu.hikingstore_6.entities.User;
import no.ntnu.hikingstore_6.exceptions.UserNotFoundException;
import no.ntnu.hikingstore_6.repositories.UserRepository;
import no.ntnu.hikingstore_6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService service;


    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @RolesAllowed("ROLE_ADMIN")
    public String showUserList(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }


    @PostMapping("/process_register")
    public String processRegister(User user) {


        userService.save(user);

        return "register_success";
    }


    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());


        return "login_form";
    }


    @DeleteMapping("/delete/user/{email}")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<User> delete(@PathVariable("email") String email) throws UserNotFoundException {
        userService.delete(email);

        return ResponseEntity.ok().build();
    }




}
