package domain;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Cook extends Actor {


    private Collection<MasterClass> masterClasses;

    @Valid
    @OneToMany(mappedBy = "cook", cascade = CascadeType.ALL)
    public Collection<MasterClass> getMasterClasses() {
        return masterClasses;
    }

    public void setMasterClasses(Collection<MasterClass> masterClasses) {
        this.masterClasses = masterClasses;
    }
}
