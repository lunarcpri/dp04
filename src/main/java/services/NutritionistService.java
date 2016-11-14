package services;

import domain.Nutritionist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.NutritionistRepository;
import security.LoginService;
import security.UserAccount;

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

    public Nutritionist findByPrincipal() {
        Nutritionist result;
        UserAccount userAccount;

        userAccount = LoginService.getPrincipal();
        Assert.notNull(userAccount);
        result = nutritionistRepository.findByNutritionistAccountId(userAccount.getId());

        return result;
    }
}
