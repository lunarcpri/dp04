package domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class QualifiedRecipe extends DomainEntity{

    private Recipe recipe;
    private Contest contest;
    private Boolean winner = false;



    @NotNull
    public Boolean getWinner() {
        return winner;
    }


    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

    @ManyToOne
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @ManyToOne
    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }


}
