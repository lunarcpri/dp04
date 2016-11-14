package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public abstract class LearningMaterial extends DomainEntity{
    private String title;
    private String resume;
    private Collection<Attachment> attachments;
    private MasterClass masterClass;


    @NotBlank
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotBlank
    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    @Valid
    @OneToMany(mappedBy = "learningMaterial",cascade = CascadeType.ALL)
    public Collection<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Collection<Attachment> attachments) {
        this.attachments = attachments;
    }

    @ManyToOne
    public MasterClass getMasterClass() {
        return masterClass;
    }

    public void setMasterClass(MasterClass masterClass) {
        this.masterClass = masterClass;
    }
}
