package domain;


import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Nutritionist extends UserOrNutritionist {


    private Curriculum curriculum;
    public Nutritionist()
    {
        super();
    }


    @Valid
    @OneToOne (cascade = CascadeType.ALL)
    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }
}
