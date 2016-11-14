package services;

import domain.Actor;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.ActorRepository;
import repositories.RecipeRepository;
import security.UserAccountService;

import java.util.Collection;

@Service
@Transactional
public class ActorService {


    // Managed repository -----------------------------------------------------

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private UserAccountService userAccountService;

    // Constructors -----------------------------------------------------------

    public ActorService() {

        super();

    }

    // Simple CRUD methods ----------------------------------------------------

    public Collection<Actor> findAll() {
        Collection<Actor> result;

        result = actorRepository.findAll();
        Assert.notNull(result);

        return result;
    }

    public Actor findOne(int actorId) {
        Assert.isTrue(actorId != 0);

        Actor result;

        result = actorRepository.findOne(actorId);
        Assert.notNull(result);

        return result;
    }

    public void save(Actor actor) {
        Assert.notNull(actor);

        actorRepository.save(actor);
    }

    public void delete(Actor actor) {
        Assert.notNull(actor);
        Assert.isTrue(actor.getId() != 0);
        Assert.isTrue(actorRepository.exists(actor.getId()));

        actorRepository.delete(actor);
    }

    public User create() {
        User result;

        result = new User();

        return result;
    }

    // Other business methods -------------------------------------------------



}