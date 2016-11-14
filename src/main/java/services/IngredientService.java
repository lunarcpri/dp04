package services;

import domain.Ingredient;
import domain.Property;
import org.apache.taglibs.standard.extra.spath.ASCII_CharStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.IngredientRepository;
import security.Authority;
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


    private void delete(Ingredient ingredient){
        Assert.notNull(ingredient);

        ingredientRepository.delete(ingredient);
    }

    public void newIngredient(String description, String name, Collection<Property> properties){
        userAccountService.assertRole("NUTRITIONIST");
        Ingredient ingredient = create();
        ingredient.setDescription(description);
        ingredient.setName(name);
        ingredient.setProperties(properties);

        save(ingredient);
    }

    public void modify(int id,String description, String name, Collection<Property> properties){
        userAccountService.assertRole("NUTRITIONIST");
        Ingredient ingredient = findOne(id);
        ingredient.setDescription(description);
        ingredient.setName(name);
        ingredient.setProperties(properties);

        save(ingredient);
    }

     public void delete(int id){
         Ingredient ingredient = ingredientRepository.findOne(id);
         userAccountService.assertRole("NUTRITIONIST");

         Assert.isTrue(ingredient.getQuantities().size()==0);

         delete(ingredient);
     }

}
