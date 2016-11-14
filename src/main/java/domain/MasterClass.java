package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class MasterClass extends DomainEntity{

    private String title;
    private String description;
    private Cook cook;
    private Collection<LearningMaterial> learningMaterials;
    private boolean promoted;
    private Collection<User> attendingUsers;

    public MasterClass()
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
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Valid
    @OneToMany(mappedBy = "masterClass",cascade = CascadeType.ALL)
    public Collection<LearningMaterial> getLearningMaterials() {
        return learningMaterials;
    }

    public void setLearningMaterials(Collection<LearningMaterial> learningMaterials) {
        this.learningMaterials = learningMaterials;
    }

    @Valid
    @NotNull
    @ManyToOne (optional = false)
    public Cook getCook() {
        return cook;
    }

    public void setCook(Cook cook) {
        this.cook = cook;
    }

    public boolean isPromoted() {
        return promoted;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    @ManyToMany(mappedBy = "masterClasses")
    public Collection<User> getAttendingUsers() {
        return attendingUsers;
    }

    public void setAttendingUsers(Collection<User> attendingUsers) {
        this.attendingUsers = attendingUsers;
    }
}
