package services;

import domain.Ingredient;
import domain.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.IngredientRepository;
import security.UserAccountService;

import java.util.Collection;

@Service
@Transactional
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAccountService userAccountService;

    public IngredientService(){
        super();
    }


    // Listing
    public Collection<Ingredient> findAll(){
        Collection<Ingredient> result;

        result = ingredientRepository.findAll();
        Assert.notNull(result);

        return  result;
    }

    public Ingredient findOne(int id){
        Ingredient result;

        result = ingredientRepository.findOne(id);
        Assert.notNull(result);

        return  result;
    }

    // Create

    public Ingredient create(){
        return new Ingredient();
    }

    public void save(Ingredient ingredient){
        Assert.notNull(ingredient);

        ingredientRepository.save(ingredient);
    }



    public void newIngredient(Ingredient ingredient){
        userAccountService.assertRole("NUTRITIONIST");
        Assert.notNull(ingredient);

        save(ingredient);
    }

    public void modify(Ingredient ingredient){
        userAccountService.assertRole("NUTRITIONIST");
        Ingredient ingredientModified = findOne(ingredient.getId());
        ingredientModified.setDescription(ingredient.getDescription());
        ingredientModified.setName(ingredient.getName());
        ingredientModified.setProperties(ingredient.getProperties());

        save(ingredient);
    }

     public void delete(Ingredient ingredient){
         userAccountService.assertRole("NUTRITIONIST");

         Assert.isTrue(ingredient.getQuantities().size()==0);

         ingredientRepository.delete(ingredient);
     }

}
