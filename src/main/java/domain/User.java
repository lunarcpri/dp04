package domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class User extends UserOrNutritionist
{
    private Collection<Recipe> recipes;
    private Collection<MasterClass> masterClasses;
    public User()
    {
        super();
    }

    @OneToMany(mappedBy = "author")
    public Collection<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Collection<Recipe> recipes) {
        this.recipes = recipes;
    }

    @ManyToMany
    public Collection<MasterClass> getMasterClasses() {
        return masterClasses;
    }

    public void setMasterClasses(Collection<MasterClass> masterClasses) {
        this.masterClasses = masterClasses;
    }
}
