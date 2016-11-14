package domain;


import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Curriculum extends DomainEntity
{

    private String educational;
    private String experience;
    private String hobbies;
    private Collection<Reference> references;
    private Nutritionist nutritionist;

    public Curriculum()
    {
        super();
    }

    @SafeHtml
    public String getEducational() {
        return educational;
    }

    public void setEducational(String educational) {
        this.educational = educational;
    }

    @SafeHtml
    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @SafeHtml
    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }


    //Relationships


    @Valid
    @OneToMany(mappedBy = "curriculum")
    public Collection<Reference> getReferences() {
        return references;
    }


    public void setReferences(Collection<Reference> references) {
        this.references = references;
    }


    @Valid
    @NotNull
    @OneToOne(mappedBy = "curriculum")
    public Nutritionist getNutritionist() {
        return nutritionist;
    }

    public void setNutritionist(Nutritionist nutritionist) {
        this.nutritionist = nutritionist;
    }
}
