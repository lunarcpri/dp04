package repositories;

import domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {


    @Query("select c from Recipe r join r.comments c where r.id=?1 order by c.created_at DESC ")
    Collection<Comment> findAllCommentsByRecipe(int id);
}
