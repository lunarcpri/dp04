package services;

import domain.Nutritionist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.NutritionistRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NutritionistService {

    @Autowired
    private NutritionistRepository nutritionistRepository;

    @Autowired
    private FolderService folderService;

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

    public Nutritionist save(Nutritionist n){
        Assert.notNull(n);

        return nutritionistRepository.save(n);
    }
    public void create(Nutritionist n){
        Assert.notNull(n);
        List<Authority> authorities = new ArrayList<Authority>();
        Authority a = new Authority();
        a.setAuthority("NUTRITIONIST");
        authorities.add(a);
        n.getUserAccount().setAuthorities(authorities);
        n = nutritionistRepository.save(n);
        Assert.notNull(n);
        folderService.createDefaultFolders(n);
    }
}
