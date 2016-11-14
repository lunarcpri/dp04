package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends domain.DomainEntity{

    private String name;
    private String description;
    private String picture;
    private Collection<Tag> tags;
    private Category parent;
    private Collection<Category> childrens;
    private Collection<Recipe> recipes;

    public Category()
    {
        super();
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @URL
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    //Relationships

    @Valid
    @ManyToMany
    public Collection<Tag> getTags() {
        return tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

    @ManyToOne(optional = true)
    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent")
    public Collection<Category> getChildrens() {
        return childrens;
    }

    public void setChildrens(Collection<Category> childrens) {
        this.childrens = childrens;
    }

    @OneToMany(mappedBy = "category")
    public Collection<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Collection<Recipe> recipes) {
        this.recipes = recipes;
    }
}
