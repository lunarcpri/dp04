package services;

import domain.Contest;
import domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.ContestRepository;
import security.UserAccountService;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ContestService {

    @Autowired
    private ContestRepository contestRepository;


    @Autowired
    private UserAccountService userAccountService;

    @Autowired UserService userService;

    @Autowired
    AdministratorService administratorService;


    public ContestService(){
        super();
    }

    private Contest create(){
        Contest result;

        result = new Contest();

        return result;
    }

    private void save(Contest contest){
        Assert.notNull(contest);

        contestRepository.save(contest);
    }

    public Contest findOne(int id){
        Contest result;

        result = contestRepository.findOne(id);
        Assert.notNull(result);

        return result;
    }

    public Collection<Contest> findAll(){
        Collection<Contest> result;

        result = contestRepository.findAll();
        Assert.notNull(result);

        return result;
    }


    public void newContest(Contest contest){
        userAccountService.assertRole("ADMIN");
        Assert.isTrue(contest.getClosed_at().after(contest.getOpened_at()));
        Assert.isTrue(contest.getOpened_at().after(new Date()));
        Assert.notNull(contest);
        save(contest);
    }


    public void processWinner(){
        Collection<Contest> result;

        userAccountService.assertRole("ADMIN");

        result = contestRepository.findClosedContests();
        Assert.notNull(result);
        for(Contest e: result) {
           List<Recipe> recipes =findContestRecipesOrderByLikes(e.getId());
            if (recipes.size()>0){
                int winners = (recipes.size()>3) ? 3 : recipes.size();
                List<Recipe> winnersList = recipes.subList(0,winners);
                e.setWinnerRecipes(winnersList);
            }
            e.setEnded(true);
            save(e);

        }

    }

    public List<Recipe> findContestRecipesOrderByLikes(int id){
        List<Recipe> result = new ArrayList<Recipe>();

        Collection<Object[]> collection = contestRepository.findContestRecipesOrderByLikes(id);
        Assert.notNull(collection);

        for(Object[] e: collection){
            result.add((Recipe) e[0]);
        }

        return  result;
    }

    public List<Object[]> findMinMaxAvgRecipesPerContest(){
        List<Object[]> result;

        result = contestRepository.findMinMaxAvgRecipesPerContest();
        Assert.notNull(result);

        return result;
    }

    public Contest findContestWithMoreRecipes(){
        Contest result;

        result = contestRepository.findContestWithMoreRecipes();
        Assert.notNull(result);

        return result;
    }
}

