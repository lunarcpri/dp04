package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Contest extends DomainEntity {

    private String title;
    private Date opened_at;
    private Date closed_at;
    private int numberOfWinners;
    private Collection<QualifiedRecipe> qualifiedRecipes;
    private boolean ended;

    public Contest() {
        super();
    }

    @NotBlank
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date getOpened_at() {
        return opened_at;
    }

    public void setOpened_at(Date opened_at) {
        this.opened_at = opened_at;
    }


    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date getClosed_at() {
        return closed_at;
    }

    public void setClosed_at(Date closed_at) {
        this.closed_at = closed_at;
    }

    public int getNumberOfWinners() {
        return numberOfWinners;
    }

    public void setNumberOfWinners(int winners) {this.numberOfWinners = winners;}

    @OneToMany(mappedBy = "contest")
    public Collection<QualifiedRecipe> getQualifiedRecipes() {
        return qualifiedRecipes;
    }

    public void setQualifiedRecipes(Collection<QualifiedRecipe> qualifiedRecipes) {
        this.qualifiedRecipes = qualifiedRecipes;
    }

    @Column(columnDefinition="bit default 0")
    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }
}
