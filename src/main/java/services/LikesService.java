package services;

import domain.Actor;
import domain.Likes;
import domain.Recipe;
import domain.UserOrNutritionist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.LikesRepository;
import sun.reflect.generics.visitor.Reifier;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class LikesService {

    @Autowired
    private LikesRepository likesRepository;


    public LikesService(){
        super();
    }

    public Likes create(){
        return new Likes();
    }

    public boolean isRecipeLikedByActor(int actorid, int recipeid,boolean liked){
        Collection<Recipe> recipes = likesRepository.isRecipeLikedByActor(actorid,recipeid,liked);
        Assert.notNull(recipeid);

        return recipes.size()!=0;
    }

    public void delete(Likes likes){
        Assert.notNull(likes);

        likesRepository.delete(likes);
    }



    public Collection<Likes> findRecipeLikeByActor(int actorid, int recipeid){
        Collection<Likes> likes = likesRepository.findRecipeLikeByActor(actorid,recipeid);
        Assert.notNull(likes);

        return likes;
    }

    public void save(Likes likes){
        Assert.notNull(likes);

        likesRepository.save(likes);
    }

    public void like(Recipe recipe, UserOrNutritionist actor, boolean islike){
        Likes likes = create();
        likes.setIsLike(islike);
        likes.setRecipe(recipe);
        likes.setUserOrNutritionist(actor);

        save(likes);
    }


}
