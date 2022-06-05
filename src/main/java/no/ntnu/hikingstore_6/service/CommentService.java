package no.ntnu.hikingstore_6.service;


import no.ntnu.hikingstore_6.entities.Comment;
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

        public Comment get(Integer id) throws CommentNotFoundException {
            Optional<Comment> result = repo.findById(id);
            if ( result.isPresent()) {
                return result.get();
            }
            throw new CommentNotFoundException("Could not be find any users with ID " + id);

        }

        public void delete(Integer id) throws CommentNotFoundException {
            Long count= repo.countById(id);
            if (count == null || count == 0) {
                throw new CommentNotFoundException("Could not find any users with ID " + id);
            }
            repo.deleteById(id);
        }


}
