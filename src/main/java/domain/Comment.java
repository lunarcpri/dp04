package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Comment extends domain.DomainEntity {

    private String title;
    private String text;
    private Collection<Star> stars;
    private UserOrNutritionist autor;
    private Recipe recipe;
    private Date created_at;


    public Comment()
    {
        super();
    }

    @NotBlank
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotBlank
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    //Relationships

    @Valid
    @OneToMany(mappedBy = "comment")
    public Collection<Star> getStars() {
        return stars;
    }

    public void setStars(Collection<Star> stars) {
        this.stars = stars;
    }

    @ManyToOne(optional = false)
    public UserOrNutritionist getAutor() {
        return autor;
    }

    public void setAutor(UserOrNutritionist userOrNutritionist) {
        this.autor = userOrNutritionist;
    }

    @ManyToOne
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
