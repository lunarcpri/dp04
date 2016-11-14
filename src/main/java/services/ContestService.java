package services;

import domain.Contest;
import domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.ContestRepository;
import security.UserAccountService;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ContestService {

    @Autowired
    private ContestRepository contestRepository;

    @Autowired
    private QualifiedRecipeService qualifiedRecipeService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired UserService userService;


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

    public Collection<Recipe> findWinners(int id){
        Collection<Recipe> result;

        result = qualifiedRecipeService.findWinnersRecipesByContestId(id);

        return result;
    }

    public Collection<Recipe> findRecipes(int id){
        Collection<Recipe> result;

        result = qualifiedRecipeService.findRecipesByContestId(id);

        return result;
    }



    public void newContest(Contest contest){
        userAccountService.assertRole("ADMIN");
        Assert.isTrue(contest.getClosed_at().after(contest.getOpened_at()));
        Assert.isTrue(contest.getOpened_at().after(new Date()));
        Assert.notNull(contest);
        save(contest);
    }

    public void modify(Contest contest){
        userAccountService.assertRole("ADMIN");
        if (contest.getQualifiedRecipes().isEmpty()){
            Contest result = findOne(contest.getId());
            result.setClosed_at(contest.getClosed_at());
        }else{
            save(contest);
        }

    }

    public void delete(Contest contest){
        userAccountService.assertRole("ADMIN");
        if (contest.getQualifiedRecipes().isEmpty()){
            delete(contest);
        }
    }

    public void processWinner(){
        Collection<Contest> contests = contestRepository.findClosedContests();

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

