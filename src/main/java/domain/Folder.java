package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Folder extends domain.DomainEntity {

    public enum FolderType{
        SPAMBOX, INBOX, OUTBOX, THRASHBOX, CUSTOM
    }

    private String name;
    private Collection<Message> messages;
    private FolderType folderType;
    private Actor actor;

    public Folder() {
        super();
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Valid
    @ManyToMany
    public Collection<Message> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }


    @Enumerated(EnumType.STRING)
    public FolderType getFolderType() {
        return folderType;
    }

    public void setFolderType(FolderType folderType) {
        this.folderType = folderType;
    }

    @ManyToOne(optional = false)
    @Valid
    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
}
