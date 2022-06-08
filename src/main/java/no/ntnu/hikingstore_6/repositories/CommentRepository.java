package no.ntnu.hikingstore_6.repositories;

import no.ntnu.hikingstore_6.entities.Comment;
import no.ntnu.hikingstore_6.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    public List<Comment> findByProduct_Id(Integer productId);

}
