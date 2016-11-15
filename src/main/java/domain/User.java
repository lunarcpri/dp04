package domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class User extends UserOrNutritionist
{
    private Collection<Recipe> recipes;
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

}
