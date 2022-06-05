package no.ntnu.hikingstore_6.controllers;

import no.ntnu.hikingstore_6.entities.Product;
import no.ntnu.hikingstore_6.repositories.ProductRepository;
import no.ntnu.hikingstore_6.service.ProductService;
import no.ntnu.hikingstore_6.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
public class ProductController {
    @Autowired private ProductService service;

    @Autowired
    ProductRepository repo;

    @GetMapping("/products")
    public String showUserList(Model model) {
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);
        return "products";
    }

    @GetMapping("/products/new")
    public String showNewForm(Model model) {
        model.addAttribute("product",new Product());
        model.addAttribute("pageTitle", "Add New Product");
        return "product_form";
    }

    @PostMapping("/products/save")
    public String saveUser(Product product, RedirectAttributes ra){
        service.save(product);
        ra.addFlashAttribute("message", "the product has been saved");
        return "redirect:/products";

    }

    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Product product = service.get(id);
            model.addAttribute("product", product);
            model.addAttribute("pageTitle", "Edit Product (ID: " + id + ")");

            return "product_form";
        } catch (ProductNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/products";
        }
    }
    @GetMapping("/products/delete/{id}")
    public String showEditForm(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message","Product has been deleted");
        } catch (ProductNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/products";

    }

    @GetMapping("/test")
    @RolesAllowed({"ROLE_EDITOR"})
    public String hello() {
        return "products";
    }


    @PostMapping("/test")
    @RolesAllowed({"ROLE_EDITOR"})
    public ResponseEntity<Product> create(@RequestBody @Valid Product product) {
        Product savedProduct = repo.save(product);
        URI productURI = URI.create("/products/" + savedProduct.getId());
        return ResponseEntity.created(productURI).body(savedProduct);
    }



    @GetMapping("/products/productCards/{id}")
    public String showSingleProduct(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Product product = service.get(id);
            model.addAttribute("product", product);

            return "productCards";

        } catch (ProductNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/products";
        }
    }



}
