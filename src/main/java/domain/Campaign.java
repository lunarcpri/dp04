package domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Campaign extends DomainEntity {

    private Date start_at;
    private Date end_at;
    private boolean star_campaign;
    private Collection<Banner> banners;
    private Sponsor sponsor;

    public Campaign()
    {
        super();
    }

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date getStart_at() {
        return start_at;
    }

    public void setStart_at(Date start_at) {
        this.start_at = start_at;
    }

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date getEnd_at() {
        return end_at;
    }

    public void setEnd_at(Date end_at) {
        this.end_at = end_at;
    }

    public boolean isStar_campaign() {
        return star_campaign;
    }

    public void setStar_campaign(boolean star_campaign) {
        this.star_campaign = star_campaign;
    }

    @Valid
    @OneToMany(mappedBy = "campaign")
    public Collection<Banner> getBanners() {
        return banners;
    }

    public void setBanners(Collection<Banner> banners) {
        this.banners = banners;
    }


    @ManyToOne(optional = false)
    @Valid
    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }
}
