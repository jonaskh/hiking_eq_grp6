package no.ntnu.hikingstore_6.controllers;

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
    private UserRepository userRepo;

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @RolesAllowed("ROLE_ADMIN")
    public String showUserList(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }



    @PostMapping("/users/new")
    public String showNewForm(Model model) {
        model.addAttribute("user",new User());
        model.addAttribute("pageTitle", "Add New User");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra){
        service.save(user);
        ra.addFlashAttribute("message", "the user has been saved");
        return "redirect:/users";

    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");

            return "user_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }
    @GetMapping("/users/delete/{email}")
    public String showEditForm(@PathVariable("email") String email, RedirectAttributes ra) {
        try {
            service.delete(email);
        ra.addFlashAttribute("message","User has been deleted");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";

    }

    @PostMapping("/register")
    public ResponseEntity<User> save(@RequestBody User user) {
        try {
            User newUser = userService.save(user);
            return  new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/delete/user/{email}")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<User> delete(@PathVariable("email") String email) throws UserNotFoundException {
        userService.delete(email);

        return ResponseEntity.ok().build();
    }


    @PutMapping("/edit/user/{email}")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity update(@PathVariable("email") String email, @Valid @RequestBody User user) throws UserNotFoundException {

        userService.update(user);

        return ResponseEntity.ok().build();
    }


    /*@PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "register_success";
    }*/



}
