package no.ntnu.hikingstore_6.controllers;



import no.ntnu.hikingstore_6.entities.Comment;
import no.ntnu.hikingstore_6.entities.Product;
import no.ntnu.hikingstore_6.exceptions.CommentNotFoundException;
import no.ntnu.hikingstore_6.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService service;

    @GetMapping("/comments")
    public String showCommentList(Model model) {
        List<Comment> listComments = service.listAll();
        model.addAttribute("listComments", listComments);
        return "comments";
    }

    /*@GetMapping("")
    public String showCommentsByProductId(@PathVariable("product_id") Product product, Model model) throws CommentNotFoundException {
        List<Comment> listComments = service.getCommentByProductId(product);

        return "comments";
    }*/


    @PostMapping("/comments/save")
    public String saveComment(Comment comment, RedirectAttributes ra){
        service.save(comment);
        ra.addFlashAttribute("message", "the comment has been saved");
        return "redirect:/comments";

    }

    @GetMapping("/comments/delete/{id}")
    public String deleteComment(@PathVariable("id") Integer id, RedirectAttributes ra) {
        service.delete(id);
        ra.addFlashAttribute("message","Comment has been deleted");
        return "redirect:/comments";

    }



}




