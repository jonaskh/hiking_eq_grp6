package no.ntnu.hikingstore_6.repositories;

import no.ntnu.hikingstore_6.entities.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    Long countById(Integer id);
}
