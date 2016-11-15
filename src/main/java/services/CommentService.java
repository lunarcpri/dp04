package services;

import domain.Comment;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CommentRepository;
import security.UserAccountService;

import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserOrNutritionistService userOrNutritionistService;


    public CommentService(){
        super();
    }


    public Comment create(){
        return new Comment();
    }
    public Comment findOne(int id){
        Comment result;

        result = commentRepository.findOne(id);
        Assert.notNull(result);

        return result;
    }

    private void save(Comment comment){
        Assert.notNull(comment);

        commentRepository.save(comment);
    }

    public Collection<Comment> findAll(){
        Collection<Comment> result;

        result = commentRepository.findAll();
        Assert.notNull(result);

        return result;
    }

    public Collection<Comment> findAllCommentsByRecipe(int id){
        Collection<Comment> result;

        result = commentRepository.findAllCommentsByRecipe(id);
        Assert.notNull(result);

        return  result;
    }

    public void newComment(Comment comment){
        userAccountService.assertRole("USER,NUTRITIONIST");
        User u = userService.findByPrincipal();
        comment.setAutor(u);
        comment.setCreated_at(new Date());

        save(comment);
    }


}
