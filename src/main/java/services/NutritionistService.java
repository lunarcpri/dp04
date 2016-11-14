package services;

import domain.Actor;
import domain.Nutritionist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.NutritionistRepository;

@Service
@Transactional
public class NutritionistService {

    @Autowired
    private NutritionistRepository nutritionistRepository;


    public NutritionistService(){
        super();
    }

    public Nutritionist findOne(int id){
        Nutritionist result;

        result = nutritionistRepository.findOne(id);

        return result;
    }

    public Nutritionist findNutritionistByActor(Actor actor){
        Nutritionist result;

        result = findOne(actor.getId());
        Assert.notNull(result);

        return result;
    }
}
