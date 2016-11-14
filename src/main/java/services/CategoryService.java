package services;

import domain.Category;
import domain.Tag;
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

    private void delete(Category category){
        Assert.notNull(categoryRepository);

        categoryRepository.delete(category);
    }



    public void create(String name, String description, Category parent, String picture, Collection<Tag> tags){
        userAccountService.assertRole("ADMIN");
        Category category = create();
        category.setName(name);
        category.setDescription(description);
        category.setParent(parent);
        category.setPicture(picture);
        category.setTags(tags);

        save(category);
    }

    public void modify(int id, String name, String description, Category parent, String picture, Collection<Tag> tags){
        userAccountService.assertRole("ADMIN");
        Category category = findOne(id);
        category.setName(name);
        category.setDescription(description);
        category.setParent(parent);
        category.setPicture(picture);
        category.setTags(tags);

        save(category);
    }

    public void delete(int id){
        userAccountService.assertRole("ADMIN");
        Category category = findOne(id);
        Assert.notNull(category);
        Assert.isTrue(category.getRecipes().size()==0);
        delete(category);
    }


}
