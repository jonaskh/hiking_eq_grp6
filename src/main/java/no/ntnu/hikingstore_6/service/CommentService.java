package no.ntnu.hikingstore_6.service;


import no.ntnu.hikingstore_6.entities.Comment;
import no.ntnu.hikingstore_6.entities.Product;
import no.ntnu.hikingstore_6.exceptions.CommentNotFoundException;
import no.ntnu.hikingstore_6.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

        @Autowired
        private CommentRepository repo;

        public List<Comment> listAll() {
            return(List<Comment>) repo.findAll();
        }


        public void save(Comment comment) {
            repo.save(comment);
        }

        public List<Comment> getCommentByProductId(Product product) throws CommentNotFoundException {
            return repo.findByProduct_Id(product.getId());
        }

    public void delete(Integer id) {
    }



}
