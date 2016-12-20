package domain;



import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class SocialIdentity extends domain.DomainEntity{
    public SocialIdentity(){ super();}

    // Attributes -------------------------------------------------------------
    private String nick;
    private String name;
    private String link;
    private String picture;

    @NotBlank
    public String getNick()
    {
        return this.nick;
    }

    public void setNick(String nick)
    {
        this.nick = nick;
    }


    @NotBlank
    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @NotBlank
    @URL
    public String getLink()
    {
        return this.link;
    }

    public void setLink(String url)
    {
        this.link = url;
    }


    @URL
    public String getPicture()
    {
        return this.picture;
    }

    public void setPicture(String picture)
    {
        this.picture = picture;
    }




//Relationships

    private Actor actor;

    @Valid
    @ManyToOne
    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

}