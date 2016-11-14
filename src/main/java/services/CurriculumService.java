package services;

import domain.Actor;
import domain.Curriculum;
import domain.Nutritionist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CurriculumRepository;
import security.UserAccountService;

@Service
@Transactional
public class CurriculumService {

    @Autowired
    private CurriculumRepository curriculumRepository;

    @Autowired
    private NutritionistService nutritionistService;

    @Autowired
    private UserAccountService userAccountService;

    public CurriculumService(){
        super();
    }


    private Curriculum create(){
        return new Curriculum();
    }


    public Curriculum findOne(int id){
        Curriculum result;

        result = curriculumRepository.findOne(id);
        Assert.notNull(result);

        return result;
    }

    public Curriculum findByActor(int id){
        Curriculum result;

        result = curriculumRepository.findCurriculumByActor(id);
        Assert.notNull(result);

        return result;
    }

    public void deleteCurriculum(int id){
        Actor a = nutritionistService.findByPrincipal();
        Curriculum curriculum = findOne(id);

        if (curriculum.getNutritionist() == a){
            delete(curriculum);
        }
    }

    private void delete(Curriculum curriculum){
        Assert.notNull(curriculum);

        curriculumRepository.delete(curriculum);
    }

    private void save(Curriculum curriculum){
        Assert.notNull(curriculum);

        curriculumRepository.save(curriculum);
    }


    public void newCurriculum(Curriculum curriculum){
        userAccountService.assertRole("NUTRITIONIST");
        Nutritionist nutritionist = nutritionistService.findByPrincipal();
        curriculum.setNutritionist(nutritionist);
        save(curriculum);

    }

    public void modify(int id, Curriculum curr){
        userAccountService.assertRole("NUTRITIONIST");
        Nutritionist nutritionist = nutritionistService.findByPrincipal();
        Curriculum curriculum = findOne(id);
        curriculum.setEducational(curr.getEducational());
        curriculum.setExperience(curr.getExperience());
        curriculum.setHobbies(curr.getHobbies());
        curriculum.setReferences(curr.getReferences());

        save(curriculum);
    }
}
