package edu.austral.ingsis.repositories;

import edu.austral.ingsis.domain.post.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface PostRepository extends CrudRepository<Post, String> {
}
