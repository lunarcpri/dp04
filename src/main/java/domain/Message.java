package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Collection;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Message  extends domain.DomainEntity{


    public enum Priority {
        HIGH, NEUTRAL, LOW
    }

    private String body;
    private Date sended_at;
    private String subject;
    private Priority priority;
    private Actor recipient;
    private Actor sender;
    private Collection<Folder> folders;

    public Message()
    {
        super();
    }

    @NotBlank
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date getSended_at() {
        return sended_at;
    }

    public void setSended_at(Date sended_at) {
        this.sended_at = sended_at;
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @NotBlank
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    //Relationships

    @Valid
    @ManyToOne (optional = false)
    public Actor getRecipient() {
        return recipient;
    }

    public void setRecipient(Actor recipient) {
        this.recipient = recipient;
    }

    @Valid
    @ManyToOne (optional = false)
    public Actor getSender() {
        return sender;
    }

    public void setSender(Actor sender) {
        this.sender = sender;
    }

    @ManyToMany
    public Collection<Folder> getFolders() {
        return folders;
    }

    public void setFolders(Collection<Folder> folders) {
        this.folders = folders;
    }
}
