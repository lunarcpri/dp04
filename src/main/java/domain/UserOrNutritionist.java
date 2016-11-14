package domain;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public abstract class UserOrNutritionist extends Actor {

    private Collection<Likes> likes;
    private Collection<Comment> comments;
    private Collection<UserOrNutritionist> follower;
    private Collection<UserOrNutritionist> following;


    public UserOrNutritionist()
    {
        super();
    }


    @OneToMany(mappedBy = "userOrNutritionist")
    @Valid
    public Collection<Likes> getLikes() {
        return likes;
    }

    public void setLikes(Collection<Likes> likes) {
        this.likes = likes;
    }

    @Valid
    @OneToMany(mappedBy = "autor")
    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    @ManyToMany(mappedBy = "following")
    public Collection<UserOrNutritionist> getFollower() {
        return follower;
    }

    public void setFollower(Collection<UserOrNutritionist> follower) {
        this.follower = follower;
    }

    @ManyToMany
    public Collection<UserOrNutritionist> getFollowing() {
        return following;
    }

    public void setFollowing(Collection<UserOrNutritionist> following) {
        this.following = following;
    }
}
