package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Attachment extends DomainEntity{
    private String url;
    private LearningMaterial learningMaterial;

    public Attachment()
    {
        super();
    }

    @URL
    @NotBlank
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Valid
    @ManyToOne(optional = true)
    public LearningMaterial getLearningMaterial() {
        return learningMaterial;
    }

    public void setLearningMaterial(LearningMaterial learningMaterial) {
        this.learningMaterial = learningMaterial;
    }
}
