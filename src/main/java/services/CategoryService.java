package services;

import domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CategoryRepository;
import security.UserAccountService;

import java.util.Collection;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserAccountService userAccountService;

    public CategoryService(){
        super();
    }

    public Collection<Category> findAll(){
        return categoryRepository.findAll();
    }

    private Category create(){
        return new Category();
    }

    public Category findOne(int id){
        Category result;

        result = categoryRepository.findOne(id);
        Assert.notNull(result);

        return result;
    }

    private void save(Category category){
        Assert.notNull(category);

        categoryRepository.save(category);
    }




    public void create(Category category){
        userAccountService.assertRole("ADMIN");

        save(category);
    }

    public void modify(Category category){
        userAccountService.assertRole("ADMIN");
        Category categoryModified = findOne(category.getId());
        categoryModified.setName(category.getName());
        categoryModified.setDescription(category.getDescription());
        categoryModified.setParent(category.getParent());
        categoryModified.setPicture(category.getPicture());
        categoryModified.setTags(category.getTags());

        save(category);
    }

    public void delete(Category category){
        userAccountService.assertRole("ADMIN");

        Assert.notNull(category);
        Assert.isTrue(category.getRecipes().size()==0);
            for(Category e:category.getChildrens()){
               Assert.isTrue(e.getRecipes().size()>0);
        }
        categoryRepository.delete(category);
    }


}
