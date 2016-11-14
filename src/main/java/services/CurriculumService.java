package services;

import domain.Actor;
import domain.Curriculum;
import domain.Nutritionist;
import domain.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CurriculumRepository;
import security.Authority;
import security.UserAccountService;

import java.util.Collection;

@Service
@Transactional
public class CurriculumService {

    @Autowired
    private CurriculumRepository curriculumRepository;

    @Autowired
    private UserService userService;

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
        Actor a = userService.findByPrincipal();
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


    public void newCurriculum(String educational, String experience, String hobbies, Collection<Reference> references){
        userAccountService.assertRole("NUTRITIONIST");
        Actor actor = userService.findByPrincipal();
        Nutritionist nutritionist = (Nutritionist) actor;
        Curriculum curriculum = create();
        curriculum.setEducational(educational);
        curriculum.setExperience(experience);
        curriculum.setHobbies(hobbies);
        curriculum.setNutritionist(nutritionist);
        curriculum.setReferences(references);

        save(curriculum);

    }

    public void modify(int id,String educational, String experience, String hobbies, Collection<Reference> references){
        userAccountService.assertRole("NUTRITIONIST");
        Actor actor = userService.findByPrincipal();
        Nutritionist nutritionist = (Nutritionist) actor;
        Curriculum curriculum = findOne(id);
        Assert.isTrue(curriculum.getNutritionist() == nutritionist);
        curriculum.setEducational(educational);
        curriculum.setExperience(experience);
        curriculum.setHobbies(hobbies);
        curriculum.setNutritionist(nutritionist);
        curriculum.setReferences(references);

        save(curriculum);
    }
}
